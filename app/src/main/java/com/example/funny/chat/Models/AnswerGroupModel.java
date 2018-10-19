package com.example.funny.chat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerGroupModel {
    @SerializedName("_idGrp")
    @Expose
    private Integer _idGrp;

    @SerializedName("_idMsg")
    @Expose
    private Integer _idMsg;

    public Integer get_idGrp() {
        return _idGrp;
    }

    public Integer get_idMsg() {
        return _idMsg;
    }

    public String getAnswer() {
        return answer;
    }

    @SerializedName("Answer")
    @Expose
    private String  answer;

}
