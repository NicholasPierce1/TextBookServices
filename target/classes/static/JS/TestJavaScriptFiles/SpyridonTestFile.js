import * as CHECKIN_OUTHANDLER from "./../CheckInOutHandler.js";

let testValue = 1;
export function testValue(){


    if(++testValue %2 ===0){
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