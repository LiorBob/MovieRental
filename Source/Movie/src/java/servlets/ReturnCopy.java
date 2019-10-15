package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.UserEntry;

import backEnd.MoviesDataAccess;


public class ReturnCopy extends HttpServlet 
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
        String selectedRentedCopyID;
        selectedRentedCopyID = request.getParameter("selectedRentedCopyID");
      
        int rentedCopyID;
        
        
        try
        {
            rentedCopyID = Integer.parseInt(selectedRentedCopyID);
            
            database.returnCopy(rentedCopyID);
            
            
            /* update userEntry in session , after a manager
               returned the copy of a movie . */
            
            HttpSession session = request.getSession();

            UserEntry userEntry ;
            userEntry = (UserEntry) session.getAttribute("userEntry");

            UserEntry updatedUserEntry;
            updatedUserEntry = database.getUser(userEntry.getEMail());
            
            session.setAttribute("userEntry", updatedUserEntry);
            
            
            String copyReturnedMessage;
            copyReturnedMessage = "Copy ID " + selectedRentedCopyID +
                                    " was successfully returned";
            
            session.setAttribute("copyReturnedMessage", copyReturnedMessage);
            
            RequestDispatcher dispatcher;
                
            dispatcher = 
                request.getRequestDispatcher("/copyReturned.jsp");
            
            dispatcher.forward(request, response); 
            
        }
        
        // thrown by parseInt method
        catch (NumberFormatException exception)
        {
            exception.printStackTrace();
        }
            
    }
    
}
