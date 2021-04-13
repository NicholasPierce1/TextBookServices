async function getAllCheckedOutBooksForStudentAndTermAJAX(loginUserInfo, studentInfo){
    fetch(loginUserInfo, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
           `username: ${loginUserInfo.getUserName()}`
           `password: ${loginUserInfo.getPassword()}`
        })
    })
        .then(response => {
            if(response.ok){
                //return response.json();
                refreshViewWithJsonForAllCheckedOutBooks(response.json)

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
            `ID: ${studentInfo.getID()}`
            `TermCode: ${studentInfo.getTermCode()}`

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
            console.log(`An error occured in the fetch api\nreason: ${errorReason}`);
            printError(errorReason);
            })
        .then(data => {
            console.log(data)
        });
}

async function getCheckoutBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

    fetch(loginUserInfo, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: ({
               `username: ${loginUserInfo.getUserName()}`
               `password: ${loginUserInfo.getPassword()}`
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
                `ID: ${studentInfo.getID()}`
                `TermCode: ${studentInfo.getTermCode()}`

            })
        })
            .then(response => {
                if(response.ok){
                    refreshViewWithJsonForCheckedOutBook(response.json)
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
                BarCode: `${studentBookInfo.getBarcode()}`
                StudentInfo: `${studentBookInfo.studentInfo()}`

            })
        })
            .then(response => {
                if(response.ok){
                    refreshViewWithJsonForCheckedOutBook(response.json)
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


async function getCheckInBookForStudentAndTermAJAX(loginUserInfo, studentInfo, studentBookInfo){

    fetch(loginUserInfo, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: ({
               `username: ${loginUserInfo.getUserName()}`
               `password: ${loginUserInfo.getPassword()}`
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
                `ID: ${studentInfo.getID()}`
                `TermCode: ${studentInfo.getTermCode()}`

            })
        })
            .then(response => {
                if(response.ok){
                    refreshViewWithJsonForCheckedInBook(response.json)
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
                BarCode: `${studentBookInfo.getBarcode()}`
                StudentInfo: `${studentBookInfo.studentInfo()}`

            })
        })
            .then(response => {
                if(response.ok){
                    refreshViewWithJsonForCheckedInBook(response.json)
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
           `username: ${loginUserInfo.getUserName()}`
           `password: ${loginUserInfo.getPassword()}`
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
            `ID: ${studentInfo.getID()}`
            `TermCode: ${studentInfo.getTermCode()}`

        })
    })
        .then(response => {
            if(response.ok){
                refreshViewWithJsonForSellBook(response.json)
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