package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.MovieEntry;

import backEnd.MoviesDataAccess;


public class DisplayAllReviews extends HttpServlet 
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
        
        int movieID;
        movieID = database.getMovieID(movieTitle);
            
        MovieEntry movie;
        movie = database.getMovieByID(movieID);
        
        
        HttpSession session = request.getSession();

        
        /* passes all the reviews for the movie, which
           title is movieTitle, to reviewsList.jsp */
        
        session.setAttribute("movieTitle", movieTitle);
        session.setAttribute("reviews", movie.getReviews());
        
        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/reviewsList.jsp");
        dispatcher.forward(request, response); 
    }
}
