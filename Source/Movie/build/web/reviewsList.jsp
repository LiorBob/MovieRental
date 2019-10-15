<%-- 
    Document   : reviewsList
    Created on : Mar 12, 2008, 6:03:24 PM
    Author     : Lior
    
    Displays the reviews list for the movie , which
    title is movieTitle
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ArrayList, myObjects.ReviewEntry"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reviews List</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        

        <h2><span style="color:blue"><div style="text-align:center;"><%= request.getSession().getAttribute("movieTitle") %> reviews</div></span></h2>
        <hr>
         
        <%-- JSP scriptlet to retrieve the reviews list --%>
        <%
            final int MAX_NUMBER_OF_STARS = 5;
        
            ArrayList<ReviewEntry> reviews;
            reviews = (ArrayList<ReviewEntry>) request.getSession().getAttribute("reviews");
           
            for (int i = 0; i < reviews.size(); i++)
            {
        %>      
        
        <br>
        <span style="color:green">User ID : &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>
        <span style="color:blue"><%= reviews.get(i).getUserId() %></span>
        
        <br>
        <span style="color:green">Headline : &nbsp; &nbsp; &nbsp; &nbsp;</span>
        <span style="color:blue"><%= reviews.get(i).getHeadline() %></span>
        
        <br>
        <span style="color:green">Review body : &nbsp;</span>
        <span style="color:blue"><%= reviews.get(i).getText() %></span>
        
        <br>
        <span style="color:green">Rating : &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>


            <%-- displays stars representing user's rating --%>
        
            <%
                for (int j = 1; j <= reviews.get(i).getRating() / 2; j++ )
                {
            %>
                    <IMG SRC="fullstar.gif" align="middle">
            <%
                }
            
                if ((reviews.get(i).getRating() % 2) == 1)
                {
            %>
                    <IMG SRC="halfstar.gif" align="middle">
            <%        
                }
            
                int starsDisplayedSoFar = 
                        reviews.get(i).getRating() / 2  +
                        reviews.get(i).getRating() % 2  ;
            
                for (int j = 1; j <= MAX_NUMBER_OF_STARS - starsDisplayedSoFar; j++)
                {
            %>
                    <IMG SRC="nostar.gif" align="middle">
            <%
                }
            %>
        
        <hr>
            
        <%     
            }
        %>
        
    </body>
</html>
