import * as SHARED from "./../SharedHandler.js";
import * as AJAX from "./../CheckInCheckOutAjax.js";

//Shared Handlers Test

//Test ErrorBindings Methods
const data = new SHARED.ErrorBindings("x", "y", "z")

export function testGetFieldErrorName(){
    let name = data.getFieldErrorName();
    console.log(name);
}
export function testGetFieldMessage(){
    let message = data.getFieldErrorMessage();
    console.log(message);
}
export function testGetErrorData(){
    let error = data.getErrorData();
    console.log(error);
}
export function testEBGetInputTagList(){
    let list = data.getInputTagList();
    console.log(list);
}
// --------------------------------------------------------------------------------- //

//Test UserInfo Methods
let user = new SHARED.UserInfo("chase", 1234);

export function testGetUsername(){
    let name = user.getUsername();
    console.log(name);
}
export function testGetPassword(){
    let name = user.getPassword();
    console.log(name);
}
export function testUIGetInputTagList(){
    let list = user.getInputTagList();
    console.log(list);
}
export function testUserCreateJsonForm(){
    let form = user.createJsonForm();
    console.log(form);
}
// --------------------------------------------------------------------------------- //

//Test StudentInfo Methods
let student = new SHARED.StudentInfo(919, 2021);

export function testGetID(){
    let ID = student.getID();
    console.log(ID);
}
export function testGetTermCode(){
    let termCode = student.getTermCode();
    console.log(termCode);
}
export function testSIGetInputTagList(){
    let list = student.getInputTagList();
    console.log(list);
}
export function testStudentCreateJsonForm(){
    let form = student.createJsonForm();
    console.log(form);
}

// --------------------------------------------------------------------------------- //

// Test BookCopy

let bookCopy = new SHARED.BookCopy(1,2,3,4,5,
    6,7,8,9,0,1,2,3,4,5)
export function testBookCopyGetID(){
    let id = bookCopy.getID();
    console.log(id);
}
export function testBookCopyGetBookCode(){
    let bookCode = bookCopy.getBookCode();
    console.log(bookCode);
}
export function testBookCopyGetEditionYear(){
    let editionYear = bookCopy.getEditionYear();
    console.log(editionYear);
}
export function testBookCopyGetSeqNr(){
    let seqNr = bookCopy.getSeqNr();
    console.log(seqNr);
}
export function testBookCopyGetStrikeBarcode(){
    let sbc = bookCopy.getStrikeBarcode();
    console.log(sbc);
}
export function testBookCopyGetPidm(){
    let pidm = bookCopy.getPidm();
    console.log(pidm);
}
export function testBookCopyGetTermCode(){
    let termCode = bookCopy.getTermCode();
    console.log(termCode);
}
export function testBookCopyGetDateCheckedOut(){
    let checkOut = bookCopy.getDateCheckedOut();
    console.log(checkOut);
}
export function testBookCopyGetDisposition(){
    let dis = bookCopy.getDisposition();
    console.log(dis);
}
export function testBookCopyGetBookSalesPrice(){
    let bsp = bookCopy.getBookSalePrice();
    console.log(bsp);
}
export function testBookCopyGetPrevPidm(){
    let prevPidm = bookCopy.getPrevPidm();
    console.log(prevPidm);
}
export function testBookCopyGetPrevDataCheckedIn(){
    let dci = bookCopy.getPrevDateCheckedIn();
    console.log(dci);
}
export function testBookCopyGetActivityDate(){
    let ad = bookCopy.getActivityDate();
    console.log(ad);
}
export function testBookCopyBillFlag(){
    let bf = bookCopy.getBillFlag();
    console.log(bf);
}
export function testBookCopyCreateJsonForm(){
    let form = bookCopy.createJsonForm();
    console.log(form);
}
// --------------------------------------------------------------------------------- //


// Test Bag

let bag = new SHARED.Bag(1,1234);

export function testBagGetPidm(){
    let pidm = bag.getPidm();
    console.log(pidm);
}
export function testBagGetBagNumber(){
    let bagNum = bag.getBagNumber();
    console.log(bagNum);
}
export function testBagCreateJsonForm(){
    let form = bag.createJsonForm();
    console.log(form);
}

// --------------------------------------------------------------------------------- //


// Test Term
let term = new SHARED.Term(2021, "Fall");

export function testTermGetTermCode(){
    let termCode = term.getTermCode();
    console.log(termCode);
}
export function testTermGetTermDescription(){
    let termCode = term.getTermDescription();
    console.log(termCode);
}
export function testTermCreateJsonForm(){
    let form = term.createJsonForm();
    console.log(form);
}

// --------------------------------------------------------------------------------- //

//Testing AJAX calls

