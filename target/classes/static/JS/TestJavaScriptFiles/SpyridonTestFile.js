import * as CHECKIN_OUTHANDLER from "./../CheckInOutHandler.js";

let testValue = 1;
export function testErrorMessage(){

    if(++testValue%2===0){
        CHECKIN_OUTHANDLER.errorMessage();
    }
          
}

export function testShowStatusMessageError(){
    const statusMessage = "Status is good"

    if(++testValue %2 ===0){
        CHECKIN_OUTHANDLER.showStatusMessageError(statusMessage);
    }
    else{
        CHECKIN_OUTHANDLER.hideStatusMessageError();
    }
}

export function testshowBagError(){

    if(++testValue%2==0){
        CHECKIN_OUTHANDLER.showBagError();
    }
    else{
        CHECKIN_OUTHANDLER.hideBagError();
    }
}

// export function testhandleErrorResponse(){
//     var text = '{ "employees" : [' +
//                 '{ "firstName":"John" , "lastName":"Doe" },' +
//                 '{ "firstName":"Anna" , "lastName":"Smith" },' +
//                 '{ "firstName":"Peter" , "lastName":"Jones" } ]}';
//     var jsonObject = JSON.parse(text);

//     CHECKIN_OUTHANDLER.handleErrorResponse(text);
    
// }

// export function testshowBookCopyForAllCheckedOutBooks(){
//     var bookCopy = ["Book1", "Book2", "Book3"];

//     if(++testValue%2===0){
//         CHECKIN_OUTHANDLER.showBookCopyForAllCheckedOutBooks(bookCopy);
//     }
// }

export function testhandleErrorBindings(){

    CHECKIN_OUTHANDLER.handleErrorBindings();
}

export function testshowBookCopyForCheckedOutBook(){
    var text_sample = '{ "employees" : [' +
                '{ "firstName":"John" , "lastName":"Doe" },' +
                '{ "firstName":"Anna" , "lastName":"Smith" },' +
                '{ "firstName":"Peter" , "lastName":"Jones" } ]}';

    CHECKIN_OUTHANDLER.showBookCopyForCheckedOutBook(text_sample);
}