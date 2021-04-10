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

 /**
  * @class UserInfo
  *
  * creating info for user and their usernames and password
  * getters to return username and password
  *
  */

class UserInfo{

    /**
     * Class Variables
     * Names describe their values
     */

    #username;
    #password;

    static #usernameKey = "_username";
    static #passwordKey = "_password";

    /**
      * @user -> array
      *
      * creates an array of key : value pairs with
      * name of field and type of field
      */

    static user = [
        {
         name: UserInfo.#usernameKey,
         type: "string"
        },
        {
         name: UserInfo.#passwordKey,
         type: "number"
        }
    ];

    /**
     * Number of fields in the static array
    */

    static numberOfFields = user.length;

    UserInfo(){}

    /**
     * @constuctor
     *  creates new user info object
     * @param {1} ID
     *  ID for students (919#)
     * @param {2} TERM_CODE
     *  Their term
     *
     */

    constructor(username, password){
        this.username = username;
        this.password = password;
    }

    /**
     * @method parseInput
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param {1} jsonObj
     *  JSON object that was inputted
     */

    static parseJson(jsonObj){
        try{
            const jsonObject = JSON.parse(jsonObj);

            return new UserInfo(jsonObject._username, jsonObject._password);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    /**
     * @method getUsername
     * @return
     *  user's username
     *
     */

    getUsername(){
        return this.#username;
    }


    /**
     * @method getPassword
     * @return
     *  user's password
     *
     */
    getPassword(){
        return this.#password;
    }

    /**
    * @method getInputTagList
    *
    * Method for creating input element and setting the name and type fields
    *
    * @return new input tag list with user info
    */
    getInputTagList(){
        console.log("Creating tag list");
        const inputTagList = new Array();

        for(let i = 0; i < UserInfo.numberOfFields; i++){

            const inputTag = window.document.createElement("input");

            inputTag.setAttribute("Name", UserInfo.user[i].name);
            inputTag.setAttribute("Type", UserInfo.user[i].type);

            inputTagList[i] = inputTag;


        }
        console.log("Done! Creating tag list");
        return inputTagList;
    }
}


 /**
  * @class StudentInfo
  *
  * creating info for students with id and term code
  * getters to return id and termcode
  *
  */

class StudentInfo{


    /**
     * Class Variables
     * Names describe their values
     */

    #ID;
    #TERM_CODE;

    static #idKey = "id";
    static #termCodeKey = "termCode"

      /**
      * @student -> array
      *
      * creates an array of key : value pairs with
      * name of field and type of field
      */

    static student = [
        {
         name: StudentInfo.#idKey,
         type: "number"
        },
        {
         name: StudentInfo.#termCodeKey,
         type: "number"
        }
    ];

    /**
     * Number of fields in the static array
     */

    static numberOfFields = student.length;

    StudentInfo(){}

    /**
     * @constuctor
     *  creates new student info object
     * @param {1} ID
     *  ID for students (919#)
     * @param {2} TERM_CODE
     *  Their term
     *
     */

    constructor(ID, TERM_CODE){
        this.#ID = ID;
        this.#TERM_CODE = TERM_CODE;
    }

    /**
     * @method parseInput
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param {1} jsonObj
     *  JSON object that was inputted
     */
    static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new StudentInfo(jsonObject.id, jsonObject.termCode);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    /**
     * @method getID
     * @return
     *  ID (919#)
     *
     */

    getID(){
        return this.#ID;
    }

    /**
     * @method getTermCode
     * @return
     *  Term Code
     *
     */

    getTermCode(){
        return this.#TERM_CODE;
    }

    /**
    * @method getInputTagList
    *
    * Method for creating input element and setting the name and type fields
    *
    * @return new input tag list with student info
    */

    getInputTagList(){
        console.log("Creating tag list");
        const inputTagList = new Array();

        for(let i = 0; i < StudentInfo.numberOfFields; i++){

            const inputTag = window.document.createElement("input");

            inputTag.setAttribute("Name", StudentInfo.student[i].username);
            inputTag.setAttribute("Type", StudentInfo.student[i].type);

            inputTagList[i] = inputTag;


        }
        console.log("Done! Creating tag list");
        return inputTagList;
    }
}


/**
 * @class BookCopy
 *
 * @Purpose
 *  
 */

class BookCopy{

    //Static private variables holding keys in JSON format.
    static #ID = {};
    static #bookCode = {};
    static #editionYear = {};
    static #seqNr = {};
    static #strikeBarcode = {};
    static #pidm = {};
    static #termCode = {};
    static #dateCheckedOut = {};
    static #disposition = {};
    static #bookSalePrice = {};
    static #prevPidm = {};
    static #prevTermCode = {};
    static #prevDateCheckedIn = {};
    static #activityDate = {};
    static #billFlag = {};


    //Constructor that takes all the inputs and creates one.
    constructor(ID, bookCode, editionyear, seqNr, strikeBarcode, pidm, termCode, dateCheckedOut,
        disposition, bookSalePrice, prevPidm, prevTermCode, prevDateCheckedIn, activityDate, billFlag){
        this.#ID = ID;
        this.#bookCode = bookCode;
        this.#editionYear = editionyear;
        this.#seqNr = seqNr;
        this.#strikeBarcode = strikeBarcode;
        this.#pidm = pidm;
        this.#termCode = termCode;
        this.#dateCheckedOut = dateCheckedOut;
        this.#disposition = disposition;
        this.#bookSalePrice = bookSalePrice;
        this.#prevPidm = prevPidm;
        this.#prevTermCode = prevTermCode;
        this.#prevDateCheckedIn = prevDateCheckedIn;
        this.#activityDate = activityDate;
        this.#billFlag = billFlag;
    }


    /**
     * @method parseInput
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param {1} jsonObj
     *  JSON object that was inputted
     */
     static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new BookCopy(jsonObject.ID, jsonObject.bookCode, jsonObject.editionyear, jsonObject.seqNr,
                jsonObject.strikeBarcode, jsonObject.pidm, jsonObject.termCode, jsonObject.dateCheckedOut, 
                jsonObject.disposition, jsonObject.bookSalePrice, jsonObject.prevPidm, jsonObject.prevTermCode,
                jsonObject.prevDateCheckedIn, jsonObject.activityDate, jsonObject.billFlag);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }


    /**
     * GETTERS 
     */



    /**
     * @method getID
     * @returns ID 
     */
    getID(){
        return this.ID;
    }

    /**
     * @method getbookCode
     * @returns bookCode
     */
    getbookCode(){
        return this.#bookCode;
    }

    /**
     * @method geteditionyear
     * @returns editionyear
     */
    geteditionyear(){
        return this.editionyear;
    }

    /**
     * @method getseqNr
     * @returns  seqNr
     */
    getseqNr(){
        return this.seqNr;
    }

    /**
     * @method getdispsition
     * @returns disposition
     */
    getdisposition(){
        return this.disposition;
    }

    /**
     * @method getbookSalePrice
     * @returns bookSalePrice
     */
    getbookSalePrice(){
        return this.bookSalePrice;
    }

    /**
     * @method getprevPidm
     * @returns prevPidm
     */
    getprevPidm(){
        return this.prevPidm;
    }

    /**
     * @method getprevTermCode
     * @returns  prevTermCode
     */
    getprevTermCode(){
        return this.prevTermCode;
    }

    /**
     * @method getprevDateCheckedIn
     * @returns prevDateCheckedIn
     */
    getprevDateCheckedIn(){
        return this.prevDateCheckedIn
    }

    /**
     * @method getactivityDate
     * @returns activityDate
     */
    getactivityDate(){
        return this.activityDate;
    }

    /**
     * @method getbillFlag
     * @returns billFlag
     */
    getbillFlag(){
        return this.billFlag;
    }

}

/**
 * @class Bag
 * 
 * @Purpose
 */

 class Bag{

    static #pidm = {};
    static #bagNumber = {};
    


    constructor(){
        this.pidm = pidm;
        this.bagNumber = bagNumber;
       
    }

    

    static parseInput(jsonObj){
        
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new Bag(jsonObject.pidm, jsonObject.bagNumber)

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }


    /**
     * @method getpidm
     * @returns  pidm
     */
    getpidm(){
        return this.pidm;
    }

    /**
     * @method getbagNumber
     * @returns  bagNumber
     */
    getbagNumber(){
        return this.bagNumber;
    }

}

/**
 * @class
 * 
 * @Purpose
 */

 class Term{

    //Static private values
    static #termCode;
    static #termDescription;
    
    //Constructor that takes all inputs
    constructor(){
        this.termCode = termCode;
        this.termDescription = termDescription;
    }

    static parseInput(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return (jsonObject.termCode, jsonObject.termDescription);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }


    /**
     * @method gettermCode
     * @returns termCode
     */
    gettermCode(){
        return this.termCode;
    }

    /**
     * @method gettermDescription
     * @returns  termDescription
     */
    gettermDescription(){
        return this.termDescription;
    }
}

