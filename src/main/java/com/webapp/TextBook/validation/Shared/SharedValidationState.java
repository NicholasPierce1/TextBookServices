package com.webapp.TextBook.validation.Shared;


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
}
