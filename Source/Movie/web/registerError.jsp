<%-- 
    Document   : registerError
    Created on : Mar 7, 2008, 9:09:05 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registration Error</title>
    </head>
    <body>
        <h2><span style="color:red"><div style="text-align:center;">Email &nbsp;<%= (String) request.getSession().getAttribute("email") %>&nbsp; already exists</div></span></h2>
    </body>
</html>