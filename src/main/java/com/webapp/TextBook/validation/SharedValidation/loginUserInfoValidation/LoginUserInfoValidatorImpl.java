package com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

import com.webapp.TextBook.validation.Shared.RegexPatternContainer;

import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.ErrorBindingException;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;

public class LoginUserInfoValidatorImpl implements
        ConstraintValidator<LogInUserInfoValidationInterface, LoginUserInfo>,
        SharedValidationState {


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
            errorList.add(new ErrorBinding<String>(LoginUserInfo.NOMINAL_PASSWORD, "Password field is empty", null));
        }
        else if(user.get_username() == null){
            userInputsNull = true;
            errorList.add(new ErrorBinding<String>(LoginUserInfo.NOMINAL_USERNAME, "Username field is empty", null));
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
            if (!user.get_username().matches(RegexPatternContainer.STUDENT_ID_PATTERN.pattern())) {
                // add to error binding list
                errorList.add(new ErrorBinding<String>(LoginUserInfo.NOMINAL_USERNAME, "Invalid 919 number. Please follow format (919######).", user.get_username()));
            }

            // second input validation (password)
            // tests prefix of password
            // two variations exist: password w/ or w/o domain
            if (!user.get_password().matches(RegexPatternContainer.S_NUMBER_PREFIX.pattern())) {
                errorList.add(new ErrorBinding<String>(LoginUserInfo.NOMINAL_PASSWORD, "Password does not follow S-Number form (S######). Please revise", null));

            }
            else {

                // captures if their is a suffix attach to password input
                // applied to see if
                //     !haveSuffix: suffix (domain) needs to be spliced if present
                //     haveSuffix: binding error generated if suffix not present
                // given: prefix handles if injection or nonsensical additions appended
                final boolean inputHasSuffix = user.get_password().matches(RegexPatternContainer.S_NUMBER_SUFFIX.pattern());

                // wants: suffix at end
                // got: no suffix
                if (this.haveSuffix && !inputHasSuffix) {
                    // add error to binding list
                    errorList.add(new ErrorBinding<String>(LoginUserInfo.NOMINAL_PASSWORD, "Your S-Number should does not retain a domain. Please revise.", null));

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
            constraintContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_JSON_ERROR_MESSAGE);
        }
        catch(Exception exception){
            // for when conversion of binding list fails upon
            // error event json generation
            System.out.println("Something has gone wrong in LoginUserInfoVladion\n" + exception.getStackTrace());
            constraintContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_ERROR_MESSAGE);
        }

        //Check binding error list is empty and do some stuff
        return errorList.isEmpty(); //stub method to make everything happy for now
    }


}
