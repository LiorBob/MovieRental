<%-- 
    Document   : register
    Created on : Mar 7, 2008, 6:13:09 PM
    Author     : Lior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New user registration</title>
    </head>
    <body>
        
        <h2><span style="color:blue"><div style="text-align:center;">New user registration</div></span></h2>
        <hr>
    
    
        <form action = "/Movie/RegisterNewUse" method="POST">
            Email : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="registerEmail" value="" size="14" /><br/><br/>
            Password : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="registerPassword" value="" size="16" /><br/><br/>
            First Name : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="registerFirstName" value="" size="15" /><br/><br/>
            Last Name : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="registerLastName" value="" size="15" /><br/><br/>
            Address : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="registerAddress" value="" size="15" /><br/><br/>
            Phone Number : &nbsp;<input type="text" name="registerPhoneNumber" value="" size="15" /><br/><br/>
            <div style = "text-align:center;"><input type = "submit" value="Register" /></div>
        </form>            
            
    </body>
</html>
