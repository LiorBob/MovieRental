<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Main</title>
    </head>
    
    <body>
        
        <%-- we include topmostLinks.jsp , which displays
             the topmost links on each JSP page
             (except login.jsp) --%>
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        

        <h1><span style="color:blue"><div style="text-align:center;">Select genre</div></span></h1>
        
        <table width="300" height="300" cellpadding="3" cellspacing="0" align="center" border="15">
            <tr>
                <td style="background-color:#00aa00">
                    <ul type="disc">
                        <p><a href ="/Movie/SelectGen?genreName=science fiction"><li>Science fiction</li></a></p>
                        <p><a href ="/Movie/SelectGen?genreName=action"><li>Action</li></a></p>
                        <p><a href ="/Movie/SelectGen?genreName=comedy"><li>Comedy</li></a></p>
                        <p><a href ="/Movie/SelectGen?genreName=drama"><li>Drama</li></a></p>
                        <p><a href ="/Movie/SelectGen?genreName=biography"><li>Biography</li></a></p>
                    </ul>
                </td>
            </tr>
	</table>
         
    </body>
</html>
