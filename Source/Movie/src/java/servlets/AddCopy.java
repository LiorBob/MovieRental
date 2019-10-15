package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


import backEnd.MoviesDataAccess;


public class AddCopy extends HttpServlet 
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
        String selectedMovieTitle;
        selectedMovieTitle = request.getParameter("selectedMovieTitle");
        
        String dvdCopiesToAdd;
        dvdCopiesToAdd = request.getParameter("dvdCopiesToAdd");
        
        String videoCopiesToAdd;
        videoCopiesToAdd = request.getParameter("videoCopiesToAdd");


        /* 3 parameters to  addCopies method :
           movieId, numOfDvdCopiesToAdd and numOfVideoCopiesToAdd */
        
        int movieID;
        movieID = database.getMovieID(selectedMovieTitle);
        
        int numOfDVDCopiesToAdd;
        int numOfVideoCopiesToAdd;

        
        try
        {
            numOfDVDCopiesToAdd = Integer.parseInt(dvdCopiesToAdd);
            numOfVideoCopiesToAdd = Integer.parseInt(videoCopiesToAdd);
            
            boolean dvdCopiesAddedOK;
            dvdCopiesAddedOK = database.addCopies(movieID, DVD, numOfDVDCopiesToAdd);
            
            boolean videoCopiesAddedOK;
            videoCopiesAddedOK = database.addCopies(movieID, VIDEO, numOfVideoCopiesToAdd);
            
            
            if (dvdCopiesAddedOK && videoCopiesAddedOK)
            {
                String copiesAdditionMessage;
                
                copiesAdditionMessage = 
                    numOfDVDCopiesToAdd + " DVD copies and " +
                    numOfVideoCopiesToAdd + " Video copies of \"" +
                    selectedMovieTitle + "\" were successfully added";
                
                HttpSession session = request.getSession();
                session.setAttribute("copiesAdditionMessage", copiesAdditionMessage);
                
                RequestDispatcher dispatcher;
                
                dispatcher = 
                    request.getRequestDispatcher("/addedCopies.jsp");
            
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
