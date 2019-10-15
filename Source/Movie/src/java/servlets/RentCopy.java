package servlets;

import backEnd.DateHelper;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.UserEntry;

import backEnd.MoviesDataAccess;
import java.util.Properties;


public class RentCopy extends HttpServlet 
{
    static final long serialVersionUID = 1L;
    static final int DVD = 1;
    static final int VIDEO = 2 ;
    
    int MAX_NUMBER_OF_MOVIES = 3;
    
    static final int ALREADY_HAS_COPY = -1;
    static final int TOO_MANY_COPIES = -2;
    
    
    
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
            e.printStackTrace();
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
        RequestDispatcher dispatcher;
        
        UserEntry userEntry ;
        userEntry = (UserEntry) session.getAttribute("userEntry");
        
        
        
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
            
            
        /* warn user , that he/she must return copies
           which duration has elapsed */
        
        if (hasCopyToReturn)    
        {
            session.setAttribute("durationElapsedMessages", durationElapsedMessages);
            session.setAttribute("maxMovies", MAX_NUMBER_OF_MOVIES);
                
            dispatcher = request.getRequestDispatcher("/userMustReturnCopy.jsp");
            dispatcher.forward(request, response);
        }
        
        
        /* we reach here if the user has no copy to return ,
           so he/she may rent a copy of the wanted movie */
        
        else
        {
            int userID;
            userID = userEntry.getUserId();
        
            String movieTitle;
            movieTitle = request.getParameter("movieTitle");
        
            int wantedMovieID;
            wantedMovieID = database.getMovieID(movieTitle);
        
            String strTechnologyID;
            strTechnologyID = request.getParameter("technologyID");
        
            int rentedCopyID;
            int rentedMovieID;
        
            boolean userCanRent = true;
        
            // indicates why user can't rent the wanted movie
            int reason = 0;     
        
            int numOfRentedMovies;
            numOfRentedMovies = userEntry.getRents().size();

        
            /* the following code first checks if the user
               can rent additional movie (this is the 
               MAX_NUMBER_OF_MOVIES check), then checks if
               the user already has a copy of the (same) movie
               he/she wants to rent .  The value of userCanRent
               is set  accordingly . */
        
            if (numOfRentedMovies < MAX_NUMBER_OF_MOVIES)
            {
                for (int i = 0; i < numOfRentedMovies; i++)
                {
                    rentedCopyID = userEntry.getRents().get(i).getCopyId();
                    rentedMovieID = database.getMovieIDByCopyID(rentedCopyID);
                
                    // the user already has a copy of same movie
                    if (rentedMovieID == wantedMovieID)   
                    {
                        reason = ALREADY_HAS_COPY;
                        userCanRent = false;
                    
                        break;  // do not check other rents
                    }
                }
            }
        
            else    // another rent should exceed the limit of Max movies
            {
                reason = TOO_MANY_COPIES;
                userCanRent = false;
            }

        
            if (userCanRent)
            {
                int technologyID;
        
                // renting treatment section
                try
                {
                    technologyID = Integer.parseInt(strTechnologyID);
            
                    database.getAndRentAvailableCopy(wantedMovieID, technologyID, userID);
                
                    UserEntry updatedUserEntry;
                    updatedUserEntry = database.getUser(userEntry.getEMail());
                
                    session.setAttribute("userEntry", updatedUserEntry);
                
                    session.setAttribute("userEmail", userEntry.getEMail());
                
                    // to display the movie title just rented
                    session.setAttribute("movieTitle", movieTitle);
                
                
                    if (technologyID == DVD)
                    {
                        session.setAttribute("technologyName", "DVD");
                    }
                
                    else    // technologyID = VIDEO
                    {
                        session.setAttribute("technologyName", "Video");
                    }

                
                    /* to report the user about the copy ID
                       of the movie just rented . */

                    int mostRecentRentedCopyID;
                    mostRecentRentedCopyID = updatedUserEntry.getRents().get(numOfRentedMovies).getCopyId();
                
                    session.setAttribute("rentedCopyID", mostRecentRentedCopyID);
                
                
                    /* to report the user when he/she has to
                       return the copy just rented  :
                       updatedUserEntry.getRents().get(numOfRentedMovies).getRentTime()
                       is the rent time of the most-recently
                       copy rented .  */
                
                    long rentTime;
                    rentTime = updatedUserEntry.getRents().get(numOfRentedMovies).getRentTime();
                
                    String returnTime;
                    returnTime = dateHelper.getReturnTime(rentTime);
                
                    session.setAttribute("returnTime", returnTime);
                
                
                    // sendEmailToUser();
                
                    dispatcher = 
                        request.getRequestDispatcher("/copyRented.jsp");
            
                    dispatcher.forward(request, response); 
                }

                // thrown by parseInt method
                catch (NumberFormatException exception)
                {
                    exception.printStackTrace();
                }
        
            }
        
            else    // notify user why can't rent the wanted movie
            {
                String strReason = "";
            
                if (reason == ALREADY_HAS_COPY)
                {
                    strReason = userEntry.getEMail() + " : " +
                                "You already have a copy of " +
                                movieTitle;
                }
            
                if (reason == TOO_MANY_COPIES)
                {
                    strReason = userEntry.getEMail() + " : " +
                                "You can not rent more than " +
                               MAX_NUMBER_OF_MOVIES + 
                              " movies at a time";
                }
            
                session.setAttribute("reason", strReason);
            
            
                dispatcher = 
                    request.getRequestDispatcher("/rentError.jsp");
            
                dispatcher.forward(request, response); 
            }
        
        }
        
    }
}
