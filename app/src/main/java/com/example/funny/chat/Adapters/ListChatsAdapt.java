package com.example.funny.chat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.funny.chat.R;

import java.util.ArrayList;

import com.example.funny.chat.Models.ChatsListModel;

public class ListChatsAdapt extends RecyclerView.Adapter<ListChatsAdapt.Holder> {

    ArrayList<ChatsListModel> arrayList;
    Context context;
    private  Integer delMsg;

    public Integer getDelMsg() {
        return delMsg;
    }

    public void setDelMsg(Integer delMsg) {
        this.delMsg = delMsg;
    }

    public ListChatsAdapt(Context context, ArrayList<ChatsListModel> list)
    {
        this.arrayList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chatitem,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.ChatName.setText(arrayList.get(position).getChatName());
        holder.ChatText.setText(arrayList.get(position).getTextMsg());
        holder.ChatDate.setText(arrayList.get(position).getDateMsg());
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setDelMsg(arrayList.get(position).get_idMsg());
                return false;
            }
        });






    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Holder extends RecyclerView.ViewHolder{

        TextView ChatName;
        TextView ChatText;
        TextView ChatDate;

        public Holder(View itemView) {
            super(itemView);
            ChatName = itemView.findViewById(R.id.nameChat);
            ChatText = itemView.findViewById(R.id.textChat);
            ChatDate = itemView.findViewById(R.id.dateChat);


        }


    }
}
