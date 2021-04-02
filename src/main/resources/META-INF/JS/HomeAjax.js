/**
 * @Author Chase Staples
 * @DateCreated 03/31/21
 *
 *
 */

async function fetchAPI(li) {
    let url = "/" + li.srcElement.parentElement.id;
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
    postData(url);
}

function postData(url){
    const name = url.srcElement.innerHTML;
    const submenu = url.srcElement.parentElement.id;
    const navbar = window.sessionStorage.getItem('nav');

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: ({
            name: name;
            submenu: submenu;
            navbar: navbar;
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