package com.webapp.TextBook.validation.Shared;

import java.util.regex.Pattern;

public class RegexPatternContainer {

    // enumerates static final fields to hold various patterns used in both form & api validation

    public static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    public static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    public static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@.*$");
    public static final Pattern TERM_PATTERN= Pattern.compile("^(19[8-9][0-9]|2[0-9]{3})(10|20|30)$");
    public static final Pattern BARCODE_PATTERN= Pattern.compile("^317510[0,1][0-9]{5}[" +
            " Q,S,T,U,V,W,X,Y,Z,-,.,?,+.$,%,$,//, ] $");
}
