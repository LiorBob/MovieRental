<%-- 
    Document   : addPicture
    Created on : Mar 16, 2008, 8:09:05 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.util.ArrayList, myObjects.MovieEntry"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Picture for movie</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Add Picture for movie</div></span></h2>
        <hr>

        
        <form action = "/Movie/AddPic" method="POST">
        
            <br>
            <span style="color:green">Select movie :</span>
        
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
            <!-- here , select tag is a combo box displaying movies titles -->
            <select name="selectedMovieTitle"> 
        
            <%
                ArrayList<MovieEntry> moviesWithNoPicture;
                moviesWithNoPicture = (ArrayList<MovieEntry>) request.getSession().getAttribute("moviesWithNoPicture");

                for (int i = 0; i < moviesWithNoPicture.size(); i++)
                {
            %>
        
                    <option> <%= moviesWithNoPicture.get(i).getTitle() %>
                
            <%
                }
            %>
        
            </select>
        
            <br>
            
            <span style="color:green">Enter picture name for the selected movie :</span> &nbsp; &nbsp;    
            <input type="text" name="picturePath" value="" size="15" />&nbsp; &nbsp; &nbsp;
            <input type = "submit" value="Add Picture" />

        </form>   
        
    </body>
</html>
