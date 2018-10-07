package com.example.funny.chat.interfaces;

import java.util.ArrayList;

import com.example.funny.chat.Models.GroupModel;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface iGroups {
    @POST("Server/getGroups")
    @FormUrlEncoded
    Observable<ArrayList<GroupModel>> getGroup(@Field("_idUser") Integer id);
}
