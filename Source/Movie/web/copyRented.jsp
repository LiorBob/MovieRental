<%-- 
    Document   : copyRented
    Created on : Mar 4, 2008, 1:01:26 AM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Copy Rented</title>
    </head>
    <body>

        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <br><br>
        <h2><span style="color:blue"><div style="text-align:center;">An email with the renting details was sent to <%= (String) request.getSession().getAttribute("userEmail") %></div></span></h2>
        
        <br><br>
        <h2><span style="color:blue"><div style="text-align:center;">Movie : <%= (String) request.getSession().getAttribute("movieTitle") %></div></span></h2>
        <h2><span style="color:blue"><div style="text-align:center;">Technology : <%= (String) request.getSession().getAttribute("technologyName") %></div></span></h2>
        <h2><span style="color:blue"><div style="text-align:center;">Copy ID : <%= (Integer) request.getSession().getAttribute("rentedCopyID") %></div></span></h2>
        <h2><span style="color:blue"><div style="text-align:center;">Return time : &nbsp;&nbsp;<%= (String) request.getSession().getAttribute("returnTime") %></div></span></h2>
        
    </body>
</html>