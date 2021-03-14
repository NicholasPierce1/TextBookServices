package com.webapp.TextBook.validation.ApiValidation;

import com.webapp.TextBook.validation.Shared.ErrorBinding;
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
        //todo @Nick what is this supposed to do. The example I'm basing this off of had a suffix
        //which we are not using.
    }

    /***
     *
     * @param studentInfo
     * @param constraintValidatorContext
     * Any errors will be added to the errorList
     * @return if the error list is empty
     */
    @Override
    public boolean isValid(StudentInfo studentInfo, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<ErrorBinding<String>> errorList = new ArrayList<ErrorBinding<String>>();

        return false;

    }


}
