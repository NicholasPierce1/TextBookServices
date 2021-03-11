<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        h1{
            display: block;
            margin: auto;
            margin-bottom: 30px;
            width: 17%;
            color: ForestGreen;
            text-shadow: 1px 1px 3px grey}
        form{
            display: block;
            margin: auto;
            width: 15%;
            background-color: rgba(34,139,34,.2);
            border-style: ridge;
            border-width: thick;
            border-color: ForestGreen}
        label, input[type="text"]{
            display: block;
            margin: 2px 5px 2px 5px;}
        #loginBtn{
            color: ForestGreen;
            display: block;
            margin: 7px 5px;}
        #loginBtn:hover #loginBtn{background-color: Grey;}
    </style>
</head>
<body>
<h1>Log In:</h1>
<form method="get" action="/supervisorView">
    <label for="uName">Username:</label>
    <input type="text" id="uName" name="uName"/>
    <label for="uPass">Password:</label>
    <input type="text" id="uPass" name="uPass"/>
    <input type="submit" value="Submit" id="loginBtn">
</form>
</body>
</html>