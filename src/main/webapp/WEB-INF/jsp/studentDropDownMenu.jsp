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
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #18523c;">
                    <img src="NorthwestLogo.png" width="50" height="50" class="d-inline-block align-top" alt="">
                    <a class="navbar-brand" href="#"></a>
                        <span class="navbar-brand mb-0 h1">Student View</span>
                    </a>
                    <!-- For Small Windows -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Shows collapsed navbar to the screen
                                    - Links will be in js with form submissions
                    -->
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                      <ul class="navbar-nav">

                        <!--Links to Checked In/Out View -->

                        <li class="nav-item">
                          <a class="nav-link" href="#">Check In / Check Out<span class="sr-only"></span></a>
                        </li>

                        <!--Links to Patron Views
                                                    - Patron Schedule
                                                    - Sold Books
                                                    - Previous Books
                        -->
                        <li class="nav-item">
                          <a class="nav-link" href="#">Patron Info</a>
                        </li>

                        <!--Links to Login View -->

                        <li class="nav-item">
                          <a class="nav-link" href="#">Exit</a>

                        </li>
                      </ul>
                    </div>
                  </nav>

                <script
                        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                        crossorigin="anonymous">
                </script>

                <script
                        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                        crossorigin="anonymous">
                </script>

                <script
                        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                        crossorigin="anonymous">
                </script>

    </body>
    </script>
    <script type="text/javascript"
            src = "JS/ShardHandler.js"></script>
    <script type="text/javascript"
            src = "JS/StudentDropDownMenuHandler.js"></script>
    <script type="text/javascript"
            src = "JS/StudentDropDownMenuAjax.js"></script>

</html>
