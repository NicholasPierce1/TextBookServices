/**
 * @author Spyridon Kaperonis
 * 
 */


var error_IDnumber = window.document.getElementById("error_studentID");
var sessionMap = window.sessionStorage;
let errorLabelTest;
//Get Form from jsp view
let form;

// imports home handler to ensure shared, home state is invokable
import * as HOME from "./HomeHandler.js";
import * as SHARED from "./SharedHandler.js";
import * as TEST_NICK_DEMO from "./TestJavaScriptFiles/NickTestFileDemo.js";

// establishes the window's onload functionality
// initialize state (render and save login user info, set any non-locally scoped members
window.onload = (ev) =>{
    errorLabelTest = document.getElementById("testStatusCodeError");

    // invokes the home handler to initialize view state
    try{
        // HOME.initializeSharedState();
        
        // todo: spyridon, chase
        // set on clicks of the test button 
        const testButton = window.document.getElementById("testButton");
        console.log("running");
        testButton.onclick = TEST_NICK_DEMO.testStatusCodeErrorOnClick;
        console.log(`${testButton.onclick}`);
        
    }
    catch(ex){
        console.log("failed to initialize home state:\n" + ex);
    }


}


/**
 * Test Code
*/

/*
test works:
1) create the test function (function that calls the targeted method to test).
   create dummy/test state and invoke the targeted method
2) set button on click (on load) to the test function
3) run it & debug
 */



/**
 *  Level 1:
 *  Small Functions
 */

export function showStatusCodeError(message) {


    const errorLabel = window.document.getElementById("statusMessageErrorLabel");

    if(typeof(message) != "string" || errorLabel === null)
        throw new Error("input param is not a string or id is null");

    errorLabel.innerHTML = message;

    errorLabel.style.visibility = "visible";
    errorLabel.style.color = "red";

    console.log(errorLabel.style.visibility === null || errorLabel.style.visibility === "");
    console.log("test here");
}

export function hideStatusCodeError() {


    const errorLabel = window.document.getElementById("statusMessageErrorLabel");

    if(errorLabel === null)
        throw new Error("is null");

    errorLabel.style.visibility = "hidden";

}



 /**
 * Function to display errors using innerHTML.
 */

export function errorMessage(){
    if(isNaN(document.getElementById("IDnumber"))){
        error_IDnumber.style.visibility="visible";
        error_IDnumber.style.color="red";
        error_IDnumber.innerHTML("<p>Please enter the 919#</p>");

    }
    else if(typeof(document.getElementById("IDnumber"))!=="number"){
        error_IDnumber.style.visibility="visible";
        error_IDnumber.style.color="red";
        error_IDnumber.innerHTML("<p>Incorrect input type</p>");
    }
}

/**
 * Makes visible statusMessage error label and 
 * it injects statusMessage into the label.
 * @param {*} statusMessage 
 */

export function showStatusMessageError(statusMessage) {

    //Get dom element. label for statusMessage.
    const statusMessageErrorlabel = window.document.getElementById("statusMessageErrorLabel");
    
    // Make label visible and add a red color.
    statusMessageErrorlabel.style.visibility = "Visible";
    statusMessageErrorlabel.style.color="red";
    statusMessageErrorlabel.innerHTML = statusMessage;


}

/**
 * @purpose Makes statusMessage error label hidden. 
 */
export function hideStatusMessageError() {
    //Get dom element. label for statusMessage.
    const statusMessageErrorlabel = window.document.getElementById("statusMessageErrorLabel");

    // Make label hidden.
    statusMessageErrorlabel.style.visibility = "hidden";
}

/**
 * @purpose Display "Not Verified" in bag error label using innerHTML. 
 */

export function showBagError() {
    //Get dom element for label of bag error.
    const error_bag = window.document.getElementById("error_bag");
    error_bag.style.visibility = "visible";
    error_bag.innerHTML = "Not Verified";
}

/**
 * @purpose Hides errors for the Bag input
 */

export function hideBagError() {
    //Get dom element for label of bag error.
    const error_bag = window.document.getElementById("error_bag");
    error_bag.style.visibility = "hidden";
}
 /**
 *  Level 2:
 *  Functions that call the small functions
 */

export function handleErrorResponse(json) {
    //ask about the gen_error
    if (gen_error) {
        SHARED.printError();
        return true;
        if (true) {
            return handlerStatusMessageError();
        }
    }
    hideStatusMessageError();


    if (error) {
        const errorBindings = {errorN: ""};
        for (let i = 0; i < error.length; i++) {
            errorBindings.errorN = error;
            helper(false);
        }

    }
    hideErrorBindings();
}

export function handlerStatusMessageError(statusMessage) {
    if (statusMessage == "OK" || null) {
        showStatusMessageError();
        return true;
    }

}

export function handleErrorBindings() {
    const id_error_label = document.getElementById("error_studentID");
    const studentID = document.getElementById("student_id_in");

    const id_error_labelValue = id_error_label.value;
    if (id_error_labelValue != null && studentID.innerHTML == "") {
        studentID.innerHTML = "";
    }
    return
}


