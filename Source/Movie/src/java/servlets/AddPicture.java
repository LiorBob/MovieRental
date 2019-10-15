package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import backEnd.MoviesDataAccess;


public class AddPicture extends HttpServlet 
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
        String picturePath;
        picturePath = request.getParameter("picturePath");
        
        String selectedMovieTitle;
        selectedMovieTitle = request.getParameter("selectedMovieTitle");
        
        int movieID;
        movieID = database.getMovieID(selectedMovieTitle);
            
        database.addPicturePath(picturePath, movieID);

        
        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/userMain.jsp");
        dispatcher.forward(request, response); 
    }
}
