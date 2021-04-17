<%@ page import="org.springframework.boot.configurationprocessor.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Spyridon
  Date: 3/9/2021
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check In/Out</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css-bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/CSS/checkinoutCSS.css">
    <script type="application/javascript" src="/js/SharedHandler.js"></script>
    <script type="application/javascript" src="/js/HomeHandler.js"></script>
    <script type="application/javascript" src="/js/CheckInOutHandler.js"></script>
    <script type="application/javascript" src="/js/ViewModel/test.js"></script>
    <script type="application/javascript" src="/js/TestJavaScriptFiles/ChaseTestFile.js"></script>
    <script type="application/javascript" src="/js/TestJavaScriptFiles/SpyridonTestFile.js"></script>

    <!--

     - create script tag here for a new js file that holds the test suites for all the targeted functions

    -->
 
</head>
<body>
<%
    // extract json data from page load (NOT api call -- but from form controller)
    final String data = ((JSONObject)request.getAttribute("data")).toString();

    // print's invisible input tag with the id "data" for extraction by js files in client-browser
    out.println("<input type=\"hidden\" value=\"" + data + "\" id=\"data\">" );
%>
<input type="hidden" value="blah" id="blah2">

<section>
    <nav class="navbar navbar-expand-lg navbar-custom navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src='/img/Northwestlogo.png' style="width: 80px; height: 50px;">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav pb-1 pt-1">
                    <a class="nav-link active navbar-text" aria-current="page" href="#">Home</a>
                </ul>
                <div class="nav-item dropdown pb-1 pt-1">
                    <a class="nav-link dropdown-toggle navbar-text" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Inventory
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Add Books</a></li>
                        <li><a class="dropdown-item" href="#">Change Book</a></li>
                        <li><a class="dropdown-item" href="#">Query Book</a></li>
                        <li><a class="dropdown-item" href="#">Query Course</a></li>
                        <li><a class="dropdown-item" href="#">Course Message</a></li>
                        <li><a class="dropdown-item" href="#">Maintenance</a></li>
                    </ul>
                </div>
                <div class="nav-item dropdown pb-1 pt-1">
                    <a class="nav-link dropdown-toggle navbar-text" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Patrons
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Check In/Out</a></li>
                        <li><a class="dropdown-item" href="#">Patrons</a></li>
                    </ul>
                </div>
                <div class="nav-item dropdown pb-1 pt-1">
                    <a class="nav-link dropdown-toggle navbar-text" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reports
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                    </ul>
                </div>

            </div>
            <div class="navbar-item justify-content-end">
                <button class="btn btn-logout-custom" type="button">Log Out</button>
            </div>
        </div>
    </nav>
</section>
<section>

    <div class="container">
        <div class="Form my-4 py-5">
            <h2>Check In / Out</h2>
            <div class="row gy-5">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label class="input-group-text">Term</label>
                        <select class="form-select">
                            <option>Fall</option>
                            <option>Spring</option>
                            <option>Summer</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label class="input-group-text">Year</label>
                        <select class="form-select" aria-placeholder="Year">
                            <option>2021</option>
                            <option>2022</option>
                            <option>2023</option>
                            <option>2024</option>
                            <option>2025</option>
                            <option>2026</option>
                            <option>2027</option>
                        </select>
                    </div>
                </div>

            </div>
            <div class="row gy-5">
                <div class="col-md-4 pt-2">
                
                    <div class="input-group mb-3">
                        <label class="input-group-text">919#</label>
                        <input type="number" class="form-control" placeholder="ID">
                        <label type="hidden" id = "error_studentID"></label>
                    </div>
                </div>
                <div class="col-md-4 pt-2">
                    <div class="input-group mb-3">
                        <label class="input-group-text">Name</label>
                        <input type="text" class="form-control" placeholder="Name">
                        <label type="hidden" id="error_name"></label>
                    </div>
                </div>
            </div>
            <div class="row gy-5">
                <div class="col-md-4 pt-2">
                    <div class="input-group mb-3">
                        <label class="input-group-text">Barcode</label>
                        <input type="text" class="form-control" placeholder="Barcode">
                        <label type="hidden" id="error_barcode"></label>
                    </div>
                </div>
                <div class="col-md-4 pt-2">
                    <div class="input-group mb-3">
                        <label class="input-group-text">Bag#</label>
                        <input type="number" class="form-control" placeholder="Bag #">
                        <label type="hidden" id="error_bagnumber"></label>
                    </div>
                </div>
            </div>
            <div class="row gy-5">
                <div class="col-md-4 pt-2">
                    <label type="hidden" id="statusMessageErrorLabel"></label>
                </div>
            </div>






            <div class="row pt-1">
                <div class="btn-group">
                    <input type="button" class="btn-check" id="btncheck1" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "btncheck1">New ID</label>
                    <input type="button" class="btn-check" id="btncheck2" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "btncheck2">Clear</label>
                    <input type="button" class="btn-check" id="btncheck3" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "btncheck3">Schedule</label>
                    <input type="button" class="btn-check" id="btncheck4" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "btncheck4">Prev Books</label>
                    <input type="button" class="btn-check" id="btncheck5" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "btncheck5">Sold Books</label>
                </div>



            </div>
        </div>
    </div>

    <table>
    </table>
</section>

    <button onclick="testStatusCodeErrorOnClick();">test show status code error</button>

<!--

- create new buttons for EACH test
IF the test function needs data to run (ie: handleStatusErrorMessage(String) )
 then have the onClick test function have hardcoded, local constants (params)

-->

    <label id="testStatusCodeError" style="visibility: hidden">test</label>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="/js-bootstrap/bootstrap.min.js"></script>
</body>
</html>
