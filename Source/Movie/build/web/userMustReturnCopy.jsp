<%-- 
    Document   : userMustReturnCopy
    Created on : Mar 9, 2008, 10:57:53 AM
    Author     : Lior
    
    This JSP displays the "You must return ..." messages
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>User must return copy</title>
    </head>
    <body>

        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <bgsound SRC="ambulance.au" loop=infinite></bgsound>
        
        <br><br>

        <%
            int maxMovies = (Integer) request.getSession().getAttribute("maxMovies");

            String[] durationElapsedMessages = new String[maxMovies];
            durationElapsedMessages = (String[]) request.getSession().getAttribute("durationElapsedMessages");
            
            for (int i = 0; i < maxMovies; i++)
            {
                // only non-null messages will be displayed
                if (durationElapsedMessages[i] != null)
                {
        %>          
                    <marquee behavior="alternate" scrollamount="20">
                        <h1><span style="color:red"><%= durationElapsedMessages[i] %></span></h1>
                    </marquee>
        <%
                }
            }
        %>
        
    </body>
</html>