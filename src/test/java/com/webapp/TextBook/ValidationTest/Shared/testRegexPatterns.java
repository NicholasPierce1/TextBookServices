package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.validation.Shared.RegexPatternContainer;
import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * Tests Regex patterns. From the RegexPatternContainerClass
 */
public class testRegexPatterns {
    String test = "";
    /**
     * Will test student ID pattern with one valid and one invalid test
     */
    @Test
    public void testStudentID(){
       //Bad 919 with obviously bad 919... but good song lyrics
        test = "8675309";
        assert(assertNotTrue(test, RegexPatternContainer.STUDENT_ID_PATTERN));
        //Good student ID
        test = "919545753";

        assert(assertTrue(test, RegexPatternContainer.STUDENT_ID_PATTERN));
    }

    /**
     * Testing sNumber pattern for prefix
     */
    @Test
    public void testSNumberPrefix(){
        //Good eamil with suffix
        assert (assertTrue("s529236@nwmissouri.edu", RegexPatternContainer.S_NUMBER_PREFIX));
        //Good email with suffix and capital S
        assert (assertTrue("S529236@nwmissouri.edu", RegexPatternContainer.S_NUMBER_PREFIX));
        //Good email without suffix
        assert (assertTrue("s529236", RegexPatternContainer.S_NUMBER_PREFIX));
        //Good eamil no suffix capital s
        assert (assertTrue("S529236", RegexPatternContainer.S_NUMBER_PREFIX));


        //Bad email with suffix: illegal letter
        assert (assertNotTrue("n529236@nwmissouri.edu", RegexPatternContainer.S_NUMBER_PREFIX));
        //Bad email with suffix: invalid form
        assert (assertNotTrue("s52923666@nwmissouri.edu", RegexPatternContainer.S_NUMBER_PREFIX));
        //Bad email without suffix: Illegal letter
        assert (assertNotTrue("n529236", RegexPatternContainer.S_NUMBER_PREFIX));
        //Bad email without suffix: invalid form
        assert (assertNotTrue("s529236666", RegexPatternContainer.S_NUMBER_PREFIX));





    }

    /**
     * Testing sNumber pattern  for valid Suffix
     * Assumes prefix test works
     */
    @Test
    public void testSNumberSuffix(){
        //Test for valid suffix. What is before the @ does not matter
        assert(assertTrue("1234567@nwmissouri.edu",RegexPatternContainer.S_NUMBER_SUFFIX));
        //Tst for invalid suffix: no at symbol
        assert(assertNotTrue("`1234567nwmissouri.edu",RegexPatternContainer.S_NUMBER_SUFFIX));
        //Test for invalid suffix: bad url
        assert(assertNotTrue("`1234567@nwmissuri.edu",RegexPatternContainer.S_NUMBER_SUFFIX));
        //Test for invalid suffix: Bad domain extension
        assert (assertNotTrue("7654321@nwmissouri.com",RegexPatternContainer.S_NUMBER_SUFFIX));


    }

    /**
     * Testing term pattern
     */
    @Test
    public void testTermCode(){
        //Good prefix 19
        assert(assertTrue("199910", RegexPatternContainer.TERM_PATTERN));
        assert (assertTrue("198930", RegexPatternContainer.TERM_PATTERN));
        //Good 20
        assert (assertTrue("202020", RegexPatternContainer.TERM_PATTERN));
        assert (assertTrue("200010", RegexPatternContainer.TERM_PATTERN));
        //Bad pattern: too early
        assert(assertNotTrue("188810", RegexPatternContainer.TERM_PATTERN));
        //Bad pattern: term code
        assert (assertNotTrue("202040", RegexPatternContainer.TERM_PATTERN));
        //Bad Patern: too short
        assert (assertNotTrue("20", RegexPatternContainer.TERM_PATTERN));
        //Bad Pattern: too long
        assert (assertNotTrue("20202020", RegexPatternContainer.TERM_PATTERN));
    }
    /**
     * Barcode was tested already. Pasting code in here.
     * Will test the Barcode against various conditions.
     */
    @Test
    public void testBarcodeRegexBetter(){



        // creates regex pattern that follows flow
        Pattern barcodePattern = RegexPatternContainer.BARCODE_PATTERN;

        // creates array of good input to pass
        final String[] patternData =
                {"307510209733/", "307510209734+", "307510209735%",
                        "3075102097360", "307510209732$", "307510122471X",
                        "307510122493.", "307510056522-", "307510056522!", "3075100565223",
                        "307510209732$", "307510209732"
                };

        // asserts good input matches pattern
        for(String data: patternData)
            assert(barcodePattern.matcher(data).matches());

        // creates array of faulty/injection data
        final String[] injectionBadData = {"307510209733;", "307510209733\n", "307510209733£"};

        // asserts faulty data fails to match
        for(String data: injectionBadData)
            assert(!barcodePattern.matcher(data).matches());

    }
    /**
     * Small helper methods to reduce repettive code
     * @param str
     * @param pattern
     * @return
     */

    private boolean assertTrue(String str, Pattern pattern){
        return (str.matches(pattern.pattern()));
    }
    private boolean assertNotTrue(String str, Pattern pattern){
        return (!str.matches(pattern.pattern()));
    }



}
