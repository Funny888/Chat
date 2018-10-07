package com.example.funny.chat;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonData {

    @SerializedName("Login")
    @Expose
    private String Login;

    @SerializedName("Password")
    @Expose
    private String Password;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Family")
    @Expose
    private String Family;

    @SerializedName("Patronymic")
    @Expose
    private String Patronymic;

    @SerializedName("e_mail") // поменять наименование полей в getPerson;
    @Expose
    private String e_mail;

    @SerializedName("ProfImage")
    @Expose

    private String ProfImage;

    @SerializedName("answer")
    @Expose
    private String answer;

    @SerializedName("_idUser")
    @Expose
    private String _idUser;

    public String getAnswer() {
        return answer;
    }
//        PersonData(@Nullable String ProfImage, String Name, @Nullable String Family, @Nullable String Patronymic, String Login, String Password, String E_mail)
//    {
//        this.ProfImage = ProfImage;
//        this.Name = Name;
//        this.Family = Family;
//        this.Patronymic = Patronymic;
//        this.Login = Login;
//        this.Password = Password;
//        this.e_mail = E_mail;
//
//    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public String getFamily() {
        return Family;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getProfImage() {
        return ProfImage;
    }

    public String get_idUser() {
        return _idUser;
    }

    public void set_idUser(String _idUser) {
        this._idUser = _idUser;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setProfImage(String profImage) {
        ProfImage = profImage;
    }
}
