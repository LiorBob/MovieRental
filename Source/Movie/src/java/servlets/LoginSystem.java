package servlets;

import backEnd.DateHelper;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import backEnd.MoviesDataAccess;

import java.io.FileInputStream;
import java.util.Properties;
import myObjects.UserEntry;


/**
 * Servlet implementation class for Servlet: testServlet
 *
 */
public class LoginSystem extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
    static final long serialVersionUID = 1L;
   
    int MAX_NUMBER_OF_MOVIES = 3;
    
    MoviesDataAccess database;
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    DateHelper dateHelper;
    
    
    public void init() 
    {
        database = null;
        
        try 
        {
            database = new MoviesDataAccess();
        } 
        
        catch (Exception e1) 
        {

            e1.printStackTrace();
        }
        
        Properties properties = new Properties();
            
        try 
        {
            properties.load(new FileInputStream("c:\\movieSystem.properties"));
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
         
        MAX_NUMBER_OF_MOVIES = Integer.parseInt(properties.getProperty("MAX_NUMBER_OF_MOVIES"));
        dateHelper = new DateHelper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request, response);
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String email;
        String password;

        email = request.getParameter("logInEmail");
        password = request.getParameter("logInPassword");
        
        RequestDispatcher rd;

        
        if (database.checkIfEmailExists(email)) 
        {
            rd = request.getRequestDispatcher("/logInError.jsp");
            rd.forward(request, response);
        } 
        
        else if (!database.getPassword(email).equals(password)) 
        {
            rd = request.getRequestDispatcher("/wrongPassword.jsp");
            rd.forward(request, response);
        } 
        
        else        // Login OK
        {
            HttpSession session = request.getSession();
            
            UserEntry userEntry = database.getUser(email);
            session.setAttribute("userEntry", userEntry);

            
            if (database.checkIfUserManger(email)) 
            {
                session.setAttribute("isManger", (Integer) 1);
            } 
            
            else 
            {
                session.setAttribute("isManger", (Integer) 0);
            }

            
            String[] durationElapsedMessages = new String[MAX_NUMBER_OF_MOVIES];

            boolean hasCopyToReturn = false;
            
            
            /* checks whether the user has (rented) a 
               copy of movie , which duration time elapsed */
            
            for (int i = 0; i < userEntry.getRents().size(); i++)
            {
                long rentTime = userEntry.getRents().get(i).getRentTime();
                
                if (dateHelper.rentedCopyDurationElapsed(rentTime))
                {
                    durationElapsedMessages[i] =
                        "You must return copy No. " + 
                        userEntry.getRents().get(i).getCopyId() +
                        " NOW !!!";
                    
                    hasCopyToReturn = true;
                }
            }
            
            
            if (hasCopyToReturn)
            {
                session.setAttribute("durationElapsedMessages", durationElapsedMessages);
                session.setAttribute("maxMovies", MAX_NUMBER_OF_MOVIES);
                
                rd = request.getRequestDispatcher("/userMustReturnCopy.jsp");
                rd.forward(request, response);
            }
            
            else
            {
                rd = request.getRequestDispatcher("/userMain.jsp");
                rd.forward(request, response);
            }

        }
    }
}
