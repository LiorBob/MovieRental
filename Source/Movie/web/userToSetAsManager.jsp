<%-- 
    Document   : userToSetAsManager
    Created on : Mar 6, 2008, 1:22:51 AM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ArrayList"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set user as manager </title>
    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Set user as manager</div></span></h2>
        <hr>

        
        <form action = "/Movie/SetUserAsMan" method="POST">
        <br>
        <span style="color:green">Select user :</span>
        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        
        <!-- here , select tag is a combo box displaying users emails -->
        <select name="selectedUserEmail">
        
        <%
            ArrayList<String> usersEmails;
            usersEmails = (ArrayList<String>) request.getSession().getAttribute("usersEmails");

            for (int i = 0; i < usersEmails.size(); i++)
            {
        %>
        
                <option> <%= usersEmails.get(i) %>
                
        <%
            }
        %>
        
        </select>
        
        
        <br><br><br><br>
        <div style="text-align:center;"><input type = "submit" value="Set user as manager" /></div>
        </form>
            
    </body>
</html>
