package com.webapp.TextBook.repository;

/**
 * <h1>DataAccessConversionException</h1>
 * <h2>Type: Exception Class</h2>
 *
 * Exception Class made to add a custom exception for error calls.
 */
public class DataAccessConversionException extends RuntimeException{

    /**
     * <p>
     *  Custom error throwing method that uses the RuntimeException error call as a source.
     *</p>
     *
     * @param message: String message representing the error message we want to call.
     */
    public DataAccessConversionException(String message){
        super(message);
    }
}
