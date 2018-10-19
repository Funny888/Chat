package com.example.funny.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.funny.chat.Adapters.GroupAdapter;

public class Main extends AppCompatActivity {
    DrawerLayout draw;
    NavigationView navigView;
    Toolbar toolbar;
    Intent intent;
    String[] data;
    Fragment frag_profile, groups;
    FragmentTransaction frameLayout;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);


        draw = findViewById(R.id.drawer);
        navigView = findViewById(R.id.navigView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Профиль");
        actionBar.setHomeAsUpIndicator(R.mipmap.menu);

        frag_profile = new Fragment_profile();
        groups = new Groups();


        if (savedInstanceState == null) {
            frameLayout = getSupportFragmentManager().beginTransaction();
            frameLayout.add(R.id.Fragment_contayner, frag_profile);
            frameLayout.commit();
        }


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

        Name.setText(data[0]);
        e_mail.setText(data[4]);
        Family.setText(data[1]);
        Patronymic.setText(data[2]);

        if (data[3] != null) {
            ProfileImage.setBackground(null);
            Glide.with(this).load(data[3]).into(ProfileImage);

        }

        if (data[1] == null) {
            Family.setVisibility(View.GONE);
        }
        if (data[2] == null) {
            Patronymic.setVisibility(View.GONE);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserId", data[5]);
        editor.apply();
        Log.i(TAG, "userID: " + data[5]);
        navigView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                frameLayout = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.profile: {
                        frameLayout.replace(R.id.Fragment_contayner, frag_profile);
                        break;
                    }
                    case R.id.messages: {
                        frameLayout.replace(R.id.Fragment_contayner, groups);
                        break;
                    }
                    case R.id.setting: {
                        Log.i(TAG, "onNavigationItemSelected: settings");
                        break;
                    }

                }
                frameLayout.addToBackStack(getClass().getSimpleName());
                frameLayout.commit();
                draw.closeDrawer(navigView);
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
