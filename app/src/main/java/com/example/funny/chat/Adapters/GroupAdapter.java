package com.example.funny.chat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.funny.chat.Models.GroupModel;
import com.example.funny.chat.R;

import java.util.ArrayList;


public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyHolder> {

    public interface MyItemListener
    {
        void itemListener(int i);
    }

   private MyItemListener listener;

    public static final String TAG = "GroupAdapter";

    Context context;
    ArrayList<GroupModel> models;

  public void List(MyItemListener listenr)
  {
      this.listener = listenr;
  }

    public GroupAdapter(Context ctxt, ArrayList<GroupModel> models) {

        this.models = models;
        context = ctxt;
    }



    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        MyHolder hold = new MyHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.nameGroup.setText(models.get(position).getChatName());
        holder.textGroup.setText(models.get(position).getTextMsg());
        holder.dateTime.setText(models.get(position).getDateMsg());

        try {
            if (!models.get(position).getProfImage().equals(null))
                Glide.with(context).load(models.get(position).getProfImage()).into(holder.image);
        } catch (NullPointerException e) {

        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Вы нажали на диалог " + String.valueOf(position), Snackbar.LENGTH_LONG)
                        .show();
                listener.itemListener(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nameGroup, textGroup, dateTime;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            nameGroup = itemView.findViewById(R.id.nameGroup);
            textGroup = itemView.findViewById(R.id.textGroup);
            dateTime = itemView.findViewById(R.id.dateTimeGroup);
            image = itemView.findViewById(R.id.avaGroup);


        }

    }
}
