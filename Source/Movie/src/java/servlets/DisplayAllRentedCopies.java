package servlets;

import backEnd.DateHelper;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.UserEntry;

import backEnd.MoviesDataAccess;
import java.util.Properties;


public class DisplayAllRentedCopies extends HttpServlet 
{
    static final long serialVersionUID = 1L;
    int MAX_NUMBER_OF_MOVIES = 3;
    
    MoviesDataAccess database;
    DateHelper dateHelper;

    
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
        
        Properties properties = new Properties();
        
        
        try 
        {
            properties.load(new FileInputStream("c:\\movieSystem.properties"));
        } 
        
        catch (IOException e) 
        {
            
        }
         
        MAX_NUMBER_OF_MOVIES = Integer.parseInt(properties.getProperty("MAX_NUMBER_OF_MOVIES"));
           
        dateHelper = new DateHelper();
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
        HttpSession session = request.getSession();
        
        UserEntry userEntry ;
        userEntry = (UserEntry) session.getAttribute("userEntry");
        
        
        String[] returnTimes = new String[MAX_NUMBER_OF_MOVIES];
        int[] rentedCopyIDs = new int[MAX_NUMBER_OF_MOVIES];
        
        
        /* in order to display the copies , which renting 
           duration elapsed , in a different color */
        
        boolean[] copyDurationElapsed = new boolean[MAX_NUMBER_OF_MOVIES];

        
        /* the following for loop stores each copy ID the
           user has  with its corresponding return time .
           copyDurationElapsed[i] is true iff 
           rentedCopyDurationElapsed returns true . */
        
        for (int i = 0; i < userEntry.getRents().size(); i++)
        {
            long rentTime = userEntry.getRents().get(i).getRentTime();
            
            String returnTime = dateHelper.getReturnTime(rentTime);
            
            rentedCopyIDs[i] = userEntry.getRents().get(i).getCopyId();
            returnTimes[i] = returnTime;
            
            copyDurationElapsed[i] = 
                dateHelper.rentedCopyDurationElapsed(rentTime);
        }  
        

        session.setAttribute("maxMovies", MAX_NUMBER_OF_MOVIES);      
        
        session.setAttribute("rentedCopyIDs", rentedCopyIDs);
        session.setAttribute("returnTimes", returnTimes);
        session.setAttribute("copyDurationElapsed",copyDurationElapsed);

        
        RequestDispatcher dispatcher;
        
        dispatcher = 
            request.getRequestDispatcher("/userAllRentedCopies.jsp");
            
        dispatcher.forward(request, response);         
        
    }

}