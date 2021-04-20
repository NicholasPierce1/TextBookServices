import * as CHECKIN_OUTHANDLER from "./../CheckInOutHandler.js";

let testValue = 1;
export function testErrorMessage(){

        CHECKIN_OUTHANDLER.errorMessage();
    
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

export function testhandleErrorResponse(){

        CHECKIN_OUTHANDLER.handleErrorResponse(json);
    
}