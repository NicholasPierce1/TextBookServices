async function getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo){
    fetch(loginUserInfo, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
            username: "_username",
            passowrd: "_password"
        })
    })
        .then(response => {
            if(response.ok){
                return response.json();
                // should call respective handler
                // (hint: all functions that should be called start with "refreshView...."
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });

    fetch(studentInfo, {
        method: "GET",
         headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
            ID: "id",
            TermCode: "termCode"

        })
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });
}

async function getCheckoutBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

    getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo);

    fetch(studentBookInfo, {
            method: "GET",
             headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: ({
                BarCode: "barcode",
                StudentInfo: "studentInfo"

            })
        })
            .then(response => {
                if(response.ok){
                    return response.json();
                }
                else{
                    console.log("An Error Occurred")
                    throw Error("Error");
                }
            },
                errorReason => {
                console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
                printError(errorReason);
                })
            .then(data => {
                console.log(data)
            });
}

async function sellBookForStudentAJAX(loginUserInfo, studentBookInfo){
    fetch(loginUserInfo, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
            username: "_username",
            passowrd: "_password"
        })
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });

    fetch(studentBookInfo, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
            BarCode: "barcode",
            StudentInfo: "studentInfo"

        })
    })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        },
            errorReason => {
            console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });
}