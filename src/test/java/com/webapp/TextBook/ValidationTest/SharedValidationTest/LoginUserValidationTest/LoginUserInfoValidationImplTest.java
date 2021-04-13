package com.webapp.TextBook.ValidationTest.SharedValidationTest.LoginUserValidationTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation.LoginUserInfoValidatorImpl;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONTokener;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

/** Tests logic concerning LoginuserInfo
 *
 */
@SpringBootTest
public class LoginUserInfoValidationImplTest {

    // injection for api validation for manual validation triggers
    @Autowired
    private ApiValidationHandler validator;



    /**
     * more doc needed here: test for both inputs valid
     */
    @Test
    public void testBothInputsValid(){

        // creates targeted inputs (input fields valid) --> one has suffix and a capital 's' while other is converse
        final LoginUserInfo validLoginUserInfoWithSuffix = new LoginUserInfo("919123456", "S123456@nwmissouri.edu");

        final LoginUserInfo validLoginUserInfoWithoutSuffix = new LoginUserInfo("919987654", "s987654");

        // invokes api validation handler and captures validation result

        final Optional<String> validLoginUserInfoWithSuffixResult = this.validator.getApiBindingError(validLoginUserInfoWithSuffix);

        final Optional<String> validLoginUserInfoWithoutSuffixResult = this.validator.getApiBindingError(validLoginUserInfoWithoutSuffix);

        // denotes transformation input result upon passwords (email suffix should be spliced)
        final String passwordTransformationWithSuffix = "S123456";

        final String passwordTransformationWithoutSuffix = "s987654";

        // asserts no errors uncovered (optional is empty) and password transformation is of expected

        // with suffix
        assert(validLoginUserInfoWithSuffixResult.isEmpty());
        assert(passwordTransformationWithSuffix.equals(validLoginUserInfoWithSuffix.get_password()));

        // without suffix
        assert(validLoginUserInfoWithoutSuffixResult.isEmpty());
        assert(validLoginUserInfoWithoutSuffix.get_password().equals(passwordTransformationWithoutSuffix));

    }

    // test for username invalid
    /**
     * <p>
     *     todo: add here
     * </p>
     */
    @Test
    public void testInvalidUsername() throws Exception {

        // creates target input (invalid username)
        // 919 has one extra input char
        final LoginUserInfo invalidLoginUserInfo = new LoginUserInfo("9191234565", "s123456");

        // invokes api validator to acquire binding result
        final Optional<String> invalidLoginUserInfoResult = this.validator.getApiBindingError(invalidLoginUserInfo);

        // creates expected json array to string
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_USERNAME,
                                "Invalid 919 number. Please follow format (919######).",
                                invalidLoginUserInfo.get_username())
                )
        );

        // assert that an error was given
        assert(invalidLoginUserInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidLoginUserInfoResult.get()));

        // assert json array error string is expected
        assert(errorBindingJsonString.equals(invalidLoginUserInfoResult.get()));

    }

    /**
     * Test for invalid password. Tests both the password and the error message
     */
    @Test
    public void testInvalidPassword() throws Exception{
        //n is disallowed
        final LoginUserInfo badPassword = new LoginUserInfo("919123456", "n123456");
        final LoginUserInfo invalidLoginUserInfo = new LoginUserInfo("9191234565", "s123456");

        final Optional<String> invalidLoginUserInfoResult = this.validator.getApiBindingError(badPassword);
        // creates expected json array to string
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_PASSWORD,
                                "Password does not follow S-Number form (S######). Please revise",
                                null)


                )
        );

        // assert that an error was given
        assert(invalidLoginUserInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidLoginUserInfoResult.get()));

        // assert json array error string is expected
       assert(errorBindingJsonString.equals(invalidLoginUserInfoResult.get()));
    }

    /**
     * Tests for both being invalid. Tests both the error and the error message
     */
    @Test
    public void testBothInvalid() throws  Exception{
        // 919 has one extra input char
        final LoginUserInfo invalidLoginUserInfo = new LoginUserInfo("9191234565", "n123456");

        // invokes api validator to acquire binding result
        final Optional<String> invalidLoginUserInfoResult = this.validator.getApiBindingError(invalidLoginUserInfo);

        // creates expected json array to string
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_USERNAME,
                                "Invalid 919 number. Please follow format (919######).",
                                invalidLoginUserInfo.get_username()),
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_PASSWORD,
                                "Password does not follow S-Number form (S######). Please revise",
                                null)


                )
        );

        // assert that an error was given
        assert(invalidLoginUserInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidLoginUserInfoResult.get()));

        // assert json array error string is expected
        assert(errorBindingJsonString.equals(invalidLoginUserInfoResult.get()));

    }

    /**
     * Testing for null values
     */
    @Test
    public void testNull() throws Exception{
        final LoginUserInfo invalidLoginUserInfo = new LoginUserInfo(null, null);

        // invokes api validator to acquire binding result
        final Optional<String> invalidLoginUserInfoResult = this.validator.getApiBindingError(invalidLoginUserInfo);

        // creates expected json array to string
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList(
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_PASSWORD,
                                "Password field is empty",
                                null),
                        new ErrorBinding<String>(
                                LoginUserInfo.NOMINAL_USERNAME,
                                "Username field is empty",
                                null)


                )
        );

        // assert that an error was given
        assert(invalidLoginUserInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidLoginUserInfoResult.get()));

        // assert json array error string is expected
        System.out.println("EXPECTED: " + errorBindingJsonString);
        System.out.println("ACTUAL:" + invalidLoginUserInfoResult.get());
        assert(errorBindingJsonString.equals(invalidLoginUserInfoResult.get()));

    }

}
