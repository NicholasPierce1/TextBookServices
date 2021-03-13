package com.webapp.TextBook.validation.Shared;

import java.util.regex.Pattern;

public class RegexPatternContainer {

    // enumerates static final fields to hold various patterns used in both form & api validation

    public static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    public static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    public static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@.*$");
}