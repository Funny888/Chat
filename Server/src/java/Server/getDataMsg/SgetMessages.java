/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.getDataMsg;

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
@WebServlet ("/accountMsg")
public class SgetMessages extends HttpServlet{

    AccountMsg msg = new AccountMsg();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
       
        try {
            resp.getWriter().write(msg.getMsg(Integer.parseInt(req.getParameter("_idUser"))).toJSONString()); 
//            resp.getWriter().write("{text:" + msg.array.toString() +"}\n"); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SgetMessages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SgetMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
 
            
       
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().write("<html>"
                + "<head>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> </head>"
                + "<body>"
                + "<form name='reg' method='post'>"
                + "<input type='textarea' name='_idUser'/>"
                + "<input type='submit' name='send'/>"
                + "</form>"
                + "</body>"
                + "</html>");
    }
    
    
}
