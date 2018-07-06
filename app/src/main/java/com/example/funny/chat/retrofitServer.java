package com.example.funny.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.net.SocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitServer {

    private static final String URL_server = "http://192.168.0.5:8080";//192.168.0.5:8080/Server/createPerson


    public static ServerApi getValid() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .socketFactory(SocketFactory.getDefault())
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_server)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        return serverApi;
    }

    public static RegInterface createPerson()
    {
        Gson gson = new GsonBuilder()
                .create();

        OkHttpClient ok = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retr = new Retrofit.Builder()

                .baseUrl(URL_server)
                .client(ok)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RegInterface regInterface = retr.create(RegInterface.class);
        return regInterface;
    }

}