/**
 * @purpose 
 * @param {*} bookCopyList 
 */

export function showBookCopyForAllCheckedOutBooks(bookCopyList) {
    // call helper for each book copy
    for (let i = 0; i < bookCopyList.length; i++) {
        helper(bookCopyList[i])
    }


}


/**
 * @purpose Create a table for Checked out book.
 * @param {*} BookCopy 
 */

export function showBookCopyForCheckedOutBook(BookCopy) {

    //Parse book copy in JSON
    book_obj = JSON.parse(BookCopy);

    //Get DOM element for table
    let table = document.getElementsByTagName("table");
    let thead = table.createTHead();
    let row = thead.insertRow();

    for(let element of book_obj){
        if(element.disposition == "O"){
            let th = document.createElement("th");
            let text = document.createTextNode(element);
            th.appendChild(text);
            row.appendChild(th);
        }
    }

}



export function removeTableRow(table) {
    const viewTable = document.getElementsByTagName('table');

}

/**
 *  hide error bindings
 */

export function hideErrorBindings() {



}

export function deleteTableRowWhereBarcodeMatches(barcode) {
    const table = window.document.getElementsByTagName("table").rows;
    for (let i of table) {
        if (i == barcode) {
            table.deleteRow(table[i]);
        }
    }
}

export function onFocusOutForBarcode() {


    //Get barcode input
    var barcode = document.getElementById("barcode");

    //If input is empty then return
    if (barcode.innerHTML = "") {
        return;
    }
    //Call getTableRowFromBookCopy
    let checkIfNull = getTableRowFromBookCopy();

    if (!checkIfNull) {
        //Then call ajax handler for check out book
    } else {
        //call ajax handler for check in book
    }

}

export function getBarcodeFromInput() {
    //Get barcode from input element
    barcode = document.getElementById("barcode");

    //if null or empty throw error
    if (barcode.innerHTML = "" || !barcode) {
        throw 'Barcode input is null or empty';
    } else {
        return barcode;
    }
}

export function getTableRowFromBookCopy(barcode) {
    //call getBookCopyWhereBarcodeMatches
    let checkIfNull = getBookCopyWhereBarcodeMatches();

    //Check if input is null
    if (!checkIfNull) {
        return null;
    }

    //Temporary variables
    var temp_bookcode = 0;
    var temp_seqnumber = 9;
    var temp_year = 0;

    //Get all tr in table and iterate
    var rows = document.getElementsByTagName("table")[0].rows;

    //Iterate through the table and find a match for bookcode, sequence number, and year
    // If no match throw error
    for (let i = 0; i < rows.length; i++) {
        if (rows[i] == temp_bookcode && rows[i] == temp_seqnumber && rows[i] == temp_year) {

            return rows[i];

        } else {

            throw 'No match found';

        }
    }

}

export function getBookCopyWhereBarcodeMatches(barcode) {

    //Extract book copy list from local session map

    //Check for matches. On match extract and return the map of the book

    //If no match then return null

    let sessionBarcodeList = new Map();
    let bookCopyList = [window.sessionStorage.getItem('bookCopy')];
    for (let i = 0; i < bookCopyList.length; i++) {
        sessionBarcodeList.set(bookCopyList[i]);
    }
    for (let i = 0; i < sessionBarcodeList.length; i++) {
        if (sessionBarcodeList[i] == barcode) {
            let match = sessionBarcodeList.get(barcode);
            return match;
        } else {
            return null;
        }

    }
}


export function checkStudentInfoInputsAndState() {
    /*

    //Check if inputs for student info are empty and show general error message.

    //If any inputs stored in student info are different from new inputs then reset the student
    // info object in the map.


    //If map does not retain the key then skip this.

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
    }*/
}


 /**
 *  Level 3:
 *  Called by ajax handlers ("refreshView")
 */

/**
 * This method is called by ajax method once response is ok
 * The response is converted into json
 * @param {*} jsonResponse
 * @returns
 */

export function refreshViewWithJsonForAllCheckedOutBooks(jsonResponse) {

    if (handleErrorResponse() === false) {
        return
    } else if (bag == null) {
        // Create student and term. Not sure if that what create means
        const student = document.getElementById("student_name");
        const term = document.getElementById("term");

        // Get bag input to display "Not Verfied" in it.
        const bag_input = document.getElementById("bag_input");
        bag_input.innerHTML = "Not Verified";

        sessionMap.setItem(student, term);

        // Hide all error binding errors & status code error


    }
    // Use book copy from SharedHandler
    bookCopy = {};
    var bookCopySessionMap = window.sessionStorage;
    //Call helper to create book copy rows.
    helper();
    // Store book copy list and bag in session map
    bookCopySessionMap.setItem(bookCopy, bag);

}

export function refreshViewWithJsonForCheckedOutBook(json) {
    if (handleErrors() == false) {
        const book_copy = new BookCopy();
        showBookCopyForAllCheckedOutBooks(book_copy);
    }
}

