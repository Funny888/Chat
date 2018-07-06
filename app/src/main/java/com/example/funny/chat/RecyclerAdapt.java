package com.example.funny.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerAdapt extends RecyclerView.Adapter<RecyclerAdapt.Holder> {

    private ArrayList<PersonData> messages = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerAdapt.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapt.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder  extends  RecyclerView.ViewHolder{
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
