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
    /***
     * Implements iniatalized method
     * @param constraintAnnotation
     */
    @Override
    public void initialize(StudentInfoValidationInterface constraintAnnotation) {

    }
    // todo: yikes. doc missing, comments missing, code structure... yikes
    /***
     *
     * @param studentInfo
     * @param constraintValidatorContext
     * Any errors will be added to the errorList
     * @return if the error list is empty
     */
    @Override
    public boolean isValid(StudentInfo studentInfo, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<ErrorBinding<?>> errorList = new ArrayList<ErrorBinding<?>>();

        try{
        //for invlaid 919 numbers
        if(!studentInfo.getId().matches(RegexPatternContainer.STUDENT_ID_PATTERN.pattern())){
            errorList.add(new ErrorBinding<String>(StudentInfo.NOMINAL_ID, "Invalid 919 format", null));
            }
        //for invalid term code
        if(!studentInfo.getTermCode().matches(RegexPatternContainer.TERM_PATTERN.pattern())){
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
            System.out.println("Error binding failed\n" + e.getStackTrace());
            constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_JSON_ERROR_MESSAGE);
        }
        catch(Exception exception){
            // for when conversion of binding list fails upon
            // error event json generation
            System.out.println("Something has gone wrong in LoginUserInfoVladion\n" + exception.getStackTrace());
            constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_ERROR_MESSAGE);
        }

        return errorList.isEmpty();

    }


}
