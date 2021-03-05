package com.webapp.TextBook.sharedFiles;

import com.webapp.TextBook.repository.data_access.User;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class VerifySessionUser {
    //used on every api and web controller call that is NOT
    // the login page to verify the user has been validated prior.
    // This bar man in the middle attacks and injection
    private static final String ERROR_SESSION_USER_MESSAGE =
            "User state not set or does not match session. Please reset the session by logging in. Not your problem? Please contact an IT Support member.";

    private static final String ERROR_SESSION_USER_UNSET = "Session User has not been set";

    public Triplet<Boolean, String, Optional<User>> isSessionUserValid(@NotNull final User user){

        if(user == null){
            return  new Triplet<Boolean, String, Optional<User>>(
                    false,
                    ERROR_SESSION_USER_MESSAGE,
                    Optional.empty());
        }

        // wild card capture guaranteed -- type parameter will be User
        final Optional<User> userOptional = VerifySessionUser.captureWildCardUser(
                SharedSessionData.getSessionState(SharedSessionData.USER_KEY));

        if(userOptional.isEmpty())
            return new Triplet<Boolean, String, Optional<User>>(
                    false,
                    ERROR_SESSION_USER_UNSET,
                    Optional.empty());

        if(
                    !userOptional.get().getPassword().equals(user.getPassword())
                ||
                    !userOptional.get().getId().equals(user.getId())
        ){ //place holder for If any of the values in session user & user don’t match then false & static error message
            return  new Triplet<Boolean, String, Optional<User>>(
                    false,
                    ERROR_SESSION_USER_MESSAGE,
                    Optional.empty());

        }
        else
            return new Triplet<Boolean, String, Optional<User>>(
                true,
                "",
                userOptional);

    }

    // capture wild card for optional user
    @SuppressWarnings("unchecked")
    private static <T extends Optional<?>> @NotNull Optional<User> captureWildCardUser(@NotNull final T userOptional){
        try {

            return (Optional<User>)userOptional;
        }
        catch(Exception ex){
            System.out.println("Error in capturing User Optional." +
                    " Please check if Session State under User Key was properly set: " + ex);
            return Optional.empty();
        }
    }
}
