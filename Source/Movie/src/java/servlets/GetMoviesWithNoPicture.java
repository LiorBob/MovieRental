package servlets;

import java.io.*;
import java.util.ArrayList;         

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.MovieEntry;
import backEnd.MoviesDataAccess;


public class GetMoviesWithNoPicture extends HttpServlet 
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
        ArrayList<MovieEntry> moviesWithNoPicture;
        moviesWithNoPicture = database.getMoviesWithNoPicture();
            
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        session.setAttribute("moviesWithNoPicture", moviesWithNoPicture);
            
        dispatcher = request.getRequestDispatcher("/addPicture.jsp");
        dispatcher.forward(request, response); 
    }
}
