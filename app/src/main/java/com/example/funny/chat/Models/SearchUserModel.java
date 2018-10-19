package com.example.funny.chat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchUserModel {

    @SerializedName("ProfImage")
    @Expose
    private String ProfImage;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Family")
    @Expose
    private String Family;

    @SerializedName("Patronymic")
    @Expose
    private String Patronymic;

    @SerializedName("e_mail")
    @Expose
    private String e_mail;

    public String get_id() {
        return _id;
    }

    @SerializedName("_id")
    @Expose
    private String _id;

    public String getProfImage() {
        return ProfImage;
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
}
