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
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #18523c;">
            <img src="/img/NorthwestLogo.png" width="50" height="50" class="d-inline-block align-top" alt="">
                <a class="navbar-brand" href="#"></a>
                    <span class="navbar-brand mb-0 h1">Supervisor View</span>
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

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Inventory
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <li><a class="dropdown-item" href="#">Maintenance</a></li>
                          <li><a class="dropdown-item" href="#">Add Books</a></li>
                          <li><a class="dropdown-item" href="#">Query Book</a></li>
                          <li><a class="dropdown-item" href="#">Change Book Disposition</a></li>
                          <li><a class="dropdown-item" href="#">Replace Barcode</a></li>
                          <li><a class="dropdown-item" href="#">Query Course</a></li>
                          <li><a class="dropdown-item" href="#">Course Message</a></li>
                          <li><a class="dropdown-item" href="#">Change Book Code/Year</a></li>

                      <!--
                           Link to Change Book Info
                              - Change Book Disposition
                              - Replace Barcode
                              - Change Book Code Year
                      -->
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

                <li class="nav-item">
                    <a class="nav-link" href="#">Reports</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">Exit</a>
                </li>

              </ul>
            </div>
          </nav>

        <div class="container my-container "
              style="background-color: #bebebe; border: 2px solid black; height: 100%;">

              <div class="row my-row"
                   style="padding: 25px;
                          padding: 25px;">
                  <div class="col-md-20 my-col">
                        <h1>
                            Instructions
                        </h1>

                        <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                        reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                        qui officia deserunt mollit anim id est laborum.
                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                        molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae
                        aliquam voluptatem veniam, est atque cumque eum delectus sint!
                        </p>


                  </div>
              </div>
              <div class="row my-row"
                   style="padding: 25px; padding: 25px;">
                <div class="col-md-20 my-col">
                        <h1>
                            Resources
                        </h1>

                        <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                        reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                        qui officia deserunt mollit anim id est laborum.
                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                        molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae
                        aliquam voluptatem veniam, est atque cumque eum delectus sint!
                        </p>
                </div>
            </div>
          </div>


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
    <script type="text/javascript"
            src = "JS/ShardHandler.js"></script>
    <script type="text/javascript"
            src = "JS/HomeHandler.js"></script>
    <script type="text/javascript"
            src = "JS/HomeAjax.js"></script>

    <footer class="bg-dark text-center text-white sticky-bottom"
            style="bottom: 0;">

        <!-- Grid container -->
        <div class="container-fluid p-4">
            <!--Grid row-->
            <div class="row">
            <!--Grid column-->
            <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                <h5 class="text-uppercase">Contact Us</h5>

                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                    reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                    qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                <h5 class="text-uppercase">Emails</h5>

                <ul class="list-unstyled mb-0">
                <li>

                </li>
                <li>
                    (Name) (title): email
                </li>
                <li>
                    (Name) (title): email
                </li>
                <li>
                    (Name) (title): email
                </li>
                </ul>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-2 col-md-6 mb-4 mb-md-0">
                <h5 class="text-uppercase">Address</h5>
                <p>
                    The Station, <br>
                    900 University Dr, <br>
                    Maryville, MO 64468
                </p>
            </div>
            <!--Grid column-->

        </div>
        <!-- Grid container -->

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.1);">
            About | Privacy Policy | Terms of Use <br>
            © 2021 Copyright: TextBook Services
        </div>

    </footer>


</html>