<%-- 
    Document   : movieDetails
    Created on : Mar 1, 2008, 2:23:22 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.util.ArrayList, myObjects.MovieEntry"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Movie Details</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />


        <%-- displays movie title (name) .
             MAX_NUMBER_OF_STARS is for total grade ,
             to fill the rest of the stars (till
             MAX_NUMBER_OF_STARS) with blank stars . --%>
             
        <%
           final int MAX_NUMBER_OF_STARS = 5;
        
           MovieEntry movie;
           movie = (MovieEntry) request.getSession().getAttribute("movie");
        %>      

        <h2><span style="color:blue"><div style="text-align:center;"><%= movie.getTitle() %></div></span></h2>
        <hr>
        
        <!-- displays the poster of the movie -->
        <br>
        <IMG SRC="<%= movie.getPicturePath() %>">&nbsp; &nbsp; &nbsp;   
        
        
        <%-- Java code which determines whether the dvd/video
             picture has a link (if dvd/video copy is available)
             or not  (if dvd/video copy is unavailable) . --%>
        <%
            String[] techImages = new String[2];
            techImages = (String[]) request.getSession().getAttribute("techImages");

            if (techImages[0].equals("dvd_enabled.jpg"))
            {
        %>
                <!-- DVD enabled picture with link -->
                <a href ="/Movie/RentCop?movieTitle=<%= movie.getTitle() %>&technologyID=1"><IMG SRC="<%= techImages[0] %>" alt="Rent!"></a>&nbsp;
                
        <%
            }

            else
            {
        %>
                <!-- DVD disabled picture -->
                <IMG SRC="<%= techImages[0] %>" alt="no available copy">&nbsp;
                
        <%
            }

            if (techImages[1].equals("video_enabled.jpg"))
            {
        %>
                <!-- Video enabled picture with link -->
                <a href ="/Movie/RentCop?movieTitle=<%= movie.getTitle() %>&technologyID=2">
                    <IMG SRC="<%= techImages[1] %>" alt="Rent!">
                </a>
        <%
            }

            else
            {
        %>
                <!-- Video disabled picture -->
                <IMG SRC="<%= techImages[1] %>" alt="no available copy">
                
        <%
            }
        %>
       

        <br>
        <span style="color:green">Genres : &nbsp; &nbsp; &nbsp;</span>
        
        <% 
            ArrayList<String> genres = movie.getGenres();
            
            for (int i = 0; i < genres.size(); i++)
            {
        %>     

                <a href = "/Movie/SelectGen?genreName=<%= genres.get(i) %>"><%= genres.get(i) %></a>&nbsp;
                
        <%
            }
        %>


        <br>
        <span style="color:green">Actors : &nbsp; &nbsp; &nbsp;</span>

        <% 
            ArrayList<String> actors = movie.getActors();
            
            for (int i = 0; i < actors.size(); i++)
            {
        %>     
  
                <a href = "/Movie/SelectAct?actorName=<%= actors.get(i) %>"><%= actors.get(i) %></a>&nbsp;
                
        <%
            }
        %>


        <br>
        <span style="color:green">Directors : &nbsp;</span>        

        <% 
            ArrayList<String> directors = movie.getDirectors();
            
            for (int i = 0; i < directors.size(); i++)
            {
        %>     
  
                <a href = "/Movie/SelectDir?directorName=<%= directors.get(i) %>"><%= directors.get(i) %></a>&nbsp;
                
        <%
            }
        %>

        
        <br>
        <span style="color:green">Length : &nbsp; &nbsp; &nbsp;</span>        
        <span style="color:red"><%= movie.getLength() %></span>

        <br>
        <span style="color:green">Synopsis : &nbsp;</span>        
        <span style="color:red"><%= movie.getSynopsis() %></span>
        
        <br><br>
        <span style="color:green">Number of votes: &nbsp;</span>        
        <span style="color:red"><%= movie.getNumOfVotes() %></span>

        <br>
        <span style="color:green">Total grade: &nbsp;</span>

        
        <%-- displays stars representing total grade --%>
        
        <%
            for (int i = 1; i <= (int)movie.getTotalGrade() / 2; i++ )
            {
        %>
                <IMG SRC="fullstar.gif" align="middle">
        <%
            }
            
            if (((int)movie.getTotalGrade() % 2) == 1)
            {
        %>
                <IMG SRC="halfstar.gif" align="middle">
        <%        
            }
            
            int starsDisplayedSoFar = 
                (int) movie.getTotalGrade() / 2  +
                (int) movie.getTotalGrade() % 2  ;
            
            for (int i = 1; i <= MAX_NUMBER_OF_STARS - starsDisplayedSoFar; i++)
            {
        %>
                <IMG SRC="nostar.gif" align="middle">
        <%
            }
        %>
            
        
        <br><br>
        <a href = "newReview.jsp?movieTitle=<%= movie.getTitle() %>">Add Review</a>&nbsp;&nbsp;&nbsp;
        <a href = "/Movie/DisplayAllRev?movieTitle=<%= movie.getTitle() %>">All reviews for <%= movie.getTitle() %></a>

    </body>
</html>
