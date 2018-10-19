package com.example.funny.chat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class ChatsListModel {



    @SerializedName("ChatName")
    @Expose
    private String ChatName;

    @SerializedName("TextMsg")
    @Expose
    private String TextMsg;

    @SerializedName("_idGrp")
    @Expose
    private Integer _idGrp;

    public Integer get_idMsg() {
        return _idMsg;
    }

    @SerializedName("_idMsg")
    @Expose
    private Integer _idMsg;

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

    @SerializedName("Answer")
    @Expose
    private String answer;


    public ChatsListModel(String ChatName, @Nullable String ProfImage,@Nullable Integer _idMsg ,Integer _idGrp, Integer FromUser, Integer ToUser, String DateMsg, String TextMsg)
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

    public void setChatName(String chatName) {
        ChatName = chatName;
    }

    public String getTextMsg() {
        return TextMsg;
    }

    public void setTextMsg(String textMsg) {
        TextMsg = textMsg;
    }

    public Integer get_idGrp() {
        return _idGrp;
    }

    public void set_idGrp(Integer _idGrp) {
        this._idGrp = _idGrp;
    }

    public String getDateMsg() {
        return DateMsg;
    }

    public void setDateMsg(String dateMsg) {
        DateMsg = dateMsg;
    }

    public Integer getToUser() {
        return ToUser;
    }

    public void setToUser(Integer toUser) {
        ToUser = toUser;
    }

    public Integer getFromUser() {
        return FromUser;
    }

    public void setFromUser(Integer fromUser) {
        FromUser = fromUser;
    }

    public String getProfImage() {
        return ProfImage;
    }

    public String getAnswer() {
        return answer;
    }
}

