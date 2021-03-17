<%--
  Created by IntelliJ IDEA.
  User: Spyridon
  Date: 3/9/2021
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
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
        .navbar-custom .navbar-text {
            color: white;
        }
        .right button{margin-right:6px}

        .btn-custom {
            background-color: rgb(0,103,76);
        }
        .btn-text {
            color: white;
        }
        .Form{
            background-color: rgb(216, 221, 214);
            padding: 1em;
            margin: 0 -1em;
            border-radius: 0.5em;
        }

    </style>
</head>
<body>
<section>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">NavBarLogo</a>
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
                    <!-- Dropdown menu with options.-->
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Maintenance</a></li>
                        <li><a class="dropdown-item" href="#">Add Books</a></li>
                        <li><a class="dropdown-item" href="#">Query Book</a></li>
                        <li><a class="dropdown-item" href="#">Change Book Disposition</a></li>
                        <li><a class="dropdown-item" href="#">Replace Barcode</a></li>
                        <li><a class="dropdown-item" href="#">Query Course</a></li>
                        <li><a class="dropdown-item" href="#">Course Message</a></li>
                        <li><a class="dropdown-item" href="#">Change Book Code/Year</a></li>
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
                <form class="d-flex pb-1 pt-1">
                    <button class="btn btn-outline-warning" type="button">Log Out</button>
                </form>
            </div>
        </div>
    </nav>
</section>
<section class="Form my-4 py-5">
    <div class="container">

        <div class="row gy-5">
            <div class="col-md-4">
                <!-- Select form for term -->
                <select id="term" class="form-select">
                    <option>Fall</option>
                    <option>Spring</option>
                    <option>Summer</option>
                </select>

            </div>
            <div class="col-md-4">
                <!-- Select form for year -->
                <select id="year" class="form-select" aria-placeholder="Year">
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
        <div class="row gy-5">
            <div class="col-md-4 pt-2">
                <!-- Input for ID -->
                <input id="id" type="number" class="form-control" placeholder="ID">
            </div>
            <div class="col-md-4 pt-2">
                <!-- Input that will display the name of the student. It would be nice if we can
                   search by name too -->
                <input id="name" type="text" class="form-control" placeholder="Name">
            </div>
        </div>
        <div class="row gy-5">
            <div class="col-md-4 pt-2">
                <!-- Barcode -->
                <input id="barcode" type="text" class="form-control" placeholder="Barcode">

            </div>
            <div class="col-md-4 pt-2">
                <!-- Will display bag# -->
                <input id="bag" type="number" class="form-control" placeholder="Bag #">

            </div>
        </div>

    </div>
</section>
<section>
    <div class="container">
        <div class="btn-group">
            <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off">
            <label class="btn btn-custom btn-text" for = "btncheck1">New ID</label>
            <input type="checkbox" class="btn-check" id="btncheck2" autocomplete="off">
            <label class="btn btn-custom btn-text" for = "btncheck2">Clear</label>
            <input type="checkbox" class="btn-check" id="btncheck3" autocomplete="off">
            <label class="btn btn-custom btn-text" for = "btncheck3">Schedule</label>
            <input type="checkbox" class="btn-check" id="btncheck4" autocomplete="off">
            <label class="btn btn-custom btn-text" for = "btncheck4">Prev Books</label>
            <input type="checkbox" class="btn-check" id="btncheck5" autocomplete="off">
            <label class="btn btn-custom btn-text" for = "btncheck5">Sold Books</label>



        </div>
    </div>
</section>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
</body>
</html>
