package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import backEnd.XmlOperations;


public class AddMovie extends HttpServlet 
{
    static final long serialVersionUID = 1L;
    XmlOperations xmlOperations;

    
    public void init() 
    {
        xmlOperations = null;
       
        try 
        {
            xmlOperations = new XmlOperations();
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
        String xmlFileName;
        xmlFileName = request.getParameter("xmlFileName");
        
        String addMovieResult;
        addMovieResult = xmlOperations.putInDB(xmlFileName);
            

        HttpSession session = request.getSession();

        session.setAttribute("addMovieResult", addMovieResult);

        RequestDispatcher dispatcher;
            
        dispatcher = request.getRequestDispatcher("/movieAdded.jsp");
        dispatcher.forward(request, response); 
    }
}
