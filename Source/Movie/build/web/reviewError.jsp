<%-- 
    Document   : reviewError
    Created on : Mar 12, 2008, 1:36:56 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Review Error</title>
    </head>
    <body>

        <jsp:include page = "topmostLinks.jsp" flush = "true" />        

        <h2><span style="color:red"><div style="text-align:center;"><%= (String) request.getSession().getAttribute("reason") %></div></span></h2>
        
    </body>
</html>