package servlets;

import java.io.IOException;
import java.util.ArrayList;         

import javax.servlet.*;
import javax.servlet.http.*;

import backEnd.MoviesDataAccess;


public class GetEmailsUsers extends HttpServlet 
{
    static final long serialVersionUID = 1L;
    MoviesDataAccess database;

    
    public void init() 
    {
        database = null;
       
        try 
        {
            database = new MoviesDataAccess();
        } 

        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request, response);
    } 

    
    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        ArrayList<String> usersEmails;
        usersEmails = database.getUsersEmails();
        
        HttpSession session = request.getSession();

        session.setAttribute("usersEmails", usersEmails);

        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/userToSetAsManager.jsp");
        dispatcher.forward(request, response); 
    }
}
