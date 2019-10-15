<%-- 
    Document   : movieAdded
    Created on : Mar 16, 2008, 5:39:10 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie added</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;"><%= request.getSession().getAttribute("addMovieResult") %></div></span></h2>
        
    </body>
</html>
