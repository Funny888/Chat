package com.example.funny.chat;

import android.os.Build;
import android.os.Debug;

import com.example.funny.chat.interfaces.PutMessageInterface;
import com.example.funny.chat.interfaces.iDeleteFun;
import com.example.funny.chat.interfaces.iSearchUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;

import com.example.funny.chat.interfaces.Chats;
import com.example.funny.chat.interfaces.iGroups;
import com.example.funny.chat.interfaces.RegInterface;
import com.example.funny.chat.interfaces.logReqInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {

    private static final String URL_server = "http://192.168.0.49:8080";

    public static logReqInterface getValid() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .socketFactory(SocketFactory.getDefault())
                .connectTimeout(8, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        logReqInterface logReqInterface = retrofit.create(logReqInterface.class);
        return logReqInterface;
    }

    public static RegInterface createPerson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient ok = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .socketFactory(SocketFactory.getDefault())
                .build();

        Retrofit retr = new Retrofit.Builder()
                .baseUrl(URL_server)
                .client(ok)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RegInterface regInterface = retr.create(RegInterface.class);
        return regInterface;
    }




    public Chats getTokenChats()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient ok = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .client(ok)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Chats chats = retrofit.create(Chats.class);
        return chats;
    }

    public iGroups getGroups()
    {

        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(8,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(ok)
                .build();
        iGroups groups = retrofit.create(iGroups.class);

        return groups;
    }


    public PutMessageInterface sendMsg()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient ok = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(8,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(ok)
                .build();
        PutMessageInterface send = retrofit.create(PutMessageInterface.class);

        return send;
    }

    public iSearchUser iSearchUser()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(8,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(ok)
                .build();

        iSearchUser search = retrofit.create(iSearchUser.class);
        return search;
    }

    public iDeleteFun deleteFun()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(8,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .client(ok)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        iDeleteFun deleteFun = retrofit.create(iDeleteFun.class);
        return deleteFun;
    }
}
