package com.example.funny.chat.interfaces;

import com.example.funny.chat.Models.AnswerGroupModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface iDeleteFun {
    @FormUrlEncoded
    @POST()
    Observable<AnswerGroupModel> deleteGroup(@Field("_idGrp") Integer _idGrp);

    @FormUrlEncoded
    @POST("/Server/deleteMessage")
    Observable<AnswerGroupModel> deleteMsg(@Field("_idMsg") Integer _idMsg);
}
