package com.webapp.TextBook.validation.ApiValidation.StudnetBookInfoValidation;

import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.ErrorBindingException;
import com.webapp.TextBook.validation.Shared.RegexPatternContainer;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

/***
 * This class is responsible for validaiing student book info
 */

public class StudentBookInfoValidationImpl
        implements ConstraintValidator<StudentBookInfoValidationInterface, StudentBookInfo>,
        SharedValidationState
    {
        /***
         *
         * @param constraintAnnotation
         * Implements required method
         */
        @Override
        public void initialize(StudentBookInfoValidationInterface constraintAnnotation) {

        }

        @Override
        /***
         * To see if info provided is valid, namily 919 and barcode are ok
         * @Returns if an error list is empty.
         */
        public boolean isValid(StudentBookInfo studentBookInfo, ConstraintValidatorContext constraintValidatorContext) {
            ArrayList<ErrorBinding<String>> errorList = new ArrayList<ErrorBinding<String>>();
            try {
                if (studentBookInfo.getId().matches(RegexPatternContainer.STUDENT_ID_PATTERN.pattern())) {
                    errorList.add(new ErrorBinding<String>(StudentBookInfo.NOMINAL_ID, "Invlid 919", null));

                }
                if (studentBookInfo.getBarCode().matches(RegexPatternContainer.BARCODE_PATTERN.pattern())) {
                    errorList.add(new ErrorBinding<String>(StudentBookInfo.NOMINAL_BARCODE, "Invlid Barcide", null));

                }
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

            return false;
        }
    }
