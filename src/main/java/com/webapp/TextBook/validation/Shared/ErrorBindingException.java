package com.webapp.TextBook.validation.Shared;

import javax.validation.constraints.NotNull;

public class ErrorBindingException extends RuntimeException {
    //Creation of ErrorBindingException
    public ErrorBindingException(@NotNull ErrorBinding errorBinding){
        super("error in creating JSONObject from this ErrorBinding " + errorBinding);
    }

}
