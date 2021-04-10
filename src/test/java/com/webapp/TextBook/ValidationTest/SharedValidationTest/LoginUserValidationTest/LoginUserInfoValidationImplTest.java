package com.webapp.TextBook.ValidationTest.SharedValidationTest.LoginUserValidationTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation.LoginUserInfoValidatorImpl;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

/** Tests logic concerning LoginuserInfo
 *
 */
@SpringBootTest
public class LoginUserInfoValidationImplTest {
    @Autowired
    ApiValidationHandler validator;



    /**
     * Tests the isValid method for LoginUserInfo. Each case is described down below
     */
    @Test
    public void testIsValid(){

        LoginUserInfoValidatorImpl loginUserInfo = new LoginUserInfoValidatorImpl();
        //CASE ONE: null
        LoginUserInfo nullUser = new LoginUserInfo(null, null);
        //assert  !validator.getApiBindingError().isValid(nullUser,) ;
        //CASE TWO: bad 919
        LoginUserInfo bad919 = new LoginUserInfo("s52936", "8675309");
        //assert  !loginUserInfo.isValid(bad919, );
        //CASE THREE: bad sNumber
        LoginUserInfo badS = new LoginUserInfo("n52936", "");


    }

}
