package com.webapp.TextBook.sharedFiles;

import com.webapp.TextBook.repository.data_access.User;
import org.javatuples.Pair;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class VerifySessionUser {
    //used on every api and web controller call that is NOT
    // the login page to verify the user has been validated prior.
    // This bar man in the middle attacks and injection
    private static final String ERROR_SESSION_USER_MESSAGE =
            "User state not set or does not match session. Please reset the session by logging in. Not your problem? Please contact an IT Support member.";

    private static final String ERROR_SESSION_USER_UNSET = "Session User has not been set";

    public Pair<Boolean, String> isSessionUserValid(@NotNull final User user){

        if(user == null){
            return  new Pair<Boolean, String>(false, ERROR_SESSION_USER_MESSAGE);
        }

        // wild card capture not needed -- guaranteed type parameter will be User
        final Optional<User> userOptional = (Optional<User>)SharedSessionData.getSessionState(SharedSessionData.USER_KEY);

        if(userOptional.isEmpty())
            return new Pair<Boolean, String>(false, ERROR_SESSION_USER_UNSET);

        if(
                    !userOptional.get().getPassword().equals(user.getPassword())
                ||
                    !userOptional.get().getId().equals(user.getId())
        ){ //place holder for If any of the values in session user & user don’t match then false & static error message
            return  new Pair<Boolean, String>(false, ERROR_SESSION_USER_MESSAGE);

        }
        else return new Pair<Boolean, String>(true, "");

    }
}
