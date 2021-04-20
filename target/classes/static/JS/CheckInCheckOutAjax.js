let localHostPrefix = "http://localhost:8080/rest/api/inventory";


import * as CHECK_IN_OUT_HANDLER from "./CheckInOutHandler.js";
import * as SHARED from "./SharedHandler.js";

export async function getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo) {
    await fetch(`${localHostPrefix}/getCheckedOutBooks`, {

        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({ //todo: nick stuff pt. 2
            loginUserInfo: loginUserInfo.createJsonForm(),
            studentInfo: studentInfo.createJsonForm()
        })
            .then(response => {
                    if (response.ok) {
                        //return response.json();
                        CHECK_IN_OUT_HANDLER.refreshViewWithJsonForAllCheckedOutBooks(response.json)

                    } else {
                        console.log("An Error Occurred")
                        throw Error("Error");
                    }
                },
                errorReason => {
                    console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                    SHARED.printError(errorReason);
                })
            .then(data => {
                console.log(data)
            })
    });
}

export async function getCheckoutBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

    fetch(`${localHostPrefix}/checkoutBook`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: ({
               loginUserInfo: loginUserInfo.createJsonForm(),
               studentInfo: studentInfo.createJsonForm(),
               studentBookInfo: studentBookInfo.createJsonForm()
            })
        })
            .then(response => {
                if(response.ok){
                    //return response.json();
                    CHECK_IN_OUT_HANDLER.refreshViewWithJsonForCheckedOutBook(response.json)

                }
                else{
                    console.log("An Error Occurred")
                    throw Error("Error");
                }
            },
                errorReason => {
                console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                    SHARED.printError(errorReason);
                })
            .then(data => {
                console.log(data)
            });


}


export async function getCheckInBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

    fetch(`${localHostPrefix}/checkInBook`, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: ({
               loginUserInfo: loginUserInfo.createJsonForm(),
               studentInfo: studentInfo.createJsonForm(),
               studentBookInfo: studentBookInfo.createJsonForm()
            })
        })
            .then(response => {
                if(response.ok){
                    //return response.json();
                    CHECK_IN_OUT_HANDLER.refreshViewWithJsonForCheckedInBook(response.json)

                }
                else{
                    console.log("An Error Occurred")
                    throw Error("Error");
                }
            },
                errorReason => {
                console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                    SHARED.printError(errorReason);
                })
            .then(data => {
                console.log(data)
            });


}

export async function sellBookForStudentAJAX(loginUserInfo, studentBookInfo){
    fetch(`${localHostPrefix}/sellBook`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
           loginUserInfo: loginUserInfo.createJsonForm(),
           studentBookInfo: studentBookInfo.createJsonForm()
        })
    })
        .then(response => {
            if(response.ok){
                //return response.json();
                CHECK_IN_OUT_HANDLER.refreshViewWithJsonForSellBook(response.json)

            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
                console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                SHARED.printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });


}