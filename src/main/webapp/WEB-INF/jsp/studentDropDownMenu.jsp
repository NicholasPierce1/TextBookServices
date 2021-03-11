<%--
  Created by IntelliJ IDEA.
  User: s528345
  Date: 3/4/2021
  Time: 10:31 AM
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
            <a href="#" class="navbar-brand">Student View</a>

            <!-- For Small Windows -->
            <button class="navbar-toggler" data-toggle="collapse" data-target="#menu">
                <span class="navbar-toggle-icon"></span>
            </button>

            <!-- Shows collapsed navbar to the screen
                - Links will be in js with form submissions
             -->
            <div class="collapse navbar-collapse" id="menu">
                <ul class="navbar-nav">
                    <li onclick="createManualFromForm();" class="nav-item">
                        <!--Links to Checked In/Out View -->
                        <form action="/checkIncheckOut" method="get">
                            <a class="nav-link">Check In/ Check Out</a>
                        </form>
                    </li>
                    <li onclick="createManualFromForm();" class="nav-item">
                        <!--Links to Patron Views
                            - Patron Schedule
                            - Sold Books
                            - Previous Books
                         -->
                        <form action="/patronViews" method="get">
                            <a class="nav-link">Patron Views</a>
                        </form>
                    </li>
                    <li onclick="createManualFromForm();" class="nav-item">
                        <!--Links to Login View -->
                        <form action="/loginPage" method="get">
                            <a class="nav-link">Exit</a>
                        </form>
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
            src = "JS/StudentDropDownMenuHandler.js"></script>
    <script type="text/javascript"
            src = "JS/StudentDropDownMenuAjax.js"></script>

</html>
