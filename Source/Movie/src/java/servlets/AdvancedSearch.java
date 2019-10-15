package servlets;

import java.io.*;
import java.util.ArrayList;         

import javax.servlet.*;
import javax.servlet.http.*;

import myObjects.MovieEntry;
import backEnd.MoviesDataAccess;


public class AdvancedSearch extends HttpServlet 
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
        String keyword;
        keyword = request.getParameter("keyword");
        
        ArrayList<MovieEntry> movies;
        movies = database.searchByKeyword(keyword);
            
        HttpSession session = request.getSession();

        session.setAttribute("displayBy", keyword);
        session.setAttribute("moviesByCriterion", movies);
        session.setAttribute("isFromAdvancedSearch", true);

        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/moviesList.jsp");
        dispatcher.forward(request, response); 
    }
}
