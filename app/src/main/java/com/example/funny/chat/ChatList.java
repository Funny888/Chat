package com.example.funny.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.funny.chat.Adapters.ListChatsAdapt;

import java.util.ArrayList;

import com.example.funny.chat.Models.ChatsListModel;
import com.example.funny.chat.interfaces.Chats;


public class ChatList extends AppCompatActivity {
    public static final String TAG = "ChatList";

    Chats chats;
    RecyclerView resView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlist);

        ArrayList<ChatsListModel> arr = new ArrayList<>();

        chats = new RetrofitServer().getTokenChats();
//        chats.getChats(7).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).flatMap(new Function<ArrayList<ChatsListModel>, Observable<ChatsListModel>>() {
//            @Override
//            public Observable<ChatsListModel> apply(ArrayList<ChatsListModel> chatsListModels) throws Exception {
//                return Observable.fromIterable(chatsListModels);
//            }
//        }).subscribe(chatsListModel -> {
//            Log.i(TAG, "onCreate: " + chatsListModel.getChatName() + " " + chatsListModel.getTextMsg() + " " + chatsListModel.getDateMsg());
//
//            arr.add(new ChatsListModel(chatsListModel.getChatName(),chatsListModel.getProfImage(), chatsListModel.get_idGrp(), chatsListModel.getFromUser(),
//                    chatsListModel.getToUser(), chatsListModel.getDateMsg(), chatsListModel.getTextMsg()));
//            ListChatsAdapt adap = new ListChatsAdapt(this, arr);
//            resView.setAdapter(adap);
//
//        });




        resView = findViewById(R.id.chatlistview);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        arr.add(new ChatsListModel("ew","null",3,14,1,"32","rew"));
        ListChatsAdapt adap = new ListChatsAdapt(this, arr);
        resView.setLayoutManager(lm);
        resView.setAdapter(adap);



    }
}
