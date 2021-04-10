package com.webapp.TextBook.ValidationTest.SharedValidationTest.LoginUserValidationTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation.LoginUserInfoValidatorImpl;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
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

    // test for password invalid

    // test for both invalid

}
