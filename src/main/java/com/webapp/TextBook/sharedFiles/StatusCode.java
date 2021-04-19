package com.webapp.TextBook.sharedFiles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum StatusCode {
    BookCopyUndefined(""),
    DataAccessConversionException("Code: DACE -- " + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    ArgumentException(""),
    UndefinedPerson(""),
    UndefinedTerm(""),
    StudentNotVerified(""),
    BookCopyLongUndefined(""),
    CheckedInBookCopyUndefined(""),
    NoCheckOutBooks(""),
    BookCopyStrikecodeUndefined(""),
    BookAlreadyCheckedOut(""),
    FailureToUpdateBookCopy(""),
    SellBookNotFound(""),
    UserNotFound(""),
    PersonCandidateKeyNotFound(""),
    DatabaseError(""),
    UserPermissionLevelInsufficient(""),
    OK("OK");

    // retains the content message that'll be given to the user as response feedback
    private final String _contentMessage;

    // retains a private static string to be the suffix of status message errors that are general
    // or abstract to the user
    private static final String _GENERAL_STATUS_MESSAGE_SUFFIX = "An internal, soft error occurred " +
            "while processing your request. Please contact IT Support.";

    StatusCode(String contentMessage){
        this._contentMessage = contentMessage;
    }

    // returns the content message based off the enum status code for response feedback
    public @NotNull String getContentMessage(){
        return this._contentMessage;
    }
}
