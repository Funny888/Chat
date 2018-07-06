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

@WebServlet ("/createPerson")
public class ScreatePerson extends HttpServlet{

    RequestData rec = new RequestData();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            rec.createPerson(
                    req.getParameter("ProfImage"),req.getParameter("Name"),
                    req.getParameter("Family"),req.getParameter("Patronymic"),
                    req.getParameter("Login"),req.getParameter("Password"),
                    req.getParameter("e_mail"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScreatePerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScreatePerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        resp.getWriter().write("<html>"
                + "<head>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> </head>"
                + "<body>"
                + "<form name='reg' method='post'>"
                + "ProfImage \n"
                + "<input type='text' name='ProfImage'/> \n" 
                + "*Name \n"
                + "<input type='text' name='Name'/> \n"
                + "Family \n"
                + "<input type='text' name='Family'/> \n"
                + "Patronymic \n"
                + "<input type='text' name='Patronymic'/> \n"
                + "*Login \n"
                + "<input type='text' name='Login'/> \n"
                + "*Password \n"
                + "<input type='text' name='Password'/>"
                + "*e_mail \n"
                + "<input type='text' name='e_mail'/> \n"
                + "<input type='submit' name='send'/>"
                + "</form>"
                + "</body>"
                + "</html>");
    }
    
    
}
