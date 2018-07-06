package com.example.funny.chat;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface ServerApi {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST ("/Server/getPerson")
    Observable<PersonData> getPerson(@Field("Login")String log,@Field("Password")String pass);
}


public class Logon extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Logon";

    EditText login, password;
    Button bEnter, bRegister;
    ProgressBar progress;
    Intent intent;
    ServerApi serverApi;
    Observable<Boolean> obs;
    Boolean validAccess;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon_lay);

        login = findViewById(R.id.eLogin);
        password = findViewById(R.id.ePassword);
        progress = findViewById(R.id.progress);
        bEnter = findViewById(R.id.bEnter);
        bEnter.setOnClickListener(this);
        bRegister = findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);
        validFunc();

        serverApi = retrofitServer.getValid();



    }

    @Override
    public void onClick(View view) {

        switch (view.getId())

        {
            case R.id.bEnter: {
                progress.setVisibility(ProgressBar.VISIBLE);
                intent = new Intent(this, Main.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                validUser();
                break;
            }

            case R.id.bRegister: {
                 intent = new Intent(this,RegPerson.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                 startActivity(intent);
                break;
            }
        }

    }


    private Boolean validUser()
    {

          validAccess = false;

        progress.setVisibility(ProgressBar.VISIBLE);



        serverApi.getPerson(login.getText().toString(),password.getText().toString())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(personData -> {
            Log.i(TAG, "log: " + personData.getLogin() + " pass:" + personData.getPassword());
                if (personData.getLogin() != null && personData.getPassword() != null)
                {
                    String[] data = {personData.getName(),personData.getFamily(),personData.getPatronymic(),personData.getProfImage()
                            ,personData.getE_mail()};
                    progress.setVisibility(ProgressBar.INVISIBLE);
                    startActivity(intent.putExtra("data",data));
                }
            }
            ,Throwable::printStackTrace);

        return validAccess;
    }


    private void validFunc() {
        Observable<String> log = RxValid.getValidation(login);
        Observable<String> passwd = RxValid.getValidation(password);

        Observable.combineLatest(log, passwd, new BiFunction<String, String,Boolean>() {
            @Override
            public Boolean apply(String s, String s2) throws Exception {

                if (s.isEmpty() || s2.isEmpty())
                    return false;
                else
                    return true;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean == false)
                {
                    bEnter.setEnabled(false);
                }
                else
                {
                    bEnter.setEnabled(true);
                }

            }
        });
    }

}
