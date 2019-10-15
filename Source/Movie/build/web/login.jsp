<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
    <span style="color:blue">
        <div align = "center">New user ? &nbsp; 
            <a href="register.jsp">Register</a>
        </div>
    </span>
    <br>
    <hr>
    
    <br><br>
    <span style="color:blue">
        <div align = "center">&nbsp;&nbsp;Existing user
            <br>
            <img src = "arrow.gif">
        </div>
    </span>
    
    <form action = "/Movie/LoginSys" method="POST">
        <div align = "center">
            Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="logInEmail" value="" size="14" /><br/><br/>
            Password: &nbsp;&nbsp;<input type="password" name="logInPassword" value="" size="15" /><br/><br/>
            <input type = "submit" value="Login" />
        </div>
    </form>
    
</body>
</html>

