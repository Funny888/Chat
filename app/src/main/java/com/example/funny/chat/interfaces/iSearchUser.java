package com.example.funny.chat.interfaces;

import com.example.funny.chat.Models.SearchUserModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface iSearchUser {
    @FormUrlEncoded
    @POST ("/Server/searchUser")
    Observable<SearchUserModel> search(@Field("e_mail") String e_mail);

}
