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
    static fieldErrorNameKey = "fieldName";
    static fieldErrorMessageKey = "message";
    static errorDataKey = "faultyData";

    fieldErrorName = fieldErrorNameKey;
    fieldErrorMessage = fieldErrorMessageKey;
    errorData = errorDataKey;

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


    constructor(){

            this.fieldErrorName = fieldErrorName;
            this.fieldErrorMessage = fieldErrorMessage;
            this.errorData = errorData;
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
            return JSON.parse(jsonObj);

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
        return this.fieldErrorName;
    }

    /**
     * @method getFieldErrorMessage
     * @return
     *  Getter to return error message
     */
    getFieldErrorMessage(){
        return this.fieldErrorMessage;
    }

    /**
     * @method getErrorData
     * @return
     *  Getter to return error data
     */
    getErrorData(){
        return this.errorData;
    }


}

class UserInfo{

    static STUDENT_ID_PATTERN = Pattern.compile("^919[0-9]{6}$");
    static S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    static S_NUMBER_SUFFIX = Pattern.compile("^.{7}@.*$");

    haveSuffix;

    constructor(){
        this.STUDENT_ID_PATTERN = this.STUDENT_ID_PATTERN;
        this.S_NUMBER_PREFIX = this.S_NUMBER_PREFIX;
        this.S_NUMBER_SUFFIX = this.S_NUMBER_SUFFIX;
    }

    static parseInput(jsonObj){
        try{
            return JSON.parse(jsonObj);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    getSTUDENT_ID_PATTERN(){
        return this.STUDENT_ID_PATTERN;
    }

    getS_NUMBER_PREFIX(){
        return this.S_NUMBER_PREFIX;
    }

    getS_NUMBER_SUFFIX(){
        return this.S_NUMBER_SUFFIX;
    }
}

class StudentInfo{

    static STUDENT_ID_PATTERN = Pattern.compile("^919[0-9]{6}$");
    static S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    static S_NUMBER_SUFFIX = Pattern.compile("^.{7}@.*$");


    constructor(){
        this.STUDENT_ID_PATTERN = this.STUDENT_ID_PATTERN;
        this.S_NUMBER_PREFIX = this.S_NUMBER_PREFIX;
        this.S_NUMBER_SUFFIX = this.S_NUMBER_SUFFIX;
    }

    static parseInput(jsonObj){
        try{
            return JSON.parse(jsonObj);

        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object");
        }
    }

    getSTUDENT_ID_PATTERN(){
        return this.STUDENT_ID_PATTERN;
    }

    getS_NUMBER_PREFIX(){
        return this.S_NUMBER_PREFIX;
    }

    getS_NUMBER_SUFFIX(){
        return this.S_NUMBER_SUFFIX;
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
            return JSON.parse(jsonObj);

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

