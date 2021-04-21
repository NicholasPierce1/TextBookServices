<%@ page import="org.springframework.boot.configurationprocessor.json.JSONObject" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.springframework.boot.configurationprocessor.json.JSONArray" %><%--
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

        <link rel="stylesheet" href="/css-bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="/css/homeCSS.css">
        <script type="module" src = "/js/HomeHandler.js"></script>

        <!--
    per nick: remove in production. Testing path variables incur errors from get requests
    relating to favico
-->
        <link rel="shortcut icon" href="#">

    </head>

</head>
<body>

<%
    // extract json data from page load (NOT api call -- but from form controller)
    final String data = ((JSONObject)request.getAttribute("data")).toString();

    // print's invisible input tag with the id "data" for extraction by js files in client-browser
    out.println("<input type=\"hidden\" value=" + data + " id=\"data\">" );
%>

<input type="hidden" id="generalErrors" value="">
    <section>
        <nav id="Student"  class="navbar navbar-expand-lg navbar-custom navbar-dark">
            <div class="container-fluid">
                <img src="/img/NorthwestLogo.png" width="50" height="50" class="d-inline-block align-top" alt="">
                <a class="navbar-brand" href="#"></a>
                    <span class="navbar-brand mb-0 h1">Student View</span>
                </a>
                <button class="navbar-toggler toggler-custom" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav pb-1 pt-1" id="patronDropDownHomeUl" >
                        <li id="PatronHome"><a class="nav-link active navbar-text" aria-current="page" targetEndpoint="/StudentDropDownMenu">Home</a></li>
                    </ul>
                    <ul class="navbar-nav pb-1 pt-1" id="patronDropDownCheckInCheckOutUl" >
                        <li id="PatronCheckInCheckOut"><a class="nav-link navbar-text" aria-current="page" targetEndpoint="/CheckInCheckOut">Check In / Check Out</a></li>
                    </ul>
                    <div class="nav-item dropdown pb-1 pt-1" id="patronDropDownDiv">
                        <a class="nav-link dropdown-toggle navbar-text" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Patron Info
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown" id="patronDropDownUl">
                            <li id="PatronSchedule"><a class="dropdown-item" targetEndpoint="/PatronSchedule">Patron Schedule</a></li>
                            <li id="PatronPreviousBooks"><a class="dropdown-item" targetEndpoint="/PatronPreviousBooks">Patron Previous Books</a></li>
                            <li id="PatronSoldBooks"><a class="dropdown-item" targetEndpoint="PatronSoldBooks">Patron Sold Books</a></li>
                        </ul>
                    </div>

                </div>
                <div class="navbar-item justify-content-end">
                    <button class="btn btn-logout-custom" type="button">Log Out</button>
                </div>
                <div class="navbar-item justify-content-end">
                    <button class="btn btn-logout-custom" type="button">Log Out</button>
                </div>
            </div>
        </nav>

       <input type="hidden" type="text" id="hiddenInput" name="data" value="">
    </section>
    <div class="container my-container "
               style="background-color: #bebebe;
                      border: 2px solid black;
                      height: 100%;">

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
                   style="padding: 25px;
                          padding: 25px;">
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
   <script
           src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
           integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
           crossorigin="anonymous"></script>
   <script src="/js-bootstrap/bootstrap.min.js"></script>

</body>


< class="bg-dark text-center text-white sticky-bottom"
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
   </div>

</footer>


</html>