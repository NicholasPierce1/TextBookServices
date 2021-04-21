import * as TEST_Chase_DEMO from "./TestJavaScriptFiles/ChaseTestFile.js";
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
 export function printError(errorMsg){

    alert("ERROR: " + errorMsg);
    console.log(errorMsg);

}


/**
 * @class ErrorBindings
 *
 * @Purpose
 *  Create error bindings and parse json object
 */
export class ErrorBindings{

    /**
     * Class Variables:
     * Names describe their values
     */


    #fieldErrorName;
    #fieldErrorMessage;
    #errorData;

    static #fieldErrorNameKey = "fieldName";
    static #fieldErrorMessageKey = "message";
    static #errorDataKey = "faultyData";
    


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
    static numberOfFields = 3;

    ErrorBindings(){

    }

    /**
     * @constructor
     *  creates new ErrorBindings object for new errors
     *  logs error to console
     * @param {string} fieldErrorName
     *  name of the error
     * @param {string} fieldErrorMessage
     *  error message
     * @param {string} errorData
     *  error data
     */
    constructor(fieldErrorName, fieldErrorMessage, errorData){

        this.fieldName = fieldErrorName;
        this.message = fieldErrorMessage;
        this.faultyData = errorData;
    }

    /**
     * @method parseInput
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param  jsonObj
     *  JSON object that was inputted
     */
    static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new ErrorBindings(jsonObject.fieldName, jsonObject.message, jsonObject.faultyData);

        }
        catch{
            throw("Error parsing json object");
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
        const inputTagList = [];

        for(let i = 0; i < ErrorBindings.numberOfFields; i++){

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

export class UserInfo{

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

    static numberOfFields = 2;

    UserInfo(){}

    /**
     * @constuctor
     *  creates new user info object
     * @param {string} username 
     *  ID for students (919#)
     * @param {number} password
     *  Their term
     *
     */

    constructor(username, password){
        this.#username = username;
        this.#password = password;
    }

    /**
     * @method parseJson
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param jsonObj
     *  JSON object that was inputted
     */

    static parseJson(jsonObj){
        try{

            return new UserInfo(jsonObj._username, jsonObj._password);

        }
        catch{
            throw("Error parsing json object");
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
        const inputTagList = [];

        for(let i = 0; i < UserInfo.numberOfFields; i++){

            const inputTag = window.document.createElement("input");

            inputTag.setAttribute("Name", UserInfo.user[i].name);
            inputTag.setAttribute("Type", UserInfo.user[i].type);

            inputTagList[i] = inputTag;


        }
        console.log("Done! Creating tag list");
        return inputTagList;
    }

    /**
    * @function createJsonForm
    * returns fields created in json form
    */
     createJsonForm(){
        return {
            _username: this.#username,
            _password: this.#password
        };
     }


 }


 /**
  * @class StudentInfo
  *
  * creating info for students with id and term code
  * getters to return id and termcode
  *
  */

export class StudentInfo{


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

    static numberOfFields = 2;

    StudentInfo(){}

    /**
     * @constructor
     *  creates new student info object
     * @param {number} ID
     *  ID for students (919#)
     * @param {number} TERM_CODE
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
     * @param jsonObj
     *  JSON object that was inputted
     */
    static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new StudentInfo(jsonObject.id, jsonObject.termCode);

        }
        catch{
            throw("Error parsing json object");
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
        const inputTagList = [];

        for(let i = 0; i < StudentInfo.numberOfFields; i++){

            const inputTag = window.document.createElement("input");

            inputTag.setAttribute("Name", StudentInfo.student[i].username);
            inputTag.setAttribute("Type", StudentInfo.student[i].type);

            inputTagList[i] = inputTag;


        }
        console.log("Done! Creating tag list");
        return inputTagList;
    }

    /**
    * @function createJsonForm
    * returns fields created in json form
    */
     createJsonForm(){
        return {
            id: this.#ID,
            termCode: this.#TERM_CODE
        };
     }
}


/**
 * @class BookCopy
 *
 * @Purpose
 *  
 */

export class BookCopy{

    //Static private variables holding keys in JSON format.
       #ID;
       #bookCode;
       #editionYear;
       #seqNr;
       #strikeBarcode;
       #pidm;
       #termCode;
       #dateCheckedOut;
       #disposition;
       #bookSalePrice;
       #prevPidm;
       #prevTermCode;
       #prevDateCheckedIn;
       #activityDate;
       #billFlag;


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
     * @method parseJson
     *  takes JSON object and parses to string
     *  throws error if an error occurred
     * @param  jsonObj
     *  JSON object that was inputted
     */
     static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new BookCopy(
                jsonObject.#ID, jsonObject.#bookCode, jsonObject.#editionYear,
                jsonObject.#seqNr, jsonObject.#strikeBarcode, jsonObject.#pidm,
                jsonObject.#termCode, jsonObject.#dateCheckedOut, jsonObject.#disposition,
                jsonObject.#bookSalePrice, jsonObject.#prevPidm, jsonObject.#prevTermCode,
                jsonObject.#prevDateCheckedIn, jsonObject.#activityDate, jsonObject.#billFlag);

        }
        catch{
            throw("Error parsing json object");
            
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
        return this.#ID;
    }

    /**
     * @method getbookCode
     * @returns bookCode
     */
    getBookCode(){
        return this.#bookCode;
    }

    /**
     * @method geteditionyear
     * @returns editionyear
     */
    getEditionYear(){
        return this.#editionYear;
    }

    /**
     * @method getseqNr
     * @returns  seqNr
     */
    getSeqNr(){
        return this.#seqNr;
    }
    /**
     * @method getStrikeBarcode
     * @returns strike barcode
     */
    getStrikeBarcode(){
        return this.#strikeBarcode;
    }
    /**
     * @method getPidm
     * @returns pidm
     */
    getPidm(){
        return this.#pidm;
    }
    /**
     * @method getTermCode
     * @returns term code
     */
    getTermCode(){
        return this.#termCode;
    }
    /**
     * @method getDateCheckedOut
     * @returns date checked out
     */
    getDateCheckedOut(){
        return this.#dateCheckedOut;
    }
    /**
     * @method getDisposition
     * @returns disposition
     */
    getDisposition(){
        return this.#disposition;
    }

    /**
     * @method getbookSalePrice
     * @returns bookSalePrice
     */
    getBookSalePrice(){
        return this.#bookSalePrice;
    }

    /**
     * @method getprevPidm
     * @returns prevPidm
     */
    getPrevPidm(){
        return this.#prevPidm;
    }

    /**
     * @method getprevTermCode
     * @returns  prevTermCode
     */
    getPrevTermCode(){
        return this.#prevTermCode;
    }

    /**
     * @method getprevDateCheckedIn
     * @returns prevDateCheckedIn
     */
    getPrevDateCheckedIn(){
        return this.#prevDateCheckedIn
    }

    /**
     * @method getactivityDate
     * @returns activityDate
     */
    getActivityDate(){
        return this.#activityDate;
    }

    /**
     * @method getbillFlag
     * @returns billFlag
     */
    getBillFlag(){
        return this.#billFlag;
    }

    /**
    * @function createJsonForm
    * returns fields created in json form
    */
    createJsonForm(){
        return{
          ID: this.#ID,
          BookCode: this.#bookCode,
          EditionYear: this.#editionYear,
          SeqNr: this.#seqNr,
          StrikeBarCode: this.#strikeBarcode,
          Pidm: this.#pidm,
          TermCode: this.#termCode,
          DateCheckOut: this.#dateCheckedOut,
          Disposition: this.#disposition,
          BookSalePrice: this.#bookSalePrice,
          PrevPidm: this.#prevPidm,
          PrevTermCode: this.#prevTermCode,
          PrevDateCheckedIn: this.#prevDateCheckedIn,
          ActivityDate: this.#activityDate,
          BillFlag: this.#billFlag
        }
    }
}


/**
 * @class Bag
 * 
 * @Purpose
 * Pidm and Bag Number
 */

export class Bag{

    #pidm;
    #bagNumber;

    /**
     * @constructor
     *  create new bag
     * @param {number} pidm
     * @param {number} bagNumber
     *
     */


    constructor(pidm, bagNumber){
        this.#pidm = pidm;
        this.#bagNumber = bagNumber;
       
    }

    static parseJson(jsonObj){
        
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new Bag(jsonObject.#pidm, jsonObject.#bagNumber)

        }
        catch{
            throw("Error parsing json object");

        }
    }


    /**
     * @method getpidm
     * @returns  pidm
     */
    getPidm(){
        return this.#pidm;
    }

    /**
     * @method getbagNumber
     * @returns  bagNumber
     */
    getBagNumber(){
        return this.#bagNumber;
    }

    /**
    * @function createJsonForm
    * returns fields created in json form
    */
     createJsonForm(){
        return {
            Pidm: this.#pidm,
            BagNumber: this.#bagNumber
        }
     }

}

/**
 * @class Term
 * 
 * @Purpose
 * Term Code and Term Description
 */

export class Term{

    //Static private values
    #termCode;
    #termDescription;

    /**
     * @constructor
     *  creates term
     * @param {number} termCode
     * @param {string} termDescription
     *
     */
    constructor(termCode, termDescription){
        this.#termCode = termCode;
        this.#termDescription = termDescription;
    }

    static parseJson(jsonObj){
        try{

            const jsonObject = JSON.parse(jsonObj);

            return new Term(jsonObject.#termCode, jsonObject.#termDescription);

        }
        catch{
            throw("Error parsing json object");
        }
    }


    /**
     * @method getTermCode
     * @returns termCode
     */
    getTermCode(){
        return this.#termCode;
    }

    /**
     * @method getTermDescription
     * @returns  termDescription
     */
    getTermDescription(){
        return this.#termDescription;
    }

    /**
    * @function createJsonForm
    * returns fields created in json form
    */
     createJsonForm(){
        return {
            TermCode: this.#termCode,
            BagNumber: this.#termDescription
        }
     }
}

