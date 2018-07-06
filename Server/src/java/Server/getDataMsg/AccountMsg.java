/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.getDataMsg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.crypto.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class AccountMsg {
    
    JSONObject obj = new JSONObject();
   AccountData data = new AccountData();
    private ArrayList<String> array = new ArrayList<>();
   
  static final String NameDB="jdbc:mysql://localhost:3306/ChatDB",LoginDB="Funny",PasswordDB="Smeshnoy888";
    
    public JSONObject getMsg(Integer _id) throws ClassNotFoundException, SQLException {
       
        
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(NameDB,LoginDB,PasswordDB);
        
        Statement state = connect.createStatement();
        String query = "select * from Messages where _idGrp=(select _idGrp from ChatGrp where _idUser="+ _id +")";
        ResultSet result = state.executeQuery(query);
        
        
        while(result.next())
        {
            data.setIdMsg(result.getString("_idMsg"));
            data.setIdGrp(result.getString("_idGrp"));
            data.setFromUser(result.getString("FromUser"));
            data.setToUser(result.getString("ToUser"));
            data.setDateMsg(result.getString("DateMsg"));
            data.setTextMsg(result.getString("TextMsg"));
            
            array.add(data.getIdMsg());
            array.add(data.getIdGrp());
            array.add(data.getFromUser());
            array.add(data.getToUser());
            array.add(data.getDateMsg());
            array.add(data.getTextMsg());
         
        }
        obj.put("user:" + String.valueOf(_id), array);
        return obj;
    }
   
}
