package com.example.funny.chat.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.funny.chat.R;

import java.util.ArrayList;

import com.example.funny.chat.Models.ChatsListModel;

public class ListChatsAdapt extends RecyclerView.Adapter<ListChatsAdapt.Holder> {

    ArrayList<ChatsListModel> arrayList;
    Context context;

    public ListChatsAdapt(Context context, ArrayList<ChatsListModel> list)
    {
        this.arrayList = list;
        this.context = context;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull Holder holder) {
        super.onViewDetachedFromWindow(holder);
      //  Log.i("log", "onViewDetachedFromWindow: " + String.valueOf(holder.ChatText.getText()));
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Glide.with(holder.Image).load(arrayList.get(position).getProfImage());
        holder.ChatText.setText(arrayList.get(position).getTextMsg());
        holder.ChatDate.setText(arrayList.get(position).getDateMsg());


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Holder extends RecyclerView.ViewHolder{

        ImageView Image;
        TextView ChatText;
        TextView ChatDate;

        public Holder(View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.ImgMsg);
            ChatText = itemView.findViewById(R.id.TextMsg);
            ChatDate = itemView.findViewById(R.id.DateMsg);
        }


    }
}
