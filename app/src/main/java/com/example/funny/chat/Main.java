package com.example.funny.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.InputStream;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout draw;
    NavigationView navigView;
    Toolbar toolbar;
    Intent intent;
    InputStream in;
    String[] data;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        draw = findViewById(R.id.drawer);
        navigView = findViewById(R.id.navigView);
        navigView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Профиль");
        actionBar.setHomeAsUpIndicator(R.mipmap.menu);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, draw, toolbar, R.string.drawer_opened, R.string.drawer_closed);
        toggle.syncState();
        intent = getIntent();
        data = intent.getStringArrayExtra("data");

        ImageView ProfileImage = (ImageView) navigView.getHeaderView(0).findViewById(R.id.head_ProfileImage);
        TextView Name = (TextView) navigView.getHeaderView(0).findViewById(R.id.head_Name);
        TextView e_mail = (TextView) navigView.getHeaderView(0).findViewById(R.id.head_e_mail);
        TextView Family = (TextView) navigView.getHeaderView(0).findViewById(R.id.head_Family);
        TextView Patronymic = (TextView) navigView.getHeaderView(0).findViewById(R.id.head_Patronymic);

//
//        Name.setText(data[0]);
//        e_mail.setText(data[4]);
//        Family.setText(data[1]);
//        Patronymic.setText(data[2]);
//
//        if (data[3] != null) {
//            ProfileImage.setBackground(null);
//            RequestCreator pic = Picasso.get().load(data[3]);
//            pic.into(ProfileImage);}
//
//        if (data[1] == null)
//        {Family.setVisibility(View.GONE);}
//        if (data[2] == null)
//        {Patronymic.setVisibility(View.GONE);}


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile: {
                Log.i(TAG, "onNavigationItemSelected: profile");
            }
            case R.id.messages: {
                Log.i(TAG, "onNavigationItemSelected: message");
            }
            case R.id.setting: {
                Log.i(TAG, "onNavigationItemSelected: settings");
            }

        }
        draw.closeDrawer(navigView);
        return true;
    }
}
