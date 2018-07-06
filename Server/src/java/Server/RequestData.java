/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import com.sun.istack.internal.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *s
 * @author Funny
 */
public class RequestData {
    private final String loginDB="Funny",passwordDB="Smeshnoy888",DB="jdbc:mysql://localhost:3306/ChatDB";
    public Person request(String log,String pass) throws SQLException, ClassNotFoundException
    {   Person persona = new Person();
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection con = DriverManager.getConnection(DB,loginDB,passwordDB);
    
        Statement state = con.createStatement();
        String querySelect = "SELECT ProfImage,Login,Password,Name,Family,Patronymic,e_mail FROM Users";
        
        ResultSet result = state.executeQuery(querySelect);
        while(result.next())
        {
            if(log.equals(result.getString("Login")) && pass.equals(result.getString("Password")))
            {
            persona.setLogin(result.getString("Login"));
            persona.setPassword(result.getString("Password"));
            persona.setName(result.getString("Name"));
            persona.setFamily(result.getString("Family"));
            persona.setPatronymic(result.getString("Patronymic"));
            persona.setImageFrofile(result.getString("ProfImage"));
            persona.setE_mail(result.getString("e_mail"));
                System.out.println(result.getString("login") + result.getString("password"));
                
            }
        }
        
        return persona;
    }
     public Person createPerson(
             @Nullable String ProfImage,
             String Name,
             @Nullable String Family,
             @Nullable String Patronymic,
             String log,
             String pass,
             String e_mail) throws ClassNotFoundException, SQLException
    {
        Person createdPerson = new Person();
        
        Class.forName("com.mysql.jdbc.Driver");
        
        java.sql.Connection connect = DriverManager.getConnection(DB,loginDB,passwordDB);
        
        try {
            java.sql.Statement stat = connect.createStatement();
          String insert = "INSERT INTO Users(ProfImage,Name,Family,Patronymic,Login,Password,e_mail) VALUES('" + ProfImage + "','"
                                                                                                              + Name + "','"
                                                                                                              + Family + "','"
                                                                                                              + Patronymic + "','"
                                                                                                              + log + "','"
                                                                                                              + pass + "','"
                                                                                                              + e_mail +"')";
          stat.executeUpdate(insert);
            System.out.println(insert);
            
        } catch (Exception e) {
        }
        
        
        
        
        return createdPerson; 
    }
    
}
