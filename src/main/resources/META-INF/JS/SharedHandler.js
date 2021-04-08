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
    console.log(errorMsg);

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


    #fieldErrorName;
    #fieldErrorMessage;
    #errorData;

    static #fieldErrorNameKey = "fieldName";
    static #fieldErrorMessageKey = "message";
    static #errorDataKey = "faultyData";

    /**
     * @constructor
     *  creates new ErrorBindings object for new errors
     *  logs error to console
     * @param {1} fieldErrorName
     *  name of the error
     * @param {2} fieldErrorMessage
     *  error message
     * @param {3} errorData
     *  error data
     */


     /**
      * @error -> array
      *
      * creates an array of key : value pairs with
      * name of field and type of field
      */
    static error = [
        {
         name: ErrorBindings.#fieldErrorNameKey,
         type: "string"
        },
        {
         name: ErrorBindings.#fieldErrorMessageKey,
         type: "string"
        },
        {
         name: ErrorBindings.#errorDataKey,
         type: "string"
        }
    ];

    /**
     * Number of fields in the static array
     */
    static numberOfFields = error.length;

    ErrorBindings(){}

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
    constructor(fieldErrorName, fieldErrorMessage, errorData){

        this._fieldErrorMessage = fieldErrorMessage;
        this._fieldErrorName = fieldErrorName;
        this._errorData = errorData;
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

            const jsonObject = JSON.parse(jsonObj);

            return new ErrorBindings(jsonObject.fieldName, jsonObject.message, jsonObject.faultyData);

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

    /**
    * @method getInputTagList
    *
    * Method for creating input element and setting the name and type fields
    *
    * @return new input tag list with error bindings
    */
    getInputTagList(){
        console.log("Creating tag list");
        const inputTagList = new Array();

        for(let i = 0; i < UserInfo.numberOfFields; i++){

            const inputTag = window.document.createElement("input");

            inputTag.setAttribute("Name", ErrorBindings.error[i].name);
            inputTag.setAttribute("Type", ErrorBindings.error[i].type);

            inputTagList[i] = inputTag;


        }
        console.log("Done! Creating tag list");
        return inputTagList;
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
            return (JSON.parse(jsonObj));

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
            return (JSON.parse(jsonObj));

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
            return (JSON.parse(jsonObj));

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

