/**
 * @author Spyridon Kaperonis
 * 
 */


 var error_IDnumber = document.getElementById("error_IDnumber");
 var sessionMap = window.sessionStorage;

 

 /** 
 * Function to display errors using innerHTML. 
 */ 
 function errorMessage(){
     if(isNaN(document.getElementById("IDnumber"))){
         error_IDnumber.style.visibility="visible"
         error_IDnumber.style.color="red";
         error_IDnumber.innerHTML("<p>Please enter the 919#</p>");
         
     }
     else if(typeof(document.getElementById("IDnumber"))!=="number"){
         error_IDnumber.style.visibility="visible"
         error_IDnumber.style.color="red";
         error_IDnumber.innerHTML("<p>Incorrect input type</p>");
     }
 }
 
 
 /**
  * windows.onload 
  * 
  */

//Get Form from jsp view
var form = document.getElementById("new_id");
 
 form.addEventListener("click",
     windows.onload = () => {

        //Student data in local storage
        const DataStudentLocalStorage = window.localStorage;
        
        // Create map 
         var DataUserInfoMap = new Object();

        // Form object in the map
         form_obj = {/*Term:"Spring", Year:2021, number_id: 919444666, name:"Someone", barcode:12345566, bag_number:12345 */ };
         DataUserInfoMap['form_Object'] = form_obj;
  
         //
         let user_info = new user_info();
         let userID = user_info.getSTUDENT_ID_PATTERN();
         let userPrefix = user_info.getS_NUMBER_PREFIX();
         let userSuffix = user_info.getS_NUMBER_SUFFIX();
  
  
         DataUserInfoMap['UserID'] = (parseJson(userID), "User Id");
         DataUserInfoMap['UserPrefix'] = (parseJson(userPrefix), "user prefix");
         DataUserInfoMap['UserSuffix'] = (parseJson(userSuffix), "user suffix");
  
         invisible_input = document.getElementById("data");
  
         error_labels = [];
 
 
     
    
     

        /**
         * 
         * @param {*} statusMessage 
         */

        function handlerStatusMessageError(statusMessage){
            if(statusMessage=="OK" || null){
                showStatusMessageError();
                return true;
            }
            
        }
        

        /**
         * Get error labels. Make them visible and insert error
         * 
         */

        function showStatusMessageError(){
            error_IDnumber = document.getElementById("error_idNum");
            error_name = document.getElementById("error_name");
            error_barcode = document.getElementById("error_barcode");
            error_bagNum = document.getElementById("error_bag");


            error_IDnumber.innerHTML=""
            error_IDnumber.style.visibility="Visible";

            error_name.innerHTML=""
            error_name.style.visibility="Visible";

            error_barcode.innerHTML=""
            error_barcode.style.visibility="Visible";

            error_bagNum.innerHTML=""
            error_bagNum.style.visibility="Visible";

        }

        /**
         * Get error labels and hide them.\
         * 
         */

        function hideStatusMessageError(){
            error_IDnumber = document.getElementById("error_idNum");
            error_name = document.getElementById("error_name");
            error_barcode = document.getElementById("error_barcode");
            error_bagNum = document.getElementById("error_bag");

            error_IDnumber.style.visibility="hidden";
            error_name.style.visibility="hidden";
            error_barcode.style.visibility="hidden";
            error_bagNum.style.visibility="hidden";
        }


        /**
         * 
         * @method helper() 
         * @param {*} value 
         * @returns 
         */

        function helper(){

        }


        /**
         * 
         * @method handleErrorResponse 
         */

        function handleErrorResponse(json){
            //ask about the gen_error
            if(gen_error){
                printError();
                return true;
                if(true){
                    return handlerStatusMessageError();
                }
            }
            hideStatusMessageError();
        

            if(error){
                const errorBindings = {errorN:""};
                for(i=0; i<error.length; i++){
                    errorBindings.errorN=error;
                    helper(false);
                }
                
            }
            hideErrorBindings();
        }


        /**
         * This method is called by ajax method once response is ok
         * The response is converted into json
         * @param {*} jsonResponse 
         * @returns 
         */

        function refreshViewWithJsonForAllCheckedOutBooks(jsonResponse){
        
            if(handleErrorResponse()===false){
                return
            }
            else if(bag==null){
                    // Create student and term. Not sure if that what create means
                    const student = document.getElementById("student_name");
                    const term = document.getElementById("term");

                    // Get bag input to display "Not Verfied" in it.
                    const bag_input = document.getElementById("bag_input");
                    bag_input.innerHTML= "Not Verified";

                    sessionMap.setItem(student,term);

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

        /**
         * Show error
         * @param {*} message 
         */

        function showStatusCodeError(message){
            console.log(message);
        }

        /**
         *  Hide error
         */
        function hideStatusCodeErro(){

            error_IDnumber.style.visibility="hidden";
            
        }

        /**
         * 
         * @returns 
         */

        function handleErrorBindings(){
            const id_error_label = document.getElementById("error_idNum");
            const studentID = document.getElementById("student_id_in");

            id_error_labelValue = id_error_label.value;
            if(id_error_label !=null && studentID.innerHTML=="" ){
                studentID.innerHTML="";
            }
            return 
        }

        /**
         *  hide error bindings
         */

        function hideErrorBindings(){
            

        }
        /**
         * @purpose Shows errors for the Bag input
         */
        function showBagError(){
            const error_bag = document.getElementById("error_bag");
            error_bag.style.visibility="visible"
            error_bag.innerHTML="Not Verified";
        }

        /**
         * @purpose Hides errors for the Bag input
         */

        function hideBagError(){
            const error_bag = document.getElementById("error_bag");
            error_bag.style.visibility="hidden";
        }

        /**
         * 
         * @param {*} bookCopyList 
         */

        function showBookCopyForAllCheckedOutBooks(bookCopyList){
            // call helper for each book copy
            for(let i=0; i<bookCopyList.length; i++){
                helper(bookCopyList[i])
            }
            
            
        }

        function showBookCopyForCheckedOutBook(BookCopy){

            // I need to add the bookcopy data into bookcopy object


            let table = document.getElementsByTagName("table");
            let thead = table.createTHead();
            let row = thead.insertRow();
            for(let element of BookCopy){
                let th = document.createElement("th");
                let text = document.createTextNode(element);
                th.appendChild(text);
                row.appendChild(th);
            }
            
        }

        /**
         * 
         * @param {Check Out} json 
         * @returns
         */


        function refreshViewWithJsonForCheckedOutBook(json){
            if(handleErrors() == false){
                const book_copy = new BookCopy(); 
                showBookCopyForAllCheckedOutBooks(book_copy);
            }
        }

        //-------------------------
        function removeTableRow(table){
            const table = document.getElementsByTagName('table');
            
        }
        //-------------------------

        /**
         * 
         * @param {Check In} json 
         * @returns 
         */

        function refreshViewWithJsonForCheckedInBook(json){
            if(handlerStatusMessageError() == false){
                return
            }
            helper();  //Call helper to remove table row where the barcode mathces
            // Parse barcode
        }

        function deleteTableRowWhereBarcodeMatches(barcode){
            const table = document.getElementsByTagName('table').rows;
            for(let i of table){
                if(i == barcode){
                    table.row;
                }
            }
        }

        /**
         * 
         * @param {Sell Book} json 
         * @returns 
         */

        function refreshViewWithJsonForSellBook(json){
            if(handlerError == false){
                return;
            } else{
                //extract barcode
                // call deleteTableRowWhereBarcodeMathes
                deleteTableRowWhereBarcodeMatches();
            }

        }

        /**
         * 
         */
       
        function initiateApiFor_AllCheckedOutBooksForStudentAndTerm(){
            
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
            DataStudentLocalStorage.setItem('Bag_number',bag_num);


            //Acquire user info from local session
            //call ajax handler

        }

        /**
         * 
         */

        function initiateApiFor_GetCheckedOutBookForStudentTerm(){

            try{

            
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
            catch(err){
                error_barcode = document.getElementById("error_barcode");
                error_barcode.innerHTML="Error occured";

            }

            //Call ajax handler

        }


        /**
         * 
         * 
         */


        function initiateApiFor_CheckInBookForStudentAndTerm(){
            try{

            
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
            catch(err){
                error_barcode = document.getElementById("error_barcode");
                error_barcode.innerHTML="Error occured";

            }

             //Call ajax handler

        }



        /**
         * 
         * @param {*} barcode 
         */

        function initiateApiFor_SellBookForStudent(barcode){

            try{
            //Extract user and student info from local session
            DataStudentLocalStorage.getItem("Term");
            DataStudentLocalStorage.getItem("Year");
            DataStudentLocalStorage.getItem("Student_id");
            DataStudentLocalStorage.getItem("Student_name");
            DataStudentLocalStorage.getItem("Barcode");
            DataStudentLocalStorage.getItem("Bag_number");

            //Create student book info


            //If errors display general error message
            }catch(error){

            

            

            }
            //call ajax handler
        }

        /**
         * 
         */

        function onClickHandlerFor_SellBook(){

            //Check Student info inputs and state
            studentID = document.getElementById("student_id_in");
            
            //Get parent tr and get the bookcode, sequence number, and year.
            

            // call get list of book copies from local session map

            // .....
        }

        /**
         * 
         * @returns 
         */

        function getBarcodeFromInput(){
            //Get barcode from input element 
            barcode = document.getElementById("barcode");

            //if null or empty throw error
            if(barcode.innerHTML="" || !barcode){
                throw 'Barcode input is null or empty';
            }else{
                return barcode;
            }
        }

        function onClickHandlerFor_GetAllCheckedOutBooksForStudentAndTerm(){
            //Call checkStudentInfoInputsAndState
            checkStudentInfoInputsAndState();
            //Call respective ajax handler
        }

        function onFocusOutForBarcode(){

            //Get barcode input
            var barcode = document.getElementById("barcode");

            //If input is empty then return
            if(barcode.innerHTML=""){
                return;
            }
            //Call getTableRowFromBookCopy
            let checkIfNull = getTableRowFromBookCopy();

            if(!checkIfNull){
                //Then call ajax handler for check out book
            }
            else{
                //call ajax handler for check in book
            }

        }

        function getTableRowFromBookCopy(barcode){
            //call getBookCopyWhereBarcodeMatches
            let checkIfNull = getBookCopyWhereBarcodeMatches();
            
            //Check if input is null
            if(!checkIfNull){
                return null;
            }

            //Temporary variables
            var temp_bookcode =0;
            var temp_seqnumber= 9;
            var temp_year = 0;

            //Get all tr in table and iterate
            var rows = document.getElementsByTagName("table")[0].rows;
            
            //Iterate through the table and find a match for bookcode, sequence number, and year
            // If no match throw error
            for(let i = 0; i< rows.length; i ++){
                if(rows[i] == temp_bookcode && rows[i] == temp_seqnumber && rows[i] == temp_year  ){

                    return rows[i];

                }
                else{

                    throw 'No match found';

                }
            }

        }

        function getBookCopyWhereBarcodeMatches(barcode){
            
            //Extract book copy list from local session map

            //Check for matches. On match extract and return the map of the book

            //If no match then return null
        }   


        function checkStudentInfoInputsAndState(){
             
            //Check if inputs for student info are empty and show general error message.

            //If any inputs stored in student info are different from new inputs then reset the student
            // info object in the map.


            //If map does not retain the key then skip this. 
        }
});