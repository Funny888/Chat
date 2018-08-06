package com.example.funny.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;


import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


interface getMessages
{
    @FormUrlEncoded
    @POST("/Server/accountMsg")
    Observable <JsonObject> getMsg(@Field("_idUser") Integer _userId);
}


public class Messages extends AppCompatActivity{
public static final String TAG = "Messages: ";

getMessages Msgs;
Integer userId;
ListView list;
String[] array = new String[30];
AppBarLayout appBarLayout;
ImageView profile;
RecyclerAdapt recyclerAdapt;
RecyclerView recyclerView;
ArrayList<ChatModel> dataChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        profile = findViewById(R.id.profImage);
        RequestCreator picasso = Picasso.get().load("http://icons.iconarchive.com/icons/uiconstock/socialmedia/72/Friendster-icon.png");
        picasso.into(profile);

       appBarLayout = findViewById(R.id.appBarLayout);
       appBarLayout.setExpanded(false);
       appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
           @Override
           public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

           }
       });
        userId =1;
       Msgs = new retrofitServer().retGetMsg();



       Msgs.getMsg(userId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(responseBody -> {
           Log.i(TAG, "onCreate: "+ responseBody.getAsJsonObject());
           JsonObject obj = responseBody.getAsJsonObject(); // Принимается ответ с сервера в виде Json объекта

           JsonArray array = (JsonArray) obj.get("Messages");//выдергивается массив объектов внутри полученного объекта
           Log.i(TAG, "onCreate: array " + array);

           dataChat = new ArrayList<>();

            for (int i=0; i<array.size();i++) // идет цикл по получению сообщений
            {
                JsonObject object =(JsonObject) array.get(i); // получается i-ый объект из массива
                Log.i(TAG, "onCreate: OBJECT" + object);

                JsonObject jsonObject = (JsonObject) object.get("message");// получается объект спрятанный в i-тем объекте
                Log.i(TAG, "onCreate: message: " + jsonObject);

                                                                        // Получаются данные поля в объекте message
                   Integer idMsg = (jsonObject.get("idMsg").getAsInt());
                   Integer idGrp = (jsonObject.get("idGrp").getAsInt());
                   Integer FromUser = (jsonObject.get("FromUser").getAsInt());
                   Integer ToUser = (jsonObject.get("ToUser").getAsInt());
                   String DateMsg = jsonObject.get("DateMsg").getAsString();
                   String TextMsg = jsonObject.get("TextMsg").getAsString();
                dataChat.add(new ChatModel(idMsg,idGrp,FromUser,ToUser,DateMsg,TextMsg));
                Log.i(TAG, "TextMsg: " + jsonObject.get("TextMsg"));
            }
           Log.i(TAG, "ListChat >>" + dataChat.iterator().next().getTextMsg());
           recyclerAdapt = new RecyclerAdapt(this,dataChat);
           recyclerView.setAdapter(recyclerAdapt);

       });

       recyclerView = findViewById(R.id.recycle);
       RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
       recyclerView.setLayoutManager(manager);


    }














































}
