package com.example.funny.chat.interfaces;

import com.example.funny.chat.PersonData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegInterface {
    @Headers("Content-Type: application/json")
    @POST("/Server/createPerson")
    Observable<PersonData> registration(@Body PersonData data);

}
