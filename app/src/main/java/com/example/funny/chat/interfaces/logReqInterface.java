package com.example.funny.chat.interfaces;

import com.example.funny.chat.PersonData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface logReqInterface {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("/Server/getPerson")
    Observable<PersonData> getPerson(@Field("Login") String log, @Field("Password") String pass);
}
