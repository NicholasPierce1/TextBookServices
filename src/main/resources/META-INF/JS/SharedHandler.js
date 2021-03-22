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