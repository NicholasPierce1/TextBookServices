/**
 * @Authors Chase Staples and Spyridon Kaperonis
 * @DateCreated 03/09/21
 *
 *
 */

/**
 * @function printError
 *  prints error message to the screen
 *
 * @param errorMsg
 *  error message itself
 */
function printError(errorMsg){

    alert("ERROR: " + errorMsg);

}


/**
 * @class ErrorBindings
 *
 * @Purpose
 *  Create error bindings and parse json object
 */

class ErrorBindings{

    /**
     * Class Variables
     * Names describe their values
     */
    static #fieldErrorNameKey = "fieldName";
    static #fieldErrorMessageKey = "message";
    static #errorDataKey = "faultyData";

    #fieldErrorName;
    #fieldErrorMessage;
    #errorData;

    /**
     * @constuctor
     *  creates new ErrorBindings object for new errors
     *  logs error to console
     * @param {1} fieldErrorName
     *  name of the error
     * @param {2} fieldErrorMessage
     *  error message
     * @param {3} errorData
     *  error data
     */


    constructor(){

            this.fieldErrorName = fieldErrorNameKey;
            this.fieldErrorMessage = fieldErrorMessageKey;
            this.errorData = errorDataKey;
            console.log(fieldErrorName +  fieldErrorMessage + errorData);
    }

    /**
     * @method parseInput
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param {1} jsonObj
     *  JSON object that was inputted
     */
    static parseInput(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }


    /**
     * @method getFieldErrorName
     * @return
     *  Getter to return name of the error
     *
     */
    getFieldErrorName(){
        return this.#fieldErrorName;
    }

    /**
     * @method getFieldErrorMessage
     * @return
     *  Getter to return error message
     */
    getFieldErrorMessage(){
        return this.#fieldErrorMessage;
    }

    /**
     * @method getErrorData
     * @return
     *  Getter to return error data
     */
    getErrorData(){
        return this.#errorData;
    }


}

function parseJson(jsonObj){
    try{
        return (JSON.parse(jsonObj)).toString();

    }
    catch{
        throw("Error parsing json object");
        console.log("Error parsing json object");
    }
}

class UserInfo{

    constructor(){

    }

    static parseJson(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

}



class StudentInfo{


    constructor(){

    }

    static parseJson(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

}

/**
 * 1.A
 */
liMap = new Map();
userInfoMap = new Map();

/**
* 1.C
*/

function get_list_items(){

    ulList = document.getElementsByTagName('ul');

    for(let i = 0; i < ulList.length; i++){

        let menu = ulList[i].id;

        if(menu == undefined && menu.length == 0){
            console.log(`Menu: ${menu}`);
        }

        liList = ulList[i].getElementsByTagName('li');

        for(let j = 0; j < liList.length; j++){

            let menu = window.sessionStorage.getItem('nav');;
            //console.log(menu);
            let submenu = liList[j].id;
            //console.log(submenu);

            let key = liList[j].id;
            let value = "/" + liList[j].id;

            //console.log(`Menu: ${menu} \n Submenu: ${submenu}`);

            liMap.set(`${menu}${key}`, `${value}`);
        }
    }

    console.log(liMap);
}

function getURL(url){

    const name = url.srcElement.innerHTML;
    const submenu = url.srcElement.parentElement.id;
    const navbar = window.sessionStorage.getItem('nav');

    console.log(`Name: ${name}`);
    console.log(`Navbar: ${navbar}`)
    console.log(`Submenu: ${submenu}`);

    const key = `${navbar}${submenu}`;

    console.log(`key: ${key}`);
    console.log('Value:', liMap.get(key));

}

/**
 * 1
 */

window.onload = () => {
    console.log("Navbar:", document.getElementsByTagName('nav')[0].id);
    if((document.getElementsByTagName('nav')[0].id) == 'Student'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Student Menu");
    }
    if((document.getElementsByTagName('nav')[0].id) == 'Supervisor'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Supervisor Menu");
    }

    get_list_items();

    liList = document.getElementsByTagName('li');

    for(let i = 0; i < liList.length; i++){
        //liList[i].onclick = createManualForm(`${liList[i].innerHTML.id}`);
        liList[i].onclick = getURL
        //submitManualForm();
        /**
        * 1.D
        */

    }

    /**
     * 1.B
     */
    // document.getElementById("data").innerHTML = userInfoMap;




}


/**
 * @class BookCopy
 *
 * @Purpose
 *  
 */

class BookCopy{

    static BOOK_TITLE = Pattern.compile("");
    static BOOK_ISBN = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{5}-[0-9]{2}-[0-9]{1}$");
    


    constructor(){
        this.BOOK_TITLE = this.BOOK_TITLE;
        this.BOOK_ISBN = this.BOOK_ISBN;
    }

    static parseInput(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    geBOOK_TITLE(){
        return this.BOOK_TITLE;
    }
    
    getBOOK_ISBN(){
        return this.BOOK_ISBN;
    }


}

/**
 * @class Bag
 * 
 * @Purpose
 */

 class Bag{

    static STUDENT_ID = Pattern.compile("^919[0-9]{6}$");
    static BAG_NUMBER = Pattern.compile("^#[0-9]{5}$");
    


    constructor(){
        this.STUDENT_ID = this.STUDENT_ID;
        this.BAG_NUMBER = this.BAG_NUMBER;
       
    }


    static parseInput(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    getSTUDENT_ID(){
        return this.STUDENT_ID;
    }

    getBAG_NUMBER(){
        return this.BAG_NUMBER;
    }

}

/**
 * @class
 * 
 * @Purpose
 */

 class Term{

    static TERM = Pattern.compile("");
    static YEAR = Pattern.compile("");

    constructor(){
        this.TERM = this.TERM;
        this.YEAR = this.YEAR;
    }

    static parseInput(jsonObj){
        try{
            return (JSON.parse(jsonObj)).toString();

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }


    getTERM(){
        return this.TERM;
    }
    getYEAR(){
        return this.YEAR;
    }
}

