<%-- 
    Document   : advancedSearch
    Created on : Mar 16, 2008, 3:01:09 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advanced Search</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Advanced Search</div></span></h2>
        <hr>

        
        <form action = "/Movie/AdvancedSea" method="POST">
            
            <br>
                
            <span style="color:blue">Enter search keyword :</span> &nbsp; &nbsp;
            <input type="text" name="keyword" value="" size="15" />&nbsp; &nbsp; &nbsp;
            <input type = "submit" value="Search" />
            
        </form>
            
    </body>
</html>
