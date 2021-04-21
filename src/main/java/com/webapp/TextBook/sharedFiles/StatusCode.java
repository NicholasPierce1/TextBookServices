package com.webapp.TextBook.sharedFiles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum StatusCode {
    BookCopyUndefined(""),
    DataAccessConversionException("Code: DACE -- " + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    ArgumentException("Code: AE --" + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    UndefinedPerson("The person you are trying to lookup cannot be found"),
    UndefinedTerm("The term you are looking for cannot be found"),
    StudentNotVerified("This student has not completed the verification process"),
    BookCopyLongUndefined("CODE: BCLU--" + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    CheckedInBookCopyUndefined("The book you are trying to check out cannot be found"),
    NoCheckOutBooks("This student has no books to checkout for this term"),
    BookCopyStrikecodeUndefined("This strike code cannot be found, or has yet to be created"),
    BookAlreadyCheckedOut("This book has already been checked out"),
    FailureToUpdateBookCopy("CODE FUBC-- " + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    SellBookNotFound("The book copy you are trying to sell cannot be found"),
    UserNotFound("This user has not been created yet"),
    PersonCandidateKeyNotFound("This 919 number does belong to a student"),
    DatabaseError("Code: DBE-- " + StatusCode._GENERAL_STATUS_MESSAGE_SUFFIX),
    UserPermissionLevelInsufficient("You do not have permission to view this page or preform this action"),
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
