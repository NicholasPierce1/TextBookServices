/**
 * @Author Chase Staples
 * @DateCreated 03/31/21
 *
 *
 */

async function fetchAPI(url) {
    fetch(url)
        .then(response => {
            if(response.ok){
                return response.json();
            }
            else{
                console.log("An Error Occurred")
                throw Error("Error");
            }
        })
        .then(data => {
            console.log(data);

        },
            error => {
                console.log(`Error in fetch api: ${error}`);
            })
        .catch(error =>{
            console.log(error);
        })

}

function postData(){
    fetch("/rest/api/frontEnd", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({

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
            })
        .then(data => {
            console.log(data)
        });
}