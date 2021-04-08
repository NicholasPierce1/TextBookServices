function change_to_disposition(){
    document.getElementById('display_container').innerHTML='<div id="change_book_disp_form" class="Form my-1 py-3"> <h2>Change Book Disposition</h2> <div class="row gy-5"> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="book_code_disp" class="input-group-text" >Book Code</label> <input id="book_code_disp" type="text" class="form-control" placeholder="" aria-label="Username"> </div> </div> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="book_year_disp" class="input-group-text" >Book Year</label> <input id="book_year_disp" type="text" class="form-control" placeholder=""> </div> </div> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="strike_barcode_disp" class="input-group-text">Strike Barcode</label> <input id="strike_barcode_disp" type="text" class="form-control" placeholder=""> </div> </div> </div> <div class="row gy-5"> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="title_disp" class="input-group-text">Title</label> <input id="title_disp" type="text" class="form-control" placeholder="" aria-label="Username"> </div> </div> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <div class="input-group mb-3"> <label for="seq_number_disp" class="input-group-text">Seq. Number</label> <input id="seq_number_disp" type="text" class="form-control" placeholder="Username" aria-label="Username"> </div> </div> </div> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="bar_code_disp" class="input-group-text">Bar Code</label> <input id="bar_code_disp" type="text" class="form-control" placeholder="Username" aria-label="Username"> </div> </div> </div> <div class="row gy-5"> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="current_disposition" class="input-group-text">Current Disposition</label> <input id="current_disposition" type="text" class="form-control" placeholder="Disposition" aria-label="Username"> </div> </div> <div class="col-md-4 pt-2"> <div class="input-group mb-3"> <label for="change_disposition_to" class="input-group-text">Change Disposition to</label> <input id="change_disposition_to" type="text" class="form-control" placeholder="Disposition" aria-label="Disposition"> </div> </div> </div> <div class="row gy-5 "> <div class="btn-group"> <button id="disposition_send" type="button" class="btn btn-custom btn-md btn-text">Change</button> <button onclick = "clear_input();" id="disposition_clear"type="button" class="btn btn-custom btn-md btn-text">Clear</button> </div> </div> </div>';
}
function change_to_barcode(){
    document.getElementById('display_container').innerHTML='<div id="change_barcode_form" class="Form my-1 py-3"> <h2>Change Barcode</h2> <div class="row gy-5 pt-2"> <div class="col-md-4"> <div class="input-group mb-3"> <label for="book_code" class="input-group-text">Book Code</label> <input id="book_code" type="text" class="form-control"> </div> </div> <div class="col-md-4"> <div class="input-group mb-3"> <label for="book_year" class="input-group-text">Book Year</label> <input id="book_year" type="text" class="form-control"> </div> </div> <div class="col-md-4"> <div class="input-group mb-3"> <label for="strike_barcode" class="input-group-text">Strike Barcode</label> <input id="strike_barcode" type="text" class="form-control"> </div> </div> </div> </div> <div class="Form my-2 py-2"> <h2>Book Info</h2> <div class="row gy-5 pt-2"> <div class="input-group mb-3"> <label for="book_title" class="input-group-text">Title</label> <input id="book_title" type="text" class="form-control"> </div> </div> <div class="row gy-5"> <div class="col-md-4"> <div class="input-group mb-3"> <label for="book_sq_number" class="input-group-text">Seq. Number</label> <input id="book_sq_number" type="text" class="form-control"> </div> </div> <div class="col-md-4"> <div class="input-group mb-3"> <label for="change_barcode" class="input-group-text">Change Barcode</label> <input id="change_barcode" type="text" class="form-control"> </div> </div> </div> <div class="row gy-3 my-1"> <div class="btn-group"> <input type="button" class="btn-check" id="btncheck1" autocomplete="off"> <label class="btn btn-custom btn-text" for = "btncheck1">Change</label> <input type="button" class="btn-check" id="btncheck1" autocomplete="off"> <label class="btn btn-custom btn-text" for = "btncheck1">Clear</label> </div> </div> </div>';
}
function change_to_codeYear(){
    document.getElementById('display_container').innerHTML='<div class="Form my-1 py-3"> <h2>Change Code/Year</h2> <div class="row gy-5 pt-2"> <div class="col-md-4"> <div class="input-group mb-3"> <label for="book_code_chCodeYear" class="input-group-text">Book Code</label> <input id="book_code_chCodeYear" class="form-control"> </div> </div> <div class="col-md-4"> <div class="input-group mb-3"> <label for="book_year_chCodeYear" class="input-group-text">Book Year</label> <input id="book_year_chCodeYear" class="form-control"> </div> </div> </div> <div class="row gy-5"> <div class="col-md-4"> <div class="input-group mb-3"> <label for="new_book_code_chCodeYear" class="input-group-text">New Book Code</label> <input id="new_book_code_chCodeYear" class="form-control"> </div> </div> <div class="col-md-4"> <div class="input-group mb-3"> <label for="new_book_year_chCodeYear" class="input-group-text">New Book Year</label> <input id="new_book_year_chCodeYear" class="form-control"> </div> </div> </div> <div class="row gy-3 my-1"> <div class="btn-group"> <label class="btn btn-custom btn-text" for = "change_codeYear">Change</label> <input type="button" class="btn-check" id="change_codeYear" autocomplete="off"> <label class="btn btn-custom btn-text" for = "clear_codeYear">Clear</label> <input type="button" class="btn-check" id="clear_codeYear" autocomplete="off"> </div> </div> </div>';
}

























































































function onClickHandlerForSellBook(e){
    checkStudentInfoInputsAndState(e);

}

function getBarcodeFromInput(){
    if(document.getElementById("barcode") != null){
        return document.getElementById("barcode");
    }
    else{
        console.log("Error occurred: Barcode");
    }
}



function onClickHandlerForGetAllCheckedOutBooksForStudentAndTerm(e){
    checkStudentInfoInputsAndState(e);
}


function onFocusOutForBarcode(e){
    if(e == null){
        return;
    }
    else{
        getTableRowFromBookCopy(e);
        if(getTableRowFromBookCopy(e) != null){
            refreshViewWithJsonForCheckedOutBook(json);
        }
        else{
            refreshViewWithJsonForCheckedInBook(json);
        }
    }
}

function getTableRowFromBookCopy(barcode){
    if(getBookCopyWhereBarcodeMatches(barcode) == null){
        return null;
    }
    else{
        if(getBookCopyWhereBarcodeMatches(barcode)){
            return document.getElementById("table").innerHTML = "Found in this table.";
        }
        else{
            console.log("Error occurred: No Match");
        }
    }

}

function getBookCopyWhereBarcodeMatches(barcode){
    let sessionBarcodeList = new Map();
    let bookCopyList = [window.sessionStorage.getItem('bookCopy')];
    for(let i = 0; i < bookCopyList.length; i++){
        sessionBarcodeList.set(bookCopyList[i]);
    }
    for(let i = 0; i < sessionBarcodeList.length; i++){
        if(sessionBarcodeList[i] == barcode){
            let match = sessionBarcodeList.get(barcode);
            return match;
        }

        else{
            return null;
        }

    }
}


function checkStudentInfoInputsAndState(input){

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(input.length != 0 && input != null && !StudentInfo.has(input)){
                StudentInfo.set(input);
                console.log(this.status);
            }
            else{
                console.log("An Error Occurred");
            }
       }
       else{
        console.log(`An Error Occurred, State: ${this.readystate} and status: ${this.status}`);
    }
    };
    xhttp.open("GET", "studentInfo", true);
    xhttp.send();
}

