package com.webapp.TextBook.validation.Shared;


import javax.validation.constraints.NotNull;

/**
 * <h1>SharedValidationState</h1>
 *
 * <p>Holds shared state for both API and Form validation</p>
 */
public interface SharedValidationState {

    /**
     * <p>Holds the generic json error message that shall be set for both API and Form errors upon
     * failure to convert ErrorBinding into JSONArray.</p>
     */
    public static final String GENERIC_JSON_ERROR_MESSAGE = "Error in generating the corresponding " +
            "custom error message inside constraint validator -- " +
            "please check logs for more info";

    /**
     * <p>Holds generic error message that shall be set in both API and Form errors upon general/runtime errors.</p>
     *
     */
    public static final String GENERIC_ERROR_MESSAGE = "Error in validating viewmodels. Please check logs for more info.";

    /**
     * <p>Indicates if a generic error message has been given -- informs controller that
     * some error occurred within program. NOTE this only occurs IF the program's configuration is
     * awry.</p>
     * @param errorMessage: error message given from validation that may be generic or a json array string
     * @return a boolean if the error message is a generic error message (not json array string -- normal
     * response from validation on binding/validation errors).
     */
    public static boolean isGenericErrorMessage(@NotNull final String errorMessage){
        return GENERIC_JSON_ERROR_MESSAGE.equals(errorMessage) ||
                GENERIC_ERROR_MESSAGE.equals(errorMessage);
    }
}
