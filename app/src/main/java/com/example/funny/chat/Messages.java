package com.example.funny.chat;

import android.database.Cursor;
import android.icu.text.TimeZoneFormat;
import android.icu.text.TimeZoneNames;
import android.icu.util.ULocale;
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
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.funny.chat.Adapters.ListChatsAdapt;
import com.example.funny.chat.Database.DataFun;
import com.example.funny.chat.Database.DataSave;
import com.example.funny.chat.Models.ChatsListModel;
import com.example.funny.chat.interfaces.Chats;
import com.example.funny.chat.interfaces.PutMessageInterface;
import com.example.funny.chat.interfaces.iDeleteFun;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
    ArrayList<ChatsListModel> arrDB = new ArrayList<>();
    ArrayList<ChatsListModel> arrReq = new ArrayList<>();


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

//        DataSave dataSave = new DataSave(this);
//        dataSave.onUpgrade(dataSave.getWritableDatabase(),1,2);
        DataFun dataFun = new DataFun(this);


        deleteFun = new RetrofitServer().deleteFun();

        chats = new RetrofitServer().getTokenChats();
        send = new RetrofitServer().sendMsg();
        String[] id = getIntent().getStringArrayExtra("groupData");


        Iterator <String[]> it = dataFun.ShowTableU(DataSave.TABLE_Mdata).iterator();

        while (it.hasNext())
        {
            String[] DataLine = it.next();

            if(DataLine[2].equals(id[0]))
            arrDB.add(new ChatsListModel(
                    DataLine[0],
                    Integer.parseInt(DataLine[1]),
                    Integer.parseInt(DataLine[2]),
                    Integer.parseInt(DataLine[3]),
                    Integer.parseInt(DataLine[4]),
                    DataLine[5],
                    DataLine[6]));
        }


       Disposable d = chats.getChats(Integer.parseInt(id[0])).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).flatMap(new Function<ArrayList<ChatsListModel>, Observable<ChatsListModel>>() {
            @Override
            public Observable<ChatsListModel> apply(ArrayList<ChatsListModel> chatsListModels) throws Exception {
                return Observable.fromIterable(chatsListModels);
            }
        }).subscribe(chatsListModel -> {
            Log.i(TAG, "onCreate: " + " " + chatsListModel.getTextMsg() + " " + chatsListModel.getDateMsg());




               arrReq.add(new ChatsListModel(
                       chatsListModel.getProfImage(),
                       chatsListModel.get_idMsg(),
                       chatsListModel.get_idGrp(),
                       chatsListModel.getFromUser(),
                       chatsListModel.getToUser(),
                       chatsListModel.getDateMsg(),
                       chatsListModel.getTextMsg()
               ));

                    if (!VerefId(chatsListModel.get_idMsg()))
                    {
                   dataFun.InsertTableMessage(
                   chatsListModel.get_idMsg(),
                   chatsListModel.get_idGrp(),
                   chatsListModel.getProfImage(),
                   chatsListModel.getDateMsg(),
                   chatsListModel.getTextMsg(),
                   chatsListModel.getFromUser(),
                   chatsListModel.getToUser(),
                   "Записано");
                    }


            dataFun.ShowTableU(DataSave.TABLE_Mdata);
           adap = new ListChatsAdapt(this, arrDB);
           resView.setAdapter(adap);

        });















        sendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                form.setCalendar(Calendar.getInstance());

                send.sendMsg(new ChatsListModel(
                         null,null,
                        Integer.parseInt(id[0]),
                        Integer.parseInt(id[2]),
                        Integer.parseInt(id[3]),
                        String.valueOf(form.format(new Date()))
                        , writeText.getText().toString())).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread()).subscribe(chatsListModel -> {
                    writeText.setText("");
                    Snackbar.make(v, chatsListModel.getAnswer(), Snackbar.LENGTH_SHORT);
                });

                adap.notifyDataSetChanged();
            }
        });


        ItemTouchHelper.Callback cal = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE,ItemTouchHelper.LEFT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                sendDelDate(arrDB,position);
                arrDB.remove(position);
                Log.i(TAG, "onSwiped 0: " + String.valueOf(viewHolder.getAdapterPosition()));

                adap.notifyItemRemoved(viewHolder.getAdapterPosition());
                adap.notifyDataSetChanged();



            }
        };

        ItemTouchHelper help = new ItemTouchHelper(cal);
        help.attachToRecyclerView(resView);
    }



    private void sendDelDate(ArrayList<ChatsListModel> array,Integer position)
    {



            Log.i(TAG, "sendDelDate №: " + array.get(position).get_idMsg());
            Integer i = array.get(position).get_idMsg();
            deleteFun.deleteMsg(i)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(answerGroupModel -> {
                        DataFun df = new DataFun(this);
                        df.DeleteTableM(i);
                        Log.i(TAG, "sendDelDate: " + answerGroupModel.getAnswer());

                    });

    }

    private boolean VerefId(Integer id)
    {
        boolean veref = false;

        Iterator<ChatsListModel> it = arrDB.iterator();

        while (it.hasNext())
        {
            if (it.next().get_idMsg().equals(id))
            {
                veref = true;
                Log.i(TAG, "VerefId: найдено");
                return veref;
            }
        }

        return veref;
    }
}

