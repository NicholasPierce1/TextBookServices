<%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <style>
        /* Hide input number arrows for Firefox */
        input[type=number]{
            -moz-appearance: textfield;
        }
        /* Hide input number arrows for Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button{
            -webkit-appearance:none;
            margin: 0;
        }
        /* Remove arrow from navbar dropdown */
        .dropdown-toggle::after {
            content: none;
        }
        @media all and (min-width: 992px) {
            .navbar .nav-item .dropdown-menu{ display: none; }
            .navbar .nav-item:hover .nav-link{ color: #fff;  }
            .navbar .nav-item:hover .dropdown-menu{ display: block; }
            .navbar .nav-item .dropdown-menu{ margin-top:0; }
        }
        .navbar-custom {
            background-color: rgb(0,103,76);
        }
        .navbar-text {
            color: white;
            font-size: 23px ;
        }
        .btn-custom {
            background-color: rgb(0,103,76);
        }
        .btn-text{
            color: white;
        }
        .toggler-custom{
            border-color: white;
        }
        .buttonsOpt {
            align-items: center;
        }
        .btn{
            float: left;
            border: 1px;
            border-radius: 10px;
        }
        .Form{
            background-color: rgb(216, 221, 214);
            padding: 1em;
            margin: 0 -1em;
            border-radius: 0.5em;
        }
        .btn-logout-custom{
            background-color: rgb(216, 221, 214);
            font-size: 23px;
        }
        .input-group-text{
            border-radius: 10px;
        }






    </style>
</head>
<body>
<section>
    <nav class="navbar navbar-expand-lg navbar-custom navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src='/logo.jpg' style="width: 80px; height: 50px;">
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
    <!-- <nav class="navbar navbar-expand-sm navbar dark bg-dark">
        <a href="#" class="navbar-brand">Student View</a> -->

    <!-- For Small Windows -->
    <!-- <button class="navbar-toggler" data-toggle="collapse" data-target="#menu">
        <span class="navbar-toggle-icon"></span>
    </button> -->

    <!-- Shows collapsed navbar to the screen
        - Links will be in js with form submissions
    -->
    <!-- <div class="collapse navbar-collapse" id="menu">
        <ul class="navbar-nav">
            <li class="nav-item"> -->
    <!--Links to Checked In/Out View -->
    <!-- <a href="#" class="nav-link">Check In/ Check Out</a>
</li>
<li class="nav-item"> -->
    <!--Links to Patron Views
        - Patron Schedule
        - Sold Books
        - Previous Books
    -->
    <!-- <a href="#" class="nav-link">Patron Views</a>
</li>
<li class="nav-item"> -->
    <!--Links to Login View -->
    <!-- <a href="#" class="nav-link">Exit</a>
</li>
</ul>
</div>
</nav> -->
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
</section>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>
