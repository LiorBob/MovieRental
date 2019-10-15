<%-- 
    Document   : newReview
    Created on : Mar 10, 2008, 10:22:07 PM
    Author     : Lior
    
    displays a blank review textarea  for the specified movie
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add review</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Add review</div></span></h2>
        <hr>
            
        <br>

        <form action = "/Movie/AddRev" method="POST">
            Movie title : &nbsp;&nbsp;&nbsp;<input type="text" readonly="true" name="movieTitle" value="<%= request.getParameter("movieTitle") %>" size="<%= request.getParameter("movieTitle").length() %>" />
            <br><br>
            Headline : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="reviewHeadline" value="" />
            <br><br>
            Text : <textarea wrap="virtual" name="reviewText" rows="5" style="width: 500px; margin-bottom: -2.0em; margin-left: 3.2em"></textarea>
            <br><br><br><br>
            Grade : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
            <select name="selectedGrade">
                
                <% 
                    for (int i = 1; i <= 10; i++)
                    {
                %>
                
                        <option> <%= i %>
                        
                <%        
                    }
                %>
                
            </select>   
            
            <br><br>
            
            <div style="text-align:center">
                <input type="submit" value="Add Review" />
            </div>
        </form>
        
    </body>
</html>
