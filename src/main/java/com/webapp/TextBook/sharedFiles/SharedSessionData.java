package com.webapp.TextBook.sharedFiles;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SharedSessionData {
    private static final Map<String, Object> SHARED_SESSION_DATA_MAP = new HashMap<String, Object>();

    private static final String[] SESSION_STATE_KEYS = {SharedSessionData.USER_KEY};

    public static final String USER_KEY = "USER_KEY";

    // returns the optional value retaining the desired, upcasted type of the shared object
    // enclosed within the shared session state
    public static @NotNull Optional<?> getSessionState(@NotNull String sessionKey)
    throws RuntimeException{

        // validation check on session key -- if not within list of accepted keys then throw exception
        if(Arrays.binarySearch(SharedSessionData.SESSION_STATE_KEYS, sessionKey) == -1)
            throw new RuntimeException("Session Key provided is not currently tracked by session state");

        // if session hash map does not currently hap the key configured/set then empty optional
        if(!SharedSessionData.SHARED_SESSION_DATA_MAP.containsKey(sessionKey))
            return Optional.empty();

        // guaranteed to not be null -- checked when set
        return Optional.of(SharedSessionData.SHARED_SESSION_DATA_MAP.get(sessionKey));
    }

    // sets a value pair for a session state provided that the key is one of the
    // session state that is targeted within such scope
    // CURRENTLY: once a pair is sec it cannot be reset or unset
    // CURRENTLY: value cannot be null
    public static <T> void setSessionValueWithKey(@NotNull String sessionKey, T value){

        // validation check on value -- if null then exception
        if(value == null)
            throw new RuntimeException("Value attempted to be set is null");

        // validation check on session key -- if not within list of accepted keys then throw exception
        if(Arrays.binarySearch(SharedSessionData.SESSION_STATE_KEYS, sessionKey) == -1)
            throw new RuntimeException("Session Key provided is not currently tracked by session state");

        // if session hash map does not currently hap the key configured/set then throw exception
        if(SharedSessionData.SHARED_SESSION_DATA_MAP.containsKey(sessionKey))
            throw new RuntimeException("Session Key has already been set -- current rules" +
                    "bar the resetting or unsetting of session state.");

        SharedSessionData.SHARED_SESSION_DATA_MAP.put(sessionKey, value);
    }

    // adds a method to remove session state
    public static void removeSessionValueWithKey(@NotNull final String sessionKey) throws RuntimeException{

        // validation check on session key -- if not within list of accepted keys (or in session map currently) then throw exception
        if(
                Arrays.binarySearch(SharedSessionData.SESSION_STATE_KEYS, sessionKey) == -1
                        ||
                !SharedSessionData.SHARED_SESSION_DATA_MAP.containsKey(sessionKey))
            throw new RuntimeException("Session Key provided is not currently tracked by session state");

        // invokes session map to remove key-value pair where key matches
        SharedSessionData.SHARED_SESSION_DATA_MAP.remove(sessionKey);
    }

}