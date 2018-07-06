/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;

/**
 *
 * @author Funny
 */
public class Main {

    public static void main(String[] voids) throws ClassNotFoundException {
        
        
       JSONObject json = new JSONObject();
        
        String url = "jdbc:mysql://localhost:3306/ChatDB";
        String login = "Funny" , password = "Smeshnoy888";
        String l= "test",p = "test";
        try{
            Class.forName("com.mysql.jdbc.Driver");
             Connection connect = DriverManager.getConnection(url, login, password);
            
            Statement state = connect.createStatement();
            String request = "SELECT ProfImage,Login,Password,Name,Family,Patronymic,e_mail FROM Users ";
            
            System.out.println(request);
            
            ResultSet result = state.executeQuery(request);
            
            while(result.next())
            {
                json.put("login",result.getString("Login"));
                json.put("password",result.getString("password"));
                json.put("Name",result.getString("Name"));
                json.put("Family",result.getString("Family"));
                json.put("Patronymic",result.getString("Patronymic"));
                json.put("ProfImage",result.getString("ProfImage"));
                json.put("e_mail",result.getString("e_mail"));
                
            }
            System.out.println(json.toJSONString());
        }
        catch (SQLException e)
        {
            System.out.println(e.getSQLState());
            System.out.println("oshibka");
        }
        
    }
    
    
    
}
