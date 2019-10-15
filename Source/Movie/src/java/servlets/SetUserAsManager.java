package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


import backEnd.MoviesDataAccess;


public class SetUserAsManager extends HttpServlet 
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
        String selectedUserEmail;
        selectedUserEmail = request.getParameter("selectedUserEmail");
      
        database.setUserManger(selectedUserEmail);
        
        String userIsNowManagerMessage;
        userIsNowManagerMessage = selectedUserEmail +
                                    " is now manager";
        
        HttpSession session = request.getSession();
        session.setAttribute("userIsNowManagerMessage", userIsNowManagerMessage);
                
        
        RequestDispatcher dispatcher;
                
        dispatcher = 
            request.getRequestDispatcher("/userIsNowManager.jsp");
            
        dispatcher.forward(request, response); 
            
    }
    
}
