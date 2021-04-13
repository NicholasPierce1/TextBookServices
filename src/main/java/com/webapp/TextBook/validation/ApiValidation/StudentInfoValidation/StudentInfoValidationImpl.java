package com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation;

import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationInterface;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.ErrorBindingException;
import com.webapp.TextBook.validation.Shared.RegexPatternContainer;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

/***
 * Validates StudnetInfo input
 */
public class StudentInfoValidationImpl
         implements ConstraintValidator<StudentInfoValidationInterface, StudentInfo>,
        SharedValidationState {
    /**
     * Implements iniatalized method
     * @param constraintAnnotation
     */
    @Override
    public void initialize(StudentInfoValidationInterface constraintAnnotation) {

    }
    /**
     *
     * @param studentInfo
     * @param constraintValidatorContext
     * Any errors will be added to the errorList
     * @return if the error list is empty
     */
    @Override
    public boolean isValid(StudentInfo studentInfo, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<ErrorBinding<?>> errorList = new ArrayList<ErrorBinding<?>>();
        System.out.println("SBI: have arrived at isValid");
        try{
        //for invalid 919 numbers. Checks if it matches regext pattern
        if(!studentInfo.getId().matches(RegexPatternContainer.STUDENT_ID_PATTERN.pattern())){
            System.out.println("SBI: Invalid ID found");
            errorList.add(new ErrorBinding<String>(StudentInfo.NOMINAL_ID, "Invalid 919 format", null));
            }
        //for invalid term code. Checks if it matches Regex pattern
        if(!studentInfo.getTermCode().matches(RegexPatternContainer.TERM_PATTERN.pattern())){
            System.out.println("SBI: Invalid term code found");
            errorList.add(new ErrorBinding<String>(StudentInfo.NOMINAL_TERM_CODE, "Invalid term code format", null));

        }
        //Done with validation, now we can see if we need to add things to a JSON string
        if(!errorList.isEmpty()){
            constraintValidatorContext.disableDefaultConstraintViolation();

            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(errorList));

        }
        }
        catch (ErrorBindingException e){
            System.out.println("Internal Error occurred in StudentInfoValidationImpl- isValid: \n" + e.getStackTrace());
            constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_JSON_ERROR_MESSAGE);
            return false;

        }
        catch(Exception exception){
            // for when conversion of binding list fails upon
            // error event json generation
            System.out.println("Internal Error occurred in StudentInfoValidationImpl- isValid: \n" + exception.getStackTrace());
            constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_ERROR_MESSAGE);
            return false;
        }
        //If list is empty, no errors.
        //return errorList.isEmpty();
       return  false;

    }


}
