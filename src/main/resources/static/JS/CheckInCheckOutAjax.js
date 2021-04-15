let localHostPrefix = "http://localhost:8080/rest/api/inventory";

async function getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo) {
    fetch(`${localHostPrefix}/getCheckedOutBooks`, {
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
                        refreshViewWithJsonForAllCheckedOutBooks(response.json)

                    } else {
                        console.log("An Error Occurred")
                        throw Error("Error");
                    }
                },
                errorReason => {
                    console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                    printError(errorReason);
                })
            .then(data => {
                console.log(data)
            })
    });
}

    fetch(`${localHostPrefix}/getCheckedOutBooks`, {
        method: "GET",
         headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({ //todo: nick stuff pt. 2
            loginUserInfo: loginUserInfo.createJsonForm(),
            studentInfo: studentInfo.createJsonForm()
        })
    })
        .then(response => {
            if(response.ok){
                refreshViewWithJsonForAllCheckedOutBooks(response.json)
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });
}

async function getCheckoutBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

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
                    refreshViewWithJsonForCheckedOutBook(response.json)

                }
                else{
                    console.log("An Error Occurred")
                    throw Error("Error");
                }
            },
                errorReason => {
                console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                printError(errorReason);
                })
            .then(data => {
                console.log(data)
            });


}


async function getCheckInBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

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
                    refreshViewWithJsonForCheckedInBook(response.json)

                }
                else{
                    console.log("An Error Occurred")
                    throw Error("Error");
                }
            },
                errorReason => {
                console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
                printError(errorReason);
                })
            .then(data => {
                console.log(data)
            });


}

async function sellBookForStudentAJAX(loginUserInfo, studentBookInfo){
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
                refreshViewWithJsonForSellBook(response.json)

            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occurred in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });


}