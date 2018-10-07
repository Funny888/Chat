package com.example.funny.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funny.chat.Adapters.GroupAdapter;
import com.example.funny.chat.Models.GroupModel;
import com.example.funny.chat.interfaces.iGroups;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Groups extends Fragment implements GroupAdapter.MyItemListener {
    public static final String TAG = "iGroups";

    ArrayList<GroupModel> models = new ArrayList<>();
    iGroups groups;
    GroupAdapter adapter;
    RecyclerView GroupsList;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group, container, false);

        GroupsList = view.findViewById(R.id.ListGroup);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        GroupsList.setLayoutManager(manager);

        groups = new RetrofitServer().getGroups();

        groups.getGroup(Integer.parseInt("1")).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<ArrayList<GroupModel>, Observable<GroupModel>>() {
            @Override
            public Observable<GroupModel> apply(ArrayList<GroupModel> groupModels) throws Exception {
                return Observable.fromIterable(groupModels);
            }
        }).subscribe(groupModel -> {

            models.add(new GroupModel(
                    groupModel.getChatName(),
                    groupModel.getProfImage(),
                    groupModel.get_idGrp(),
                    groupModel.getFromUser(),
                    groupModel.getToUser(),
                    groupModel.getDateMsg(),
                    groupModel.getTextMsg()
            ));


            adapter = new GroupAdapter(view.getContext(), models);
            adapter.List(this);

            GroupsList.setAdapter(adapter);

            Log.i(TAG, "onCreateView: " + groupModel.get_idGrp());

        });


        return view;
    }

    @Override
    public void itemListener(int i) {
        Log.i(TAG, "itemListener: " + String.valueOf(i));
    }
}
