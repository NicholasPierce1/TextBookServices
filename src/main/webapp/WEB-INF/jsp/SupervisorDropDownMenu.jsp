<%--
  Created by IntelliJ IDEA.
  User: s528345
  Date: 3/4/2021
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Student View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-sm navbar dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="/img/NorthwestLogo.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Supervisor View
    </a>

    <!-- For Small Windows -->
    <button class="navbar-toggler" data-toggle="collapse" data-target="#menu">
        <span class="navbar-toggle-icon"></span>
    </button>

    <!-- Shows collapsed navbar to the screen
        - Links will be in js with form submissions
     -->
    <div class="collapse navbar-collapse" id="menu">
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Inventory
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Maintenance</a>
                    <a class="dropdown-item" href="#">Add Books</a>
                    <a class="dropdown-item" href="#">Query Book</a>
                    <!--
                        Link to Change Book Info
                            - Change Book Disposition
                            - Replace Barcode
                            - Change Book Code Year
                    -->
                    <a class="dropdown-item" href="#">Change Book Info</a>
                    <a class="dropdown-item" href="#">Query Course</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Patron
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Check In / Check Out</a>
                    <!--
                        Link to Patron Info
                            - Patron Schedule
                            - Previous Books
                            - Sold Books
                    -->
                    <a class="dropdown-item" href="#">Patron Info</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous">
</script>
<script type="text/javascript"
        src = "JS/ShardHandler.js"></script>
<script type="text/javascript"
        src = "JS/SupervisorDropDownMenuHandler.js"></script>
<script type="text/javascript"
        src = "JS/SupervisorDropDownMenuAjax.js"></script>

</html>