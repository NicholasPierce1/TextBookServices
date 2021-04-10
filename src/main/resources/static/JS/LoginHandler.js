// sets onload to hidden input function check
// function: renders a function that checks whether the hidden general errors input is empty
// if not then invoke alert from shared handler
window.onLoad =
    async () => {

        // acquires hidden input tag
        const generalErrorInputElement = window.document.getElementById("generalErrors");

        // if value is empty (never null) then invoke alert
        if(generalErrorInputElement.value === "")
            printError(generalErrorInputElement.value);
    }
