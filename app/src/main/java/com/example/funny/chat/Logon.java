package com.example.funny.chat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.funny.chat.interfaces.logReqInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class Logon extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Logon";

    EditText login, password;
    Button bEnter, bRegister;
    ProgressBar progress;
    Intent intent;
    logReqInterface logReqInterface;
    Observable<Boolean> obs;
    Boolean validAccess;
    SharedPreferences date;

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
        logReqInterface = RetrofitServer.getValid();

        date = getSharedPreferences("logon",MODE_PRIVATE);
        if (date.contains("login") && date.contains("pass"))
        {
            login.setText(date.getString("login",""));
            password.setText(date.getString("pass",""));
            bEnter.callOnClick();
        }





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



        logReqInterface.getPerson(login.getText().toString(),password.getText().toString())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(personData -> {
            Log.i(TAG, "log: " + personData.getLogin() + " pass:" + personData.getPassword());
                if (personData.getLogin() != null && personData.getPassword() != null)
                {
                    String[] data = {
                            personData.getName(),
                            personData.getFamily(),
                            personData.getPatronymic(),
                            personData.getProfImage(),
                            personData.getE_mail(),
                            personData.get_idUser()};
                    progress.setVisibility(ProgressBar.INVISIBLE);
                    Date();
                    startActivity(intent.putExtra("data",data));
                }
                else
                {
                    progress.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(getBaseContext(),"Не верный логин или пароль",Toast.LENGTH_SHORT).show();

                }
            }
            ,throwable -> {
                    progress.setVisibility(ProgressBar.INVISIBLE);
                            Toast.makeText(getBaseContext(),"Что-то не так",Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                });

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

    private void Date()
    {
        date = getSharedPreferences("logon",MODE_PRIVATE);
        String logDate = login.getText().toString();
        String pasDate = password.getText().toString();
        SharedPreferences.Editor edit = date.edit();
        edit.putString("login",logDate);
        edit.putString("pass",pasDate);
        edit.apply();
    }

    @Override
    public void onBackPressed() {
        setExitDialog();
    }


    private void setExitDialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Выход")
                .setMessage("Выйти из приложения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }

}
