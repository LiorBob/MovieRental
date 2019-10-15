package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.MovieEntry;
import backEnd.MoviesDataAccess;


public class DisplayMovie extends HttpServlet 
{
    static final long serialVersionUID = 1L;
    static final int DVD = 1;
    static final int VIDEO = 2 ;

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
        
        int movieID;
        movieID = database.getMovieID(movieTitle);
            
        MovieEntry movie;
        movie = database.getMovieByID(movieID);
        
        // dvd and video images to display
        String[] techImages = new String[2];  

        
        // check availability of DVD technology of the movie
        if (database.checkAvailableCopy(movieID, DVD))
        {
            techImages[0] = "dvd_enabled.jpg";
        }
            
        else            
        {
            techImages[0] = "dvd_disabled.jpg";
        }
        
        
        // check availability of Video technology of the movie
        if (database.checkAvailableCopy(movieID, VIDEO))
        {
            techImages[1] = "video_enabled.jpg";
        }
            
        else            
        {
            techImages[1] = "video_disabled.jpg";
        }

        
        HttpSession session = request.getSession();
        
        session.setAttribute("movie", movie);
        session.setAttribute("techImages", techImages);

        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/movieDetails.jsp");
        dispatcher.forward(request, response); 
    }
}
