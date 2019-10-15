<%-- 
    Document   : moviesList
    Created on : Feb 29, 2008, 2:02:24 AM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*, myObjects.MovieEntry"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Movies List</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        
        <!-- The following line displays  "Comedy movies"
             or "Drama movies" or "Spielberg movies" ,
             according to the session attribute (parameter)
             passed by the appropriate servlets . 
             If we did advanced search , we'll see
             "Movies containing ..." message instead. -->

        <% 
            if (! (Boolean) request.getSession().getAttribute("isFromAdvancedSearch"))
            {
        %>
        
                <h2><span style="color:blue"><div style="text-align:center;"> <%= request.getSession().getAttribute("displayBy") %> movies</div></span></h2>
                
        <%        
            }    
            
            else
            {
        %>
        
                <h2><span style="color:blue"><div style="text-align:center;">Movies containing "<%= request.getSession().getAttribute("displayBy") %>"</div></span></h2>
                
        <%
            }
        %>
        
        <hr>
         
         
        <%-- JSP scriptlet to retrieve movies brief details --%>
        <%
           ArrayList<MovieEntry> movies;
           movies = (ArrayList<MovieEntry>) request.getSession().getAttribute("moviesByCriterion");
           
           MovieEntry currentMovie;
           Iterator moviesIterator = movies.iterator();
           
           while(moviesIterator.hasNext())
           {
                currentMovie = (MovieEntry) moviesIterator.next();
        %>      
        
        <br>
        <span style="color:green">Title : &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>

        <!-- title of the movie is a link for further details
             about the move. -->
        <a href ="/Movie/DisplayMov?movieTitle=<%= currentMovie.getTitle() %>">
            <%= currentMovie.getTitle() %>
        </a>

        <br>
        <span style="color:green">Directors : &nbsp;</span>
        <span style="color:red"><%= currentMovie.getDirectors() %></span>
             
        <br>
        <span style="color:green">Actors : &nbsp; &nbsp; &nbsp;</span>         
        <span style="color:red"><%= currentMovie.getActors() %></span>     
        <hr>
            
        <%     
           }
        %>
        
    </body>
</html>
