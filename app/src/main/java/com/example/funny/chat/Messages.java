package com.example.funny.chat;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.funny.chat.Adapters.ListChatsAdapt;
import com.example.funny.chat.Models.ChatsListModel;
import com.example.funny.chat.interfaces.Chats;
import com.example.funny.chat.interfaces.PutMessageInterface;
import com.example.funny.chat.interfaces.iDeleteFun;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class Messages extends AppCompatActivity {
    public static final String TAG = "Messages: ";
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsBar;
    ImageView profImage;
    android.support.v7.widget.Toolbar toolbar;
    EditText writeText;
    Button sendB;
    Chats chats;
    RecyclerView resView;
    PutMessageInterface send;
    ChatsListModel model;
    iDeleteFun deleteFun;
    ListChatsAdapt adap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsBar = findViewById(R.id.colaps);
        profImage = findViewById(R.id.profImage);
        toolbar = findViewById(R.id.toolbar);
        resView = findViewById(R.id.recycle);
        writeText = findViewById(R.id.writeText);
        sendB = findViewById(R.id.sendB);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        resView.setLayoutManager(lm);

        ArrayList<ChatsListModel> arr = new ArrayList<>();

        chats = new RetrofitServer().getTokenChats();
        send = new RetrofitServer().sendMsg();
        String[] id = getIntent().getStringArrayExtra("groupData");
        chats.getChats(Integer.parseInt(id[0])).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).flatMap(new Function<ArrayList<ChatsListModel>, Observable<ChatsListModel>>() {
            @Override
            public Observable<ChatsListModel> apply(ArrayList<ChatsListModel> chatsListModels) throws Exception {
                return Observable.fromIterable(chatsListModels);
            }
        }).subscribe(chatsListModel -> {
            Log.i(TAG, "onCreate: " +  chatsListModel.getChatName() + " " + chatsListModel.getTextMsg() + " " + chatsListModel.getDateMsg());

            arr.add(new ChatsListModel(chatsListModel.getChatName(), chatsListModel.getProfImage(),chatsListModel.get_idMsg(), chatsListModel.get_idGrp(), chatsListModel.getFromUser(),
                    chatsListModel.getToUser(), chatsListModel.getDateMsg(), chatsListModel.getTextMsg()));
            adap = new ListChatsAdapt(this, arr);
            resView.setAdapter(adap);

        });


        sendB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                send.sendMsg(new ChatsListModel(
                        id[1], null,null,
                        Integer.parseInt(id[0]),
                        Integer.parseInt(id[2]),
                        Integer.parseInt(id[3]),
                        String.valueOf(form.format(new Date().getTime()))
                        , writeText.getText().toString())).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread()).subscribe(chatsListModel -> {
                    writeText.setText("");
                    Snackbar.make(v, chatsListModel.getAnswer(), Snackbar.LENGTH_SHORT);
                });
            }
        });


        ItemTouchHelper.Callback cal = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


                Log.i(TAG, "onSwiped: " + String.valueOf(adap.getDelMsg()));
            }
        };

        ItemTouchHelper help = new ItemTouchHelper(cal);
        help.attachToRecyclerView(resView);
    }
}

