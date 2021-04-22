package com.webapp.TextBook.ValidationTest.ApiValidationTest.StudentInfoValidationTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Testing StudentInfoValidatin
 * UsesSpringBootTest framework
 */
@SpringBootTest
public class StudentInfoValidationImplTest {

    // injection for api validation for manual validation triggers
    @Autowired
    private ApiValidationHandler validator;

    /**
     * Going to test the isValid method for Student Info.
     * We will use four cases
     */


    /**
     * Tests for valid input
     * Checks to make sure there are no error Bindings
     */

    @Test
    public void testValidStudent(){
        // creates targeted inputs (input fields valid) --> one has suffix and a capital 's' while other is converse
        final StudentInfo validStudentInput = new StudentInfo("919545753", "202020");


        // invokes api validation handler and captures validation result

        final Optional<String> validStudnetInfoResult = this.validator.getApiBindingError(validStudentInput);


        // denotes transformation input result upon passwords (email suffix should be spliced)

        // asserts no errors uncovered (optional is empty) and password transformation is of expected

        // Testing to make sure list is empty
        assert(validStudnetInfoResult.isEmpty());


    }

    /**
     * Test for invalid input: Bad ID
     * Tests that there is an error and that such error has the expected language
     */
    @Test
    public void testInvalidId() throws  Exception{
        //Clearly invalid 919 number
        final StudentInfo invalidStudentIdInput = new StudentInfo("123456123", "202020");


        // invokes api validation handler and captures validation result

        final Optional<String> invalidStudnetInfoResult = this.validator.getApiBindingError(invalidStudentIdInput);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_ID, "Invalid 919 format",null
                        )
                )
        );

        assert(invalidStudnetInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetInfoResult.get()));
    }

    /**
     * Tests for an invalid term
     * @throws Exception
     */
    @Test
    public void testInvalidTerm() throws  Exception{
        //10 to start term code
        final StudentInfo invalidStudentUsernameInput = new StudentInfo("919545753", "102020");


        // invokes api validation handler and captures validation result

        final Optional<String> invalidStudnetInfoResult = this.validator.getApiBindingError(invalidStudentUsernameInput);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_TERM_CODE, "Invalid term code format",null
                        )
                )
        );

        assert(invalidStudnetInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetInfoResult.get()));
    }

    /**
     * Test case for when all both inputs are invalid
     * @throws Exception
     */
    @Test
    public void testInvalidBoth() throws  Exception{
        //10 to start term code
        final StudentInfo invalidStudentBothInput = new StudentInfo("819545753", "102020");


        // invokes api validation handler and captures validation result

        final Optional<String> invalidStudnetInfoResult = this.validator.getApiBindingError(invalidStudentBothInput);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_ID, "Invalid 919 format",null
                        ),
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_TERM_CODE, "Invalid term code format",null
                        )
                )
        );

        assert(invalidStudnetInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetInfoResult.get()));
    }

}
