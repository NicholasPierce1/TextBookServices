<%--
  Created by IntelliJ IDEA.
  User: Spyridon
  Date: 3/17/2021
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Book Disposition, Barcode, Code/Year</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">


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
            color: rgb(216, 221, 214);
        }
        .input-group-text{
            border-radius: 10px;
        }






    </style>
</head>
<body>
<section>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">NavBarLogo</a>
            <button class="navbar-toggler toggler-custom" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

            </div>
            <div class="navbar-item justify-content-end">
                <button class="btn btn-logout-custom" type="button">Log Out</button>
            </div>
        </div>
    </nav>
</section>
<section>
    <div class="container">
        <div style="color: rgb(0,103,76);
                            font-family: Arial, Helvetica, sans-serif;
                            font-size: 30px;
                            font-weight: bold;
                            margin-top: 1em;
                            margin-bottom: 1em;
                            text-align: center;
                            ">
            <h1>Change Book</h1>
        </div>
        <div class="Form form-buttons">
            <div class="row">
                <div class="btn-group" role="group" aria-label="Basic radio toggle button group"
                     style="margin:5px 0 10px 0;">
                    <button onclick="change_to_disposition();" id="show_disposition" type="button" class="btn btn-custom btn-text">Disposition</button>
                    <button onclick="change_to_barcode();" id="show_barcode" type="button" class="btn btn-custom btn-text">Barcode</button>
                    <button onclick="change_to_codeYear();" id="show_codeYear" type="button" class="btn btn-custom btn-text">Code /Year</button>
                </div>
            </div>
        </div>

    </div>
    <div id="display_container" class="container">
        <!-- Here we have the Disposition Form.
        ---- This container will display all three
        ---- views.  -->



        <!--<div id="change_book_disp_form" class="Form my-1 py-3">
                <h2>Change Book Disposition</h2>
                <div class="row gy-5">
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="book_code_disp" class="input-group-text" >Book Code</label>
                            <input id="book_code_disp" type="text" class="form-control" placeholder="" aria-label="Username">
                          </div>
                    </div>
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="book_year_disp" class="input-group-text" >Book Year</label>
                            <input id="book_year_disp" type="text" class="form-control" placeholder="">
                          </div>
                    </div>
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="strike_barcode_disp" class="input-group-text">Strike Barcode</label>
                            <input id="strike_barcode_disp" type="text" class="form-control" placeholder="">
                          </div>
                    </div>
                </div>
                <div class="row gy-5">
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="title_disp" class="input-group-text">Title</label>
                            <input id="title_disp" type="text" class="form-control" placeholder="" aria-label="Username">
                          </div>
                    </div>
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <div class="input-group mb-3">
                                <label for="seq_number_disp" class="input-group-text">Seq. Number</label>
                                <input id="seq_number_disp" type="text" class="form-control" placeholder="Username" aria-label="Username">
                              </div>
                          </div>
                    </div>
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="bar_code_disp" class="input-group-text">Bar Code</label>
                            <input id="bar_code_disp" type="text" class="form-control" placeholder="Username" aria-label="Username">
                          </div>
                    </div>
                </div>

                <div class="row gy-5">
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">
                            <label for="current_disposition" class="input-group-text">Current Disposition</label>
                            <input id="current_disposition" type="text" class="form-control" placeholder="Disposition" aria-label="Username">
                          </div>
                    </div>
                    <div class="col-md-4 pt-2">
                        <div class="input-group mb-3">

                                <label for="change_disposition_to" class="input-group-text">Change Disposition to</label>
                                <input id="change_disposition_to" type="text" class="form-control" placeholder="Disposition" aria-label="Disposition">
                            </div>

                    </div>
                </div>
                <div class="row gy-5 ">
                    <div class="btn-group">
                        <button id="disposition_send" type="button" class="btn btn-custom btn-md btn-text">Change</button>
                        <button onclick = "clear_input();" id="disposition_clear"type="button" class="btn btn-custom btn-md btn-text">Clear</button>
                    </div>
                </div>
            </div> -->
    </div>

    <!-- This is the Change Barcode View. This view
      -- will be displayed in the display_container -->

    <!-- <div id="barcode" class="container">

       <div id="change_barcode_form" class="Form my-1 py-3">
           <h2>Change Barcode</h2>
           <div class="row gy-5 pt-2">
               <div class="col-md-4">
                   <div class="input-group mb-3">
                       <label for="book_code" class="input-group-text">Book Code</label>
                       <input id="book_code" type="text" class="form-control">
                   </div>
               </div>
               <div class="col-md-4">
                   <div class="input-group mb-3">
                       <label for="book_year" class="input-group-text">Book Year</label>
                       <input id="book_year" type="text" class="form-control">
                   </div>
               </div>
               <div class="col-md-4">
                   <div class="input-group mb-3">
                       <label for="strike_barcode" class="input-group-text">Strike Barcode</label>
                       <input id="strike_barcode" type="text" class="form-control">
                   </div>
               </div>
           </div>
       </div>
       <div class="Form my-1 py-3">
           <h2>Book Info</h2>
           <div class="row gy-5 pt-2">
                   <div class="input-group mb-3">
                       <label for="book_title" class="input-group-text">Title</label>
                       <input id="book_title" type="text" class="form-control">
                   </div>
           </div>
           <div class="row gy-5">
               <div class="col-md-4">
                   <div class="input-group mb-3">
                       <label for="book_sq_number" class="input-group-text">Seq. Number</label>
                       <input id="book_sq_number" type="text" class="form-control">
                   </div>
               </div>
               <div class="col-md-4">
                   <div class="input-group mb-3">
                       <label for="change_barcode" class="input-group-text">Change Barcode</label>
                       <input id="change_barcode" type="text" class="form-control">
                   </div>
               </div>
           </div>

           <div class="row gy-3 my-1">
               <div class="btn-group">
                   <input type="button" class="btn-check" id="btncheck1" autocomplete="off">
                   <label class="btn btn-custom btn-text" for = "btncheck1">Change</label>
                   <input type="button" class="btn-check" id="btncheck1" autocomplete="off">
                   <label class="btn btn-custom btn-text" for = "btncheck1">Clear</label>
               </div>
           </div>
       </div>
   </div>  -->

    <!-- This is the Change Code/Year view and it will
      -- be displayed in the 'display_container' as well -->

    <!-- <div id="codeyear" class="container">

        <div class="Form my-1 py-3">
            <h2>Change Code/Year</h2>
            <div class="row gy-5 pt-2">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label for="book_code_chCodeYear" class="input-group-text">Book Code</label>
                        <input id="book_code_chCodeYear" class="form-control">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label for="book_year_chCodeYear" class="input-group-text">Book Year</label>
                        <input id="book_year_chCodeYear" class="form-control">
                    </div>
                </div>
            </div>
            <div class="row gy-5">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label for="new_book_code_chCodeYear" class="input-group-text">New Book Code</label>
                        <input id="new_book_code_chCodeYear" class="form-control">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <label for="new_book_year_chCodeYear" class="input-group-text">New Book Year</label>
                        <input id="new_book_year_chCodeYear" class="form-control">
                    </div>
                </div>
            </div>

            <div class="row gy-3 my-1">
                <div class="btn-group">
                    <label class="btn btn-custom btn-text" for = "change_codeYear">Change</label>
                    <input type="button" class="btn-check" id="change_codeYear" autocomplete="off">
                    <label class="btn btn-custom btn-text" for = "clear_codeYear">Clear</label>
                    <input type="button" class="btn-check" id="clear_codeYear" autocomplete="off">
                </div>
            </div>
        </div>
    </div> -->


</section>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
<script src="/resources/META-INF/JS/changebook.js"></script>
</body>
</html>