export function refreshViewWithJsonForCheckedInBook(json) {
    if (handlerStatusMessageError() == false) {
        return
    }
    helper();  //Call helper to remove table row where the barcode mathces
    // Parse barcode
}

export function refreshViewWithJsonForSellBook(json) {
    if (handlerError == false) {
        return;
    } else {
        //extract barcode
        // call deleteTableRowWhereBarcodeMatches
        deleteTableRowWhereBarcodeMatches();
    }

}

export function initiateApiFor_AllCheckedOutBooksForStudentAndTerm() {

    //const DataStudentSessionMap = window.sessionStorage;
    const term = document.getElementById("term");
    const year = document.getElementById("year");
    const stu_id = document.getElementsById("student_id");
    const student_name = document.getElementById("student_name");
    const barcode = document.getElementById("barcode");
    const bag_num = document.getElementById("bag_input");


    //Set student info in local session map
    DataStudentLocalStorage.setItem('Term', term);
    DataStudentLocalStorage.setItem('Year', year);
    DataStudentLocalStorage.setItem('Student_id', stu_id);
    DataStudentLocalStorage.setItem('Student_name', student_name);
    DataStudentLocalStorage.setItem('Barcode', barcode);
    DataStudentLocalStorage.setItem('Bag_number', bag_num);


    //Acquire user info from local session

    //call ajax handler
    getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo);

}



/*
 *
*/

export function initiateApiFor_GetCheckedOutBookForStudentTerm() {

    try {


        //Get barcode from input
        barcode = document.getElementById("barcode");

        //Extract from local session map user info and student info
        DataStudentLocalStorage.getItem("Term");
        DataStudentLocalStorage.getItem("Year");
        DataStudentLocalStorage.getItem("Student_id");
        DataStudentLocalStorage.getItem("Student_name");
        DataStudentLocalStorage.getItem("Barcode");
        DataStudentLocalStorage.getItem("Bag_number");


        //Create Student book info


    }
        //if errors display general error message
    catch (err) {
        error_barcode = document.getElementById("error_barcode");
        error_barcode.innerHTML = "Error occured";

    }

    //Call ajax handler
    getCheckoutBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo);
}


/*
 *
 *
 */


export function initiateApiFor_CheckInBookForStudentAndTerm() {
    try {


        //Get barcode from input
        barcode = document.getElementById("barcode");

        //Extract from local session map user info and student info
        DataStudentLocalStorage.getItem("Term");
        DataStudentLocalStorage.getItem("Year");
        DataStudentLocalStorage.getItem("Student_id");
        DataStudentLocalStorage.getItem("Student_name");
        DataStudentLocalStorage.getItem("Barcode");
        DataStudentLocalStorage.getItem("Bag_number");


        //Create Student book info


    }
        //if errors display general error message
    catch (err) {
        error_barcode = document.getElementById("error_barcode");
        error_barcode.innerHTML = "Error occured";

    }

    //Call ajax handler
    getCheckInBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo);

}


/*
 *
 * @param {*} barcode
*/

export function initiateApiFor_SellBookForStudent(barcode) {

    try {
        //Extract user and student info from local session
        DataStudentLocalStorage.getItem("Term");
        DataStudentLocalStorage.getItem("Year");
        DataStudentLocalStorage.getItem("Student_id");
        DataStudentLocalStorage.getItem("Student_name");
        DataStudentLocalStorage.getItem("Barcode");
        DataStudentLocalStorage.getItem("Bag_number");

        //Create student book info


        //If errors display general error message
    } catch (error) {


    }
    //call ajax handler
    sellBookForStudentAJAX(loginUserInfo, studentBookInfo);
}


/**
* Level 3.5:
* Call the ajax (onclick)
*/


export function onClickHandlerFor_SellBook() {

    //Check Student info inputs and state
    studentID = document.getElementById("student_id_in");

    //Get parent tr and get the bookcode, sequence number, and year.


    // call get list of book copies from local session map

    // .....
}

export function onClickHandlerFor_GetAllCheckedOutBooksForStudentAndTerm() {
    //Call checkStudentInfoInputsAndState
    checkStudentInfoInputsAndState();
    //Call respective ajax handler
    getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo);
}





/**
 * Show error
 * @param {*} message
 */


/*
Get all checkout books
1) some button has an onclick listener that was set in the onload (window's)
2) onclick function extract the inputs, validate (input is not empty), and invoke another function to call ajax
3) the called function will call the ajax function and send correct data (url, request content, method type)
4) ajax is done it returns a response
5) the called function takes json and interprets (starts a chain of sub-calls to modify view from json)
 */

/*
test up:
1) start small functions (hide status code, show error message --> shared handler)
2) test the functions that call the small functions that were tested in step 1 (ex: handleErrorResponse)
  test view looks the way it should + bool/return type is of expected
3) test the functions that are called by the ajax (start with "refreshView...")
3.5) test the functions that call the ajax (hint: these are the ones that the onclicks call)
 */

