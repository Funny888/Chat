package com.example.funny.chat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.funny.chat.R;

import java.util.ArrayList;

import com.example.funny.chat.Models.ChatModel;

public class RecyclerAdapt extends RecyclerView.Adapter<RecyclerAdapt.Holder> {

    private Context context;
    private ArrayList<ChatModel> messages;

    public RecyclerAdapt(Context context,ArrayList<ChatModel> data)
    {
       this.context = context;
       this.messages = data;
    }


    @NonNull
    @Override
    public RecyclerAdapt.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapt.Holder holder, int position) {

//         RequestCreator pic = Picasso.get().load(messages.get(position).getProfImage());
//         pic.into(holder.ImgMsg);
         holder.TextMsg.setText(messages.get(position).getTextMsg());


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class Holder  extends  RecyclerView.ViewHolder{
        CardView cv;
        ImageView ImgMsg;
        TextView TextMsg;

        public Holder(View itemView) {
            super(itemView);
            ImgMsg = itemView.findViewById(R.id.ImgMsg);
            cv = itemView.findViewById(R.id.card);
            TextMsg = itemView.findViewById(R.id.TextMsg);

        }
    }
}
