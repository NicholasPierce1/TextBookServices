package com.webapp.TextBook.validation.FormVallidation.loginUserInfoValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.ErrorBindingException;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.formViewModel.loginUserInfo.LoginUserInfo;

public class LoginUserInfoValidatorImpl implements
        ConstraintValidator<LogInUserInfoValidationInterface, LoginUserInfo>,
        SharedValidationState {

    private static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    private static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    private static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@.*$");
    private static final String INVALID_FIELD = "Invalid Field";
    private boolean haveSuffix;

    @Override
    public void initialize(LogInUserInfoValidationInterface info){
            this.haveSuffix = info.haveSuffix();

    }
    @Override
    public boolean isValid(LoginUserInfo user, ConstraintValidatorContext constraintContext){


        ArrayList<ErrorBinding<String>> errorList = new ArrayList<ErrorBinding<String>>();

        // holds if any inputs are null
        boolean userInputsNull = false;

        // initial check -- any fields are null (faulty form data)
        if(user.get_password() == null){
            userInputsNull = true;
            errorList.add(new ErrorBinding<String>(INVALID_FIELD, "Password field is empty", null));
        }
        else if(user.get_username() == null){
            userInputsNull = true;
            errorList.add(new ErrorBinding<String>(INVALID_FIELD, "Username field is empty", null));
        }


        try {

            if(userInputsNull){

                //if binding error list is not empty then set constraint validator’s message
                // to the abstract method’s json creator helper.
                constraintContext.disableDefaultConstraintViolation();

                constraintContext.buildConstraintViolationWithTemplate(
                        ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(errorList));


                return false;
            }


            // commence bi-if logic -- failure of prerequisite condition does not
            // bar second input (password) from being validated
            if (!user.get_username().matches(STUDENT_ID_PATTERN.pattern())) {
                // add to error binding list
                errorList.add(new ErrorBinding<String>(INVALID_FIELD, "Invalid 919 number", user.get_username()));
            }

            // second input validation (password)
            // tests prefix of password
            // two variations exist: password w/ or w/o domain
            if (!user.get_password().matches(S_NUMBER_PREFIX.pattern())) {
                errorList.add(new ErrorBinding<String>(INVALID_FIELD, "Invalid s number", null));

            }
            else {

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
                    errorList.add(new ErrorBinding<String>(INVALID_FIELD, "Invalid s number", null));

                }
                // wants: no suffix at end
                // got: suffix at end
                else if (!this.haveSuffix && inputHasSuffix)
                    user.set_password(
                            user.get_password().replaceFirst("@.*", "")
                    );

            }
            if(errorList.isEmpty()){ //Error list is not empty
                //if binding error list is not empty then set constraint validator’s message
                // to the abstract method’s json creator helper.
                constraintContext.disableDefaultConstraintViolation();

                constraintContext.buildConstraintViolationWithTemplate(ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(errorList));

            }
        }
        catch (ErrorBindingException e){
            System.out.println("Error binding failed\n" + e.getStackTrace());

        }
        catch(Exception exception){
            // for when conversion of binding list fails upon
            // error event json generation
            System.out.println("Something has gone wrong in LoginUserInfoVladion\n" + exception.getStackTrace());

        }

        //Check binding error list is empty and do some stuff
        return errorList.isEmpty(); //stub method to make everything happy for now
    }


}
