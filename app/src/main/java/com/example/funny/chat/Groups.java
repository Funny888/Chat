package com.example.funny.chat;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.funny.chat.Adapters.GroupAdapter;
import com.example.funny.chat.Database.DataFun;
import com.example.funny.chat.Database.DataSave;
import com.example.funny.chat.Models.GroupModel;
import com.example.funny.chat.interfaces.iGroups;
import com.example.funny.chat.interfaces.iSearchUser;

import java.util.ArrayList;
import java.util.Iterator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Groups extends Fragment {
    public static final String TAG = "iGroups";

    ArrayList<GroupModel> modelsDB = new ArrayList<>();
    ArrayList<GroupModel> modelsReq = new ArrayList<>();
    iGroups groups;
    GroupAdapter adapter;
    RecyclerView GroupsList;
    Toolbar toolbar;
    iSearchUser searchUser;
    String _idUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group, container, false);
        setRetainInstance(true);

        toolbar = view.findViewById(R.id.searchBar);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
         ActionBar appbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);

        GroupsList = view.findViewById(R.id.ListGroup);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        GroupsList.setLayoutManager(manager);

        groups = new RetrofitServer().getGroups();

        SharedPreferences shr = view.getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        _idUser = shr.getString("UserId", "");

        DataFun dataFun = new DataFun(view.getContext());

         Iterator<String []> it = dataFun.ShowTableU(DataSave.TABLE_Gdata).iterator();

        modelsDB.clear();
         while (it.hasNext())
         {

             String[] line = it.next();
                 modelsDB.add(new GroupModel(
                         line[0],
                         line[1],
                         Integer.parseInt(line[2]),
                         Integer.parseInt(line[3]),
                         Integer.parseInt(line[4]),
                         line[5],
                         line[6]
                         ));

         }



            groups.getGroup(Integer.parseInt(_idUser)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<ArrayList<GroupModel>, Observable<GroupModel>>() {
                @Override
                public Observable<GroupModel> apply(ArrayList<GroupModel> groupModels) throws Exception {
                    return Observable.fromIterable(groupModels);
                }
            }).subscribe(groupModel -> {


                modelsReq.add(new GroupModel(
                        groupModel.getChatName(),
                        groupModel.getProfImage(),
                        groupModel.get_idGrp(),
                        groupModel.getFromUser(),
                        groupModel.getToUser(),
                        groupModel.getDateMsg(),
                        groupModel.getTextMsg()
                ));



                    if (!VerefChat(groupModel.get_idGrp())) {
                dataFun.InsertTableGroup(
                        groupModel.getChatName(),
                        groupModel.getProfImage(),
                        groupModel.get_idGrp(),
                        String.valueOf(groupModel.getFromUser()),
                        String.valueOf(groupModel.getToUser()),
                        groupModel.getDateMsg(),
                        groupModel.getTextMsg(),
                        "Записано"
                );
                    }

                dataFun.VerefUpdate(groupModel.get_idGrp(),groupModel.getProfImage(),groupModel.getTextMsg(),groupModel.getDateMsg());

                adapter = new GroupAdapter(view.getContext(), modelsDB);

                GroupsList.setAdapter(adapter);

                dataFun.ShowTableU(DataSave.TABLE_Gdata);

            });
            appbar.setDisplayHomeAsUpEnabled(true);
            appbar.setHomeAsUpIndicator(getResources().getDrawable(R.mipmap.home,getContext().getTheme()));

        ItemTouchHelper.Callback cal = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE,ItemTouchHelper.LEFT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                modelsDB.remove(position);
                DataFun df = new DataFun(view.getContext());
                df.DeleteTableG(modelsDB.get(position).get_idGrp());
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();

            }
        };

            ItemTouchHelper touch = new ItemTouchHelper(cal);
            touch.attachToRecyclerView(GroupsList);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            {
                getActivity().getWindow().getDecorView().clearFocus();
                getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                getActivity().getSupportFragmentManager().popBackStack();
                Snackbar.make(getView().getRootView(),"Переход на главную",Snackbar.LENGTH_LONG).show();
                break;
            }
            case R.id.searchButt: {
                searchUsermethod();
                    break;
                }
        }
        return false;
    }

    private boolean searchUsermethod() {
        searchUser = new RetrofitServer().iSearchUser();
        ArrayList<String> list = new ArrayList<>();

         String  edit = ((EditText)toolbar.findViewById(R.id.searchEdit)).getText().toString();
        Log.i(TAG, "searchUsermethod: " + edit);
        searchUser.search( edit)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(searchUserModel ->
        {
            list.add(searchUserModel.get_id());
            list.add(searchUserModel.getProfImage());
            list.add(searchUserModel.getName());
            list.add(searchUserModel.getFamily());
            list.add(searchUserModel.getPatronymic());
            list.add(searchUserModel.getE_mail());

            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.searching_user_dialog);
            dialog.setTitle("Поиск пользователя");
            ((TextView) dialog.findViewById(R.id.NameDialog)).setText(list.get(2));
            Glide.with(dialog.findViewById(R.id.ProfImageDialog)).load(list.get(1));
            Log.i(TAG, "searchUsermethod: " + list.get(1));
            ((TextView) dialog.findViewById(R.id.Patronymicdialog)).setText(list.get(4));
            ((TextView) dialog.findViewById(R.id.e_maildialog)).setText(list.get(5));
            dialog.findViewById(R.id.addButt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    groups.createGroup(Integer.parseInt(_idUser),Integer.parseInt(list.get(0)))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(answerGroupModel ->
                    {
                        Log.i(TAG, "onClick: " + answerGroupModel.getAnswer());
                        dialog.dismiss();
                    });
                }
            });
            dialog.create();
            dialog.show();
        });





        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private boolean VerefChat(Integer idGrp)
    {
        boolean veref = false;
     Iterator<GroupModel> it = modelsDB.iterator();
        while (it.hasNext())
        {
            if (it.next().get_idGrp().equals(idGrp))
                veref = true;
        }

        return veref;
    }
}
