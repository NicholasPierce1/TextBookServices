<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add a Book</title>
    <style>
        .column {float: left;}
        .left {width: 60%;}
        .middle {width: 30%;}
        .right {width: 10%;}
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        .dropbtn {
            background-color: lightgrey;
            color: darkgreen;
            padding: 10px;
            font-size: 16px;
            border: darkgreen;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 200px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .dropdown-content a {
            color: darkgreen;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {background-color: #ddd;}
        .dropdown:hover .dropdown-content {display: block;}
        .dropdown:hover .dropbtn {background-color: grey;}
    </style>
</head>
<body>
<h1>Textbook Services: Add Book</h1>
<div class="dropdown">
    <button class="dropbtn">Inventory</button>
    <div class="dropdown-content">
            <a href="maintenanceFormView">Maintenance</a>
            <a href="addBook">Add Books</a>
            <a href="bookQuery">Query Books</a>
            <a href="bookDisposition">Change Book Disposition</a>
            <a href="navigationBar">Replace Barcode</a>
            <a href="navigationBar">Query Course</a>
            <a href="navigationBar">Course Message</a>
            <a href="navigationBar">Change Book Code/Year</a>
        </div>
</div>
<div class="dropdown">
    <button class="dropbtn">Patron</button>
    <div class="dropdown-content">
        <a href="#">blah</a>
    </div>
</div>
<div class="dropdown">
    <button class="dropbtn">Reports</button>
    <div class="dropdown-content">
        <a href="#">blah</a>
    </div>
</div>
<form>
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type = "text"
                   id = "bookCode" />
            <label>Book Year:</label>
            <input type = "text"
                   id = "bookYear" />
        </p>
    </fieldset>
</form>
<form>
    <fieldset>
        <p>
            <label>Title:</label>
            <input type = "text"
                   id = "bookTitle" />
        </p>
        <p>
            <label>Seq Nr:</label>
            <input type = "text"
                   id = "seqNr" />
        </p>
        <p>
            <label>Strike Bar Code:</label>
            <input type = "text"
                   id = "barCode" />
        </p>
    </fieldset>
</form>
<div class="column right">
    <p>
        <button type="button">Save</button>
        <button type="button">Clear</button>
    </p>
</div>
</body>
