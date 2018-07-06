package com.example.funny.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RegInterface {
    @POST("/Server/createPerson")
    Observable<PersonData> registration(@Body PersonData data);

}

public class RegPerson extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RegPerson";


    TextInputEditText TprofImage, Tname, Tfamily, Tpatronymic, Tlogin, Tpassword, Te_mail;
    AppCompatButton BImageSrc;
    Button BsendReg;
    RegInterface regInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        TprofImage = findViewById(R.id.ProfImageR);
        Tname = findViewById(R.id.NameR);
        Tfamily = findViewById(R.id.FamilyR);
        Tpatronymic = findViewById(R.id.PatronymicR);
        Tlogin = findViewById(R.id.LoginR);
        Tpassword = findViewById(R.id.PasswordR);
        Te_mail = findViewById(R.id.E_mailR);
        BImageSrc = findViewById(R.id.BProfImageR);
        BImageSrc.setOnClickListener(this);
        BsendReg = findViewById(R.id.sendReg);
        BsendReg.setOnClickListener(this);
        regInterface = retrofitServer.createPerson();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BProfImageR: {

                break;
            }

            case R.id.sendReg: {
                PersonData data = regData(TprofImage.getText().toString(), Tname.getText().toString(), Tfamily.getText().toString(), Tpatronymic.getText().toString(), Tlogin.getText().toString(), Tpassword.getText().toString(), Te_mail.getText().toString());
                regInterface.registration(data).subscribe(personData -> {
                    Log.i(TAG, "onClick: ");
                }, Throwable::printStackTrace);
                break;
            }
        }
    }


    private PersonData regData(@Nullable String ProfImage, String Name, @Nullable String Family, @Nullable String Patronymic, String Login, String Password, String E_mail) {
        PersonData data = new PersonData();
        data.setProfImage(ProfImage);
        data.setName(Name);
        data.setFamily(Family);
        data.setPatronymic(Patronymic);
        data.setLogin(Login);
        data.setPassword(Password);
        data.setE_mail(E_mail);

        //TprofImage.getText().toString(),Tname.getText().toString(),Tfamily.getText().toString(),Tpatronymic.getText().toString(),Tlogin.getText().toString(),Tpassword.getText().toString(),Te_mail.getText().toString()
        //@Nullable String ProfImage,String Name,@Nullable String Family,@Nullable String Patronymic,String Login,String Password,String E_mail
        return data;
    }
}
