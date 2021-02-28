package com.webapp.TextBook.sharedFiles;

import java.util.Map;

public  class SharedSessionData {
    private static Map<String, Object> SHARED_SESSION_DATA_MAP;

    public static Map<String, Object> GET_SHARED_SESSION_DATA_MAP(){
        return SHARED_SESSION_DATA_MAP;
    }
    public static String USER_KEY;

}
