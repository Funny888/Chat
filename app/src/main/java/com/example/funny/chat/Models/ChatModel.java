package com.example.funny.chat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatModel {



    public ChatModel(String a)
    {
        this.setTextMsg(a);
    }


    @SerializedName("_idMsg")
    @Expose
    private int _idMsg;

    @SerializedName("_idGrp")
    @Expose
    private int _idGrp;

    @SerializedName("FromUser")
    @Expose
    private int FromUser;

    @SerializedName("ToUser")
    @Expose
    private int ToUser;

    @SerializedName("DateMsg")
    @Expose
    private String  DateMsg;

    @SerializedName("TextMsg")
    @Expose
    private String  TextMsg;


    public ChatModel(int _idMsg,int _idGrp,int FromUser,int ToUser,String DateMsg,String TextMsg)
    {
        this._idMsg = _idMsg;
        this._idGrp = _idGrp;
        this.FromUser = FromUser;
        this.ToUser = ToUser;
        this.DateMsg = DateMsg;
        this.TextMsg = TextMsg;
    }

    public int get_idMsg() {
        return _idMsg;
    }

    public void set_idMsg(int _idMsg) {
        this._idMsg = _idMsg;
    }

    public int get_idGrp() {
        return _idGrp;
    }

    public void set_idGrp(int _idGrp) {
        this._idGrp = _idGrp;
    }

    public int getFromUser() {
        return FromUser;
    }

    public void setFromUser(int fromUser) {
        FromUser = fromUser;
    }

    public Integer getToUser() {
        return ToUser;
    }

    public void setToUser(int toUser) {
        ToUser = toUser;
    }

    public String getDateMsg() {
        return DateMsg;
    }

    public void setDateMsg(String dateMsg) {
        DateMsg = dateMsg;
    }

    public String getTextMsg() {
        return TextMsg;
    }

    public void setTextMsg(String textMsg) {
        TextMsg = textMsg;
    }
}
