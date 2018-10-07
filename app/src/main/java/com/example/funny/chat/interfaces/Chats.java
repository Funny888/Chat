package com.example.funny.chat.interfaces;

import com.example.funny.chat.Models.ChatsListModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Chats {
    @POST("/Server/getChat")
    @FormUrlEncoded
    Observable<ArrayList<ChatsListModel>> getChats(@Field("_idGrp") Integer _idGrp);
}
