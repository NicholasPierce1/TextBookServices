<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Query Course</title>
    <style>
        .column {float: left;}
        .left {width: 90%;}
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

        #tableDiv{
            height: 500px;
            width: 100%;

            border-style: ridge;
            border-width: thick;
            border-color: ForestGreen;
        }
        table{
            border_collapse:collapse;
            }
        tr{
            width: 100px;
            padding: 50px;
        }
        tr:nth-child(even){
            background-color: lightgrey;
        }
        th{
            text-align: left;
            width: 100px;
            padding: 0px 15px;
        }
        td{
            width: 100px;
            padding: 0px 15px;
        }
        #tdTitle{
            width: 100%;
            padding: 0px 15px;
        }
    </style>
    <script>
        let bookData = [
        { title: 'book1', code: '1010', year: '2000'},
        { title: 'book2', code: '1020', year: '2000'},
        { title: 'book3', code: '1030', year: '2000'},
        { title: 'database thing here', code: 'Durban', year: 'do the'}
        ];

        window.onload = () => {
            loadTableData(bookData);
        };

        function loadTableData(bookData) {
            const tableBody = document.getElementById('tableData');
            let dataHtml = '';

        for(let book of bookData) {
            dataHtml += '<tr><td>'+book.code+'</td><td>'+book.year+'</td><td id = "tdTitle">'+book.title+'</td></tr>';
            }
            console.log(dataHtml)
            tableBody.innerHTML = dataHtml;
        }

    </script>
</head>
<body>
<h1>Textbook Services: Query a Course</h1>
<div class="dropdown">
    <button class="dropbtn">Inventory</button>
    <div class="dropdown-content">
            <a href="maintenanceFormView">Maintenance</a>
            <a href="addBook">Add Books</a>
            <a href="bookQuery">Query Books</a>
            <a href="bookDisposition">Change Book Disposition</a>
            <a href="replaceBarcode">Replace Barcode</a>
            <a href="queryCourse">Query Course</a>
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
            <label>Course:</label>
            <input type = "text"
                   id = "course" />
        </p>
    </fieldset>
</form>
<div id = 'tableDiv'>
<table>
    <thead>
        <tr>
            <th>Book Code</th>
            <th>Book Year</th>
            <th>Title</th>
        </tr>
    </thead>
    <tbody id="tableData"></tbody>
</table>
</div>
</body>
</html>