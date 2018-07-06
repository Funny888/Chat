/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.getDataMsg;

/**
 *
 * @author Funny
 */
public class AccountData {
   private String _idMsg; 
   private String _idGrp;
   private String FromUser; 
   private String ToUser; 
   private String DateMsg;
   private String TextMsg;

    public String getIdMsg() {
        return _idMsg;
    }

    public void setIdMsg(String _idMsg) {
        this._idMsg = _idMsg;
    }

    public String getIdGrp() {
        return _idGrp;
    }

    public void setIdGrp(String _idGrp) {
        this._idGrp = _idGrp;
    }

    public String getFromUser() {
        return FromUser;
    }

    public void setFromUser(String FromUser) {
        this.FromUser = FromUser;
    }

    public String getToUser() {
        return ToUser;
    }

    public void setToUser(String ToUser) {
        this.ToUser = ToUser;
    }

    public String getDateMsg() {
        return DateMsg;
    }

    public void setDateMsg(String DateMsg) {
        this.DateMsg = DateMsg;
    }

    public String getTextMsg() {
        return TextMsg;
    }

    public void setTextMsg(String TextMsg) {
        this.TextMsg = TextMsg;
    }
}
