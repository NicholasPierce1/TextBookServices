package com.webapp.TextBook.validation.loginUserInfoValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.regex.Pattern;
import com.webapp.TextBook.viewModel.loginUserInfo.LoginUserInfo;
import org.springframework.web.servlet.tags.BindErrorsTag;

public class LoginUserInfoValidatorImpl implements ConstraintValidator<LogInUserInfoValidationInterface, LoginUserInfo>{
    private static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    private static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    private static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@.*$");
    private boolean haveSuffix;

    @Override
    public void initialize(LogInUserInfoValidationInterface info){
            this.haveSuffix = info.haveSuffix();

    }
    @Override
    public boolean isValid(LoginUserInfo user, ConstraintValidatorContext constraintContext){


        /* NICK: logic and structure is wrong -- missing multiple dependency classes
            // missing: ErrorBinding, ErrorBindingException
         */
        try {
            // commence bi-if logic -- failure of prerequisite condition does not
            // bar second input (password) from being validated
            if (!user.get_username().matches(STUDENT_ID_PATTERN.pattern())) {
                // add to error binding list
            }

            // second input validation (password)
            // tests prefix of password
            // two variations exist: password w/ or w/o domain
            if (!user.get_password().matches(S_NUMBER_PREFIX.pattern())) {
                // add error to binding list
            } else {

                // captures if their is a suffix attach to password input
                // applied to see if
                //     !haveSuffix: suffix (domain) needs to be spliced if present
                //     haveSuffix: binding error generated if suffix not present
                // given: prefix handles if injection or nonsensical additions appended
                final boolean inputHasSuffix = user.get_password().matches(S_NUMBER_SUFFIX.pattern());

                // wants: suffix at end
                // got: no suffix
                if (this.haveSuffix && !inputHasSuffix) {
                    // add error to binding list
                }
                // wants: no suffix at end
                // got: suffix at end
                else if (!this.haveSuffix && inputHasSuffix)
                    user.set_password(
                            user.get_password().replaceFirst("@.*", "")
                    );

            }
        }
        catch(RuntimeException exception){
            // for when conversion of binding list fails upon
            // error event json generation
        }

        //Check binding error list is empty and do some stuff
        return false; //stub method to make everything happy for now
    }


}
