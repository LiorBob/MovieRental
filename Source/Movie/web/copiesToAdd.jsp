<%-- 
    Document   : copiesToAdd
    Created on : Mar 5, 2008, 12:32:13 PM
    Author     : Lior
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ArrayList"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add copies</title>
        
        <script language="javascript">
            
            function isNumber(dvdCopiesToAdd, videoCopiesToAdd)
            {
                var intRegex = /^(0|[1-9][0-9]*)$/; 	
   
                if (intRegex.test(dvdCopiesToAdd.value) == false)
                {
                    alert('dvdCopiesToAdd must have a positive integer value');
                    dvdCopiesToAdd.focus();
                    
                    return (false);
                }
	
                if (intRegex.test(videoCopiesToAdd.value) == false)
                {
                    alert('videoCopiesToAdd must have a positive integer value');
                    videoCopiesToAdd.focus();
                    
                    return (false);
                }
	
                return (true);
	
            }
            
        </script>

    </head>
    <body>
        
        <jsp:include page = "topmostLinks.jsp" flush = "true" />
        
        <h2><span style="color:blue"><div style="text-align:center;">Add copies</div></span></h2>
        <hr>

        
        <form action = "/Movie/AddCop" 
              onsubmit="return isNumber(this.dvdCopiesToAdd, this.videoCopiesToAdd)">
        
        <br>
        <span style="color:green">Select movie :</span>
        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        
        <!-- here , select tag is a combo box displaying movies titles -->
        <select name="selectedMovieTitle">
        
        <%
            ArrayList<String> moviesTitles;
            moviesTitles = (ArrayList<String>) request.getSession().getAttribute("moviesTitles");

            for (int i = 0; i < moviesTitles.size(); i++)
            {
        %>
        
                <option> <%= moviesTitles.get(i) %>
                
        <%
            }
        %>
        
        </select>
        
        
        <br><br><br><br>
        <span style="color:green">DVD copies to add : </span>&nbsp; 
        <input type="text" name="dvdCopiesToAdd" value="0" size="13" />
        
        <br><br><br><br>
        <span style="color:green">Video copies to add :&nbsp;</span> 
        <input type="text" name="videoCopiesToAdd" value="0" size="13" />
        
        <br><br><br>
        <div style="text-align:center;"><input type = "submit" value="Add copies" /></div>
        
        </form>
        
            
    </body>
</html>
