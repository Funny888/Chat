package com.example.funny.chat.interfaces;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getMessages
{
    @FormUrlEncoded
    @POST("/Server/accountMsg")
    Observable<JsonObject> getMsg(@Field("_idUser") Integer _userId);
}
