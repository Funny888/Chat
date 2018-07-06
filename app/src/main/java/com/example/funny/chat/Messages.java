package com.example.funny.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class Messages extends AppCompatActivity{

ListView list;
String[] array = new String[30];
AppBarLayout appBarLayout;
ImageView profile;
RecyclerAdapt recyclerAdapt;
RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        profile = findViewById(R.id.profImage);
        RequestCreator picasso = Picasso.get().load("http://icons.iconarchive.com/icons/uiconstock/socialmedia/72/Friendster-icon.png");
        picasso.into(profile);
//        test();

       appBarLayout = findViewById(R.id.appBarLayout);
       appBarLayout.setExpanded(false);
       appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
           @Override
           public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

           }
       });

       recyclerView = findViewById(R.id.recycle);
        recyclerAdapt = new RecyclerAdapt();
        recyclerView.setAdapter(recyclerAdapt);

    }
    private void test()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i<30;i++)
                {
                    array[i] = "stroka" + String.valueOf(i);

                }
                ArrayAdapter<String > adapter = new ArrayAdapter<String>(Messages.this,android.R.layout.simple_list_item_1,array);
                list.setAdapter(adapter);


            }
        }).start();
    }

}
