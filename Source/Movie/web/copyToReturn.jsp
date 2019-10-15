<%-- 
    Document   : copyToReturn
    Created on : Mar 6, 2008, 1:47:05 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ArrayList"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Return Copy</title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Return copy</div></span></h2>
        <hr>

        
        <form action = "/Movie/ReturnCop" method="POST">
        <br>
        <span style="color:green">Select copy :</span>
        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        
        <!-- here , select tag is a combo box displaying rented copies IDs -->
        <select name="selectedRentedCopyID">
        
        <%
            ArrayList<Integer> rentedCopyIDs;
            rentedCopyIDs = (ArrayList<Integer>) request.getSession().getAttribute("rentedCopyIDs");

            for (int i = 0; i < rentedCopyIDs.size(); i++)
            {
        %>
        
                <option> <%= rentedCopyIDs.get(i) %>
                
        <%
            }
        %>
        
        </select>
        
        
        <br><br><br><br>
        <div style="text-align:center;"><input type = "submit" value="Return copy" /></div>
        </form>
            
    </body>
</html>
