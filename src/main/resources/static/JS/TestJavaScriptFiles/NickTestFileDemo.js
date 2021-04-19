import * as CHECKIN_OUTHANDLER from "./../CheckInOutHandler.js";

let testStatusCodeInt = 1;
// test function for show status code error
export function testStatusCodeErrorOnClick(event){

    // enumerates dummy state to invoke target function (showStatusCodeError/hide)
    const errorMsg = "test error message";

    if(++testStatusCodeInt % 2 === 0) // even clicks
        CHECKIN_OUTHANDLER.showStatusCodeError(errorMsg);
    else
        CHECKIN_OUTHANDLER.hideStatusCodeError();
}