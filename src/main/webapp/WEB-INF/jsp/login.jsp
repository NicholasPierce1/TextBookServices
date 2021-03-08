<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        .form{
            margin: auto;
            width: 10%
            display: flex;
            flex-wrap: wrap;}
    </style>
</head>
<body>
<h1>Please Log In</h1>
<form>
    <fieldset>
        <p><label>Username:</label></p>
        <p><input type = "text" id = "uName" /></p>
        <p><label>Password:</label></p>
        <p><input type = "text" id = "uPwd" /></p>
    </fieldset>
    <p><button type="button">Login</button></p>
</form>
</body>
</html>