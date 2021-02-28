package com.webapp.TextBook.sharedFiles;

import com.webapp.TextBook.repository.data_access.User;
import org.javatuples.Pair;

import javax.validation.constraints.NotNull;

public  class VerifySessionUser {
    //used on every api and web controller call that is NOT
    // the login page to verify the user has been validated prior.
    // This bar man in the middle attacks and injection
    private static final String ERROR_SESSION_USER_MESSAGE =
            "User state not set or does not match session. Please reset the session by logging in. Not your problem? Please contact an IT Support member.";
    public Pair<Boolean, String> isSessionUserValid(@NotNull final User user){
        if(user == null){
            return  new Pair<Boolean, String>(false, ERROR_SESSION_USER_MESSAGE);
        }
        else if(
                    !user.getPassword().equals(user.getPassword())
                ||
                    !user.getId().equals(user.getId())
        ){ //place holder for If any of the values in session user & user don’t match then false & static error message
            return  new Pair<Boolean, String>(false, ERROR_SESSION_USER_MESSAGE);

        }
        else return new Pair<Boolean, String>(true, "");

    }
}
