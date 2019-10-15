<%-- 
    Document   : userAllRentedCopies
    Created on : Mar 10, 2008, 2:05:07 PM
    Author     : Lior
    
    Displays all rented copies of the current user ,
    with return dates
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>All rented copies of <%= ((myObjects.UserEntry) request.getSession().getAttribute("userEntry")).getEMail() %></title>
    </head>
    <body>

        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h1>
            <span style="color:blue">
                <div style="text-align:center;">
                    All rented copies of <%= ((myObjects.UserEntry) request.getSession().getAttribute("userEntry")).getEMail() %>
                </div>
            </span>
        </h1>
        
        <hr>        
        <br>
            
            
        <div style="text-align:center;">
            <h2>
                <span style="color:blue"><u>Copy ID</u></span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:blue"><u>Return Time</u></span>
            </h2>    
        </div>            
        

        <%
            int maxMovies = (Integer) request.getSession().getAttribute("maxMovies");

            String[] returnTimes = new String[maxMovies];
            returnTimes = (String[]) request.getSession().getAttribute("returnTimes");
            
            int[] rentedCopyIDs = new int[maxMovies];
            rentedCopyIDs = (int[]) request.getSession().getAttribute("rentedCopyIDs");
            
            boolean[] copyDurationElapsed = new boolean[maxMovies];
            copyDurationElapsed = (boolean[]) request.getSession().getAttribute("copyDurationElapsed");
            
            boolean displayYouMustReturn = false;

                       
            for (int i = 0; i < maxMovies; i++)
            {
                // only non-null messages will be displayed
                if (returnTimes[i] != null)
                {
                    if (copyDurationElapsed[i])
                    {
                        displayYouMustReturn = true;
        %>
        
                        <div style="text-align:center;">
                            <h2>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red"><%= rentedCopyIDs[i] %></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span style="color:red"><%= returnTimes[i] %></span>
                            </h2>    
                        </div>
                    
        <%
                    }
                    
                    else
                    {
        %>        

                        <div style="text-align:center;">
                            <h2>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:blue"><%= rentedCopyIDs[i] %></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span style="color:blue"><%= returnTimes[i] %></span>
                            </h2>    
                        </div>
        
        <%
                    }
                }    
            }
            
            
            /* displays "You must return ..." only when
               there are "RED" messages on screen */
            
            if (displayYouMustReturn)
            {
        %>

                <div style="text-align:center;">
                    <h2>
                        <span style="color:green"><u>You must return the copies in RED , or you can't rent anymore .</u></span>
                    </h2>    
                </div>   
                
        <%
            }
        %>
        
    </body>
</html>