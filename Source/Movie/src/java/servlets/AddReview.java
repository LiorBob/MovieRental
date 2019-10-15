package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.*;

import backEnd.MoviesDataAccess;


public class AddReview extends HttpServlet 
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
        String movieTitle;
        movieTitle = request.getParameter("movieTitle");
        
        String reviewHeadline;
        reviewHeadline = request.getParameter("reviewHeadline"); 
        
        String reviewText;
        reviewText = request.getParameter("reviewText"); 
        
        String selectedGrade;
        selectedGrade = request.getParameter("selectedGrade"); 
        
        int movieID;
        movieID = database.getMovieID(movieTitle);
        
        
        HttpSession session = request.getSession();
        
        UserEntry userEntry ;
        userEntry = (UserEntry) session.getAttribute("userEntry");
        
        int userID;
        userID = userEntry.getUserId();
        
        int rating;
        
        
        try
        {
            rating = Integer.parseInt(selectedGrade);

            
            /* creates ReviewEntry object , to pass it
               to addReview method of MoviesDataAccess */

            ReviewEntry reviewEntry = new ReviewEntry();
            
            reviewEntry.setMovieID(movieID);
            reviewEntry.setUserId(userID);
            reviewEntry.setHeadline(reviewHeadline);
            reviewEntry.setText(reviewText);
            reviewEntry.setRating(rating);
            
            
            /* creates MovieEntry object , to get the
               total number of votes so far and total
               grade so far (not including the new
               review vote) .  */
            
            MovieEntry movieEntry;
            movieEntry = database.getMovieByID(movieID);
                    
            int numOfVotesSoFar;
            numOfVotesSoFar = movieEntry.getNumOfVotes();
            
            double totalGradeSoFar;
            totalGradeSoFar = movieEntry.getTotalGrade();

            
            RequestDispatcher dispatcher;
            
            
            /* the user didn't add a review for the
               same movie  before */
            
            if (! database.didGiveReview(movieID, userID))
            {
                if (database.addReview(reviewEntry, numOfVotesSoFar, totalGradeSoFar))
                {
                    dispatcher = 
                        request.getRequestDispatcher("/reviewAdded.jsp");
            
                    dispatcher.forward(request, response); 
                }
                
                else        // addReview failed
                {
                    String reason = 
                            "Add review failed : " +
                            "database connection problem";
                    
                    session.setAttribute("reason", reason);
                    
                    dispatcher = 
                        request.getRequestDispatcher("/reviewError.jsp");
            
                    dispatcher.forward(request, response); 
                }
            }    
           
            
            /* the user already added review for the
               same movie before */
            
            else        
            {
                String reason = 
                        "You've already added review for " +
                        "this movie .  Your new review " +
                        "was NOT added .";
                    
                session.setAttribute("reason", reason);
                
                dispatcher = 
                    request.getRequestDispatcher("/reviewError.jsp");
            
                dispatcher.forward(request, response); 
            }
            
        }
     
        
        // thrown by parseInt method
        catch (NumberFormatException exception)
        {
            exception.printStackTrace();
        }
                
    }
    
}
