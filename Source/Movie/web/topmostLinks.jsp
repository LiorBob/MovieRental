<%-- 
    Document   : managementLinks
    Created on : Mar 5, 2008, 1:48:10 AM
    Author     : Lior
--%>

 <%@ page import = "myObjects.UserEntry"%>


    <%
        UserEntry userEntry = (UserEntry) request.getSession().getAttribute("userEntry");
    %>

    <a href ="login.jsp">Sign out, <%= userEntry.getEMail() %></a>&nbsp;&nbsp;
    <a href ="userMain.jsp">Back to Main screen</a>&nbsp;&nbsp;
    <a href ="advancedSearch.jsp">Advanced Search</a>&nbsp;&nbsp;
    <a href ="/Movie/DisplayAllRentedCop">All rented copies</a>



    <%-- if the user is manager, display management links --%>  
    <%
        if ((Integer)request.getSession().getAttribute("isManger") == 1) 
        {
    %>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href ="addMovie.jsp">Add movie</a>&nbsp;&nbsp;
            <a href ="/Movie/GetMoviesWithNoPic">Add picture for movie</a>&nbsp;&nbsp;
            <a href ="/Movie/GetTitlesMov">Add copies</a>&nbsp;&nbsp;
            <a href ="/Movie/GetIDsCopiesRen">Return copy</a>&nbsp;&nbsp;
            <a href ="/Movie/GetEmailsUse">Set user as manager</a>
    <%
        }
    %>
