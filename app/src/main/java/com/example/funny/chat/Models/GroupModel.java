package com.example.funny.chat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupModel {


    @SerializedName("ChatName")
    @Expose
    private String ChatName;

    @SerializedName("TextMsg")
    @Expose
    private String TextMsg;

    @SerializedName("_idGrp")
    @Expose
    private Integer _idGrp;

    @SerializedName("DateMsg")
    @Expose
    private String DateMsg;

    @SerializedName("ToUser")
    @Expose
    private Integer ToUser;

    @SerializedName("FromUser")
    @Expose
    private Integer FromUser;

    @SerializedName("ProfImage")
    @Expose
    private String ProfImage;


    public GroupModel(String ChatName,String ProfImage, Integer _idGrp,Integer FromUser, Integer ToUser,String DateMsg, String TextMsg)
    {
        this.ChatName = ChatName;
        this.ProfImage = ProfImage;
        this._idGrp = _idGrp;
        this.FromUser = FromUser;
        this.ToUser = ToUser;
        this.DateMsg = DateMsg;
        this.TextMsg = TextMsg;
    }

    public String getChatName() {
        return ChatName;
    }



    public String getTextMsg() {
        return TextMsg;
    }


    public Integer get_idGrp() {
        return _idGrp;
    }



    public String getDateMsg() {
        return DateMsg;
    }



    public Integer getToUser() {
        return ToUser;
    }



    public Integer getFromUser() {
        return FromUser;
    }


    public String getProfImage() {
        return ProfImage;
    }
}
