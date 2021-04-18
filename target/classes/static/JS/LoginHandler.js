// sets onload to hidden input function check
// function: renders a function that checks whether the hidden general errors input is empty
// if not then invoke alert from shared handler

// imports method definition from shared handler
import {printError} from "./SharedHandler.js";

window.onload =
     () => {
        console.log("inside js file for login!!");
        // acquires hidden input tag
        const generalErrorInputElement = window.document.getElementById("generalErrors");

        // if value is not empty (never null) then invoke alert (general error occurred)
        if(generalErrorInputElement.value)
            printError(generalErrorInputElement.value);

    }

