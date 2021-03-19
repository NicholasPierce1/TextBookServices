/**
 * @Authors Chase Staples and Spyridon Kaperonis
 * @DateCreated 03/09/21
 *
 * @Purpose
 *  To create error bindings for view composition
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


    static fieldErrorNameKey = "fieldName";
    static fieldErrorMessageKey = "message";
    static errorDataKey = "faultyData";

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

            this.fieldErrorName = fieldErrorName;
            this.fieldrrorMessage = fieldErrorMessage;
            this.errorData = errorData;
            console.log(fieldErrorName +  fieldErrorMessage + errorData)
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
            let text = JSON.parse(jsonObj);
        }
        catch{
            throw("Error parsing json object");
            console.log("Error parsing json object")
        }
    }


    /**
     * @method getFieldErrorName
     * @return
     *  Getter to return name of the error
     *
     */
    static getFieldErrorName(){
        return this.fieldErrorName;
    }

    /**
     * @method getFieldErrorMessage
     * @return
     *  Getter to return error message
     */
    static getFieldErrorMessage(){
        return this.fieldErrorMessage;
    }

    /**
     * @method getErrorData
     * @return
     *  Getter to return error data
     */
    static getErrorData(){
        return this.errorData;
    }


}