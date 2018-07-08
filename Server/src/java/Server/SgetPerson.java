/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Funny
 */
@WebServlet ("/getPerson")
public class SgetPerson extends HttpServlet{

    RequestData reqData = new RequestData();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    
        try {
            Person persona = reqData.request(req.getParameter("Login"), req.getParameter("Password"));
            resp.getWriter().write("{Name:" + persona.getName() + ",\nFamily:" + persona.getFamily() +
                    ",\nPatronymic:" + persona.getPatronymic() + ",\nLogin:" + persona.getLogin() + ",\nPassword:" + persona.getPassword() +
                    ",\ne_mail:" + persona.getE_mail()+ ",\nProfImage:" + "'" + persona.getImageFrofile() + "'" + "}");
            
           } catch (SQLException ex) {
            Logger.getLogger(SgetPerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SgetPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
  
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
          resp.getWriter().write("<html>"
                + "<head>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> </head>"
                + "<body>"
                + "<form name='reg' method='post'>"
                + "Логин \n"
                + "<input type='text' name='Login'/> \n" 
                + "Пароль \n"
                + "<input type='text' name='Password'/>"
                + "<input type='submit' name='send'/>"
                + "</form>"
                + "</body>"
                + "</html>");
    }
    
    
    
    
}
