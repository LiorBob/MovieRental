package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.UserEntry;

import backEnd.MoviesDataAccess;


public class RegisterNewUser extends HttpServlet 
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
        String email;
        
        email = request.getParameter("registerEmail");

        
        RequestDispatcher rd;
        
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        
        
        // email already exists -  report an error
        if (!database.checkIfEmailExists(email)) 
        {
            rd = request.getRequestDispatcher("/registerError.jsp");
            rd.forward(request, response);            
        }

        else
        {
            UserEntry userEntry = new UserEntry();
        
            // update the new user details in userEntry object
            userEntry.setEMail(email);
            userEntry.setPassword(request.getParameter("registerPassword"));
            userEntry.setFirstName(request.getParameter("registerFirstName"));
            userEntry.setLastName(request.getParameter("registerLastName"));
            userEntry.setAddress(request.getParameter("registerAddress"));        
            userEntry.setPhoneNumber(request.getParameter("registerPhoneNumber"));
        
            // adds the new user (who has just registered)
            database.addUser(userEntry);    
        
            rd = request.getRequestDispatcher("/userRegistered.jsp");
            rd.forward(request, response);
            
        }
        
    }
    
}
