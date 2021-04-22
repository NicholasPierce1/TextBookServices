package com.webapp.TextBook.validation.Shared;

import java.util.regex.Pattern;

public class RegexPatternContainer {

    // enumerates static final fields to hold various patterns used in both form & api validation
    // creates three characters from ascii, decimal literals
    // 0 -> null, 32 -> space, 127 -> delete, 255 -> vacant
    private static final char nullChar = (char)0;
    private static final char spaceChar = (char)32;
    private static final char deleteChar = (char)127;
    private static final char endOfSpecialChar = (char)255;
    public static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    public static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@nwmissouri\\.edu|)$");
    public static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@nwmissouri\\.edu$");
    public static final Pattern TERM_PATTERN= Pattern.compile("^(19[8-9][0-9]|2[0-9]{3})(10|20|30)$");
    // creates regex pattern that follows flow
    // <prefix number: 307510> + 6 number + anything that's not:
    // within null-space, a semicolon (;), or delete
    public static final Pattern BARCODE_PATTERN = Pattern.compile("^307510[0-9]{6}[^"
            + nullChar + "-" + spaceChar +
            deleteChar + "-" + endOfSpecialChar +
            ";]?$");


}
