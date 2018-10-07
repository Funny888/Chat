package com.example.funny.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.funny.chat.Adapters.RecyclerAdapt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



import java.util.ArrayList;

import com.example.funny.chat.Models.ChatModel;
import com.example.funny.chat.interfaces.getMessages;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Messages extends AppCompatActivity {
    public static final String TAG = "Messages: ";
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsBar;
    ImageView profImage;
    android.support.v7.widget.Toolbar toolbar;
    EditText writeText;
    Button sendB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        appBarLayout = findViewById(R.id.appBarLayout);
        collapsBar = findViewById(R.id.colaps);
        profImage = findViewById(R.id.profImage);
        toolbar = findViewById(R.id.toolbar);
        RecyclerView  recycle = findViewById(R.id.recycle);
        writeText = findViewById(R.id.writeText);
        sendB = findViewById(R.id.sendB);

    }
}

