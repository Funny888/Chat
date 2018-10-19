package com.example.funny.chat.interfaces;

import com.example.funny.chat.Models.ChatsListModel;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface PutMessageInterface {
    @POST("/Server/putInChat")
    Observable<ChatsListModel> sendMsg(@Body ChatsListModel model);
}
