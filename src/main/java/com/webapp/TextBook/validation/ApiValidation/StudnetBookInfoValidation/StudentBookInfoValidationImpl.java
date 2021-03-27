package com.webapp.TextBook.validation.ApiValidation.StudnetBookInfoValidation;

import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.ErrorBindingException;
import com.webapp.TextBook.validation.Shared.RegexPatternContainer;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;

/***
 * This class is responsible for validaiing student book info
 */
@Component
public class StudentBookInfoValidationImpl
        implements ConstraintValidator<StudentBookInfoValidationInterface, StudentBookInfo>,
        SharedValidationState
    {

        /**
         * <p>Local reference to Spring's Bean Container for local API Validator to facilitate generic
         * API ViewModel validation. Usage: applied solely for encapsulated StudentInfo validation.</p>
         */
        private final ApiValidationHandler _validator;

        /**
         * <p>Autowired constructor to inject local API Validator's state.
         * NOTE: never invoke an autowired constructor.</p>
         * @param validator Spring Bean Container for Validator -- singleton
         */
        @Autowired
        public StudentBookInfoValidationImpl(ApiValidationHandler validator){
            this._validator = validator;
        }

        /***
         *
         * @param constraintAnnotation
         * Implements required method
         */
        @Override
        public void initialize(StudentBookInfoValidationInterface constraintAnnotation) {

        }

        @Override

        // todo: this is not good documentation, commenting, and coding practices
        /****
         * Enforces business logic/rules for student book info.
         * @Params Studnetbookinfo, contstraintValidatorContext
         * Code will check various conditions, if conditons are not meant the infraction will be added
         * to a list.
         * @Returns if list is empty or not. Empty list = no problems
         *
         */
        public boolean isValid(StudentBookInfo studentBookInfo, ConstraintValidatorContext constraintValidatorContext) {

            // holds a list of error bindings pertaining to the composite fields that failed their
            // respective business context validations
            final ArrayList<ErrorBinding<?>> ERROR_LIST = new ArrayList<ErrorBinding<?>>();

            // local json array
            // only set IF StudentInfo (composite API ViewModel) fails validation
            // to be used in conjunction with composite error bindings of other member/s in StudentBookInfo
            JSONArray student_info_error_bindings = null;

            try {

                // stores error binding result for student info
                final Triplet<Boolean, Optional<String>, Optional<JSONArray>> STUDENT_INFO_VALIDATION_RESULT =
                        this.handleStudentInfoValidation(studentBookInfo.getStudentInfo());

                // if errors are present then either then handles error state to be consistent with
                // paradigm established for view model validation -- error bindings
                if(!STUDENT_INFO_VALIDATION_RESULT.getValue0()){ // invalid

                    // if error message is generic (first optional is present) then
                    // set constraint validator context to it's message and return error state
                    if(STUDENT_INFO_VALIDATION_RESULT.getValue1().isPresent()){

                        constraintValidatorContext.disableDefaultConstraintViolation();

                        constraintValidatorContext.buildConstraintViolationWithTemplate(
                                STUDENT_INFO_VALIDATION_RESULT.getValue1().get()
                            );

                        return false;

                    }

                    // error message is json array of error bindings
                    // sets JSONArray for error binding conglomeration
                    // NOTE: will always be set
                    student_info_error_bindings = STUDENT_INFO_VALIDATION_RESULT.getValue2().orElseThrow();
                }

                //If if dosen't match the barcode pattern, add to list.
                if (studentBookInfo.getBarCode().matches(RegexPatternContainer.BARCODE_PATTERN.pattern())) {
                    ERROR_LIST.add(new ErrorBinding<String>(StudentBookInfo.NOMINAL_BARCODE, "Invlid Barcide", null));

                }

                // if error list (composite -- non-StudentInfo) not empty OR
                // student info retained errors then create error string
                if(!ERROR_LIST.isEmpty() || student_info_error_bindings != null){

                    // disables default configurations to permit custom error messaging
                    constraintValidatorContext.disableDefaultConstraintViolation();

                    /*
                    sets final string message of error description according to whether
                    error list or student error bindings are set
                     */
                    final String ERROR_MESSAGE;

                    if(ERROR_LIST.isEmpty()) // StudentInfo is invalid only (not null)
                        ERROR_MESSAGE = student_info_error_bindings.toString();

                    else if(student_info_error_bindings == null) // error list is not empty
                        ERROR_MESSAGE = ErrorBinding.ErrorBindingJsonHelper.
                                CreateJsonStringFromErrorBindings(ERROR_LIST);

                    else { // both are present

                        // iterates over all ErrorBindings (in JSON form) and appends to student book JSONArray
                        final JSONArray STUDENT_BOOK_INFO_COMPOSITE_ERRORS =
                                new JSONArray(
                                        ErrorBinding.ErrorBindingJsonHelper.
                                                CreateJsonStringFromErrorBindings(ERROR_LIST)
                                );

                        for(int i = 0; i < student_info_error_bindings.length(); i++)
                            STUDENT_BOOK_INFO_COMPOSITE_ERRORS.put(student_info_error_bindings.getJSONObject(i));

                        // sets error message to JSONArray to string of conglomerate, exhaustive student book info
                        ERROR_MESSAGE = STUDENT_BOOK_INFO_COMPOSITE_ERRORS.toString();
                    }

                    // sets error message to custom message of constraint validator
                    constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR_MESSAGE);

                    // error state return
                    return false;
                }


                // success state return
                return true;
            }
            catch (ErrorBindingException e){
                System.out.println("Error binding failed\n" + e.getMessage());
                constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_JSON_ERROR_MESSAGE);

                return false;
            }
            catch(Exception exception){
                // for when conversion of binding list fails upon
                // error event json generation
                System.out.println("Something has gone wrong in LoginUserInfoVladion\n" + exception.getMessage());
                constraintValidatorContext.buildConstraintViolationWithTemplate(SharedValidationState.GENERIC_ERROR_MESSAGE);

                return false;
            }
        }


        /**(
        @Returns:  Triplet<Boolean, Optional<String>, Optional<JSONArray>> >
        creates private helper to check and convert potential error string into a Triplet
        comprised of:
         a boolean denoting if an error is present,
         an optional holding a string IF the error is generic,
         or an optional holding a JSONArray if the error is an JSONArray
         */
        private @NotNull Triplet<Boolean, Optional<String>, Optional<JSONArray>>
        handleStudentInfoValidation(@NotNull final StudentInfo studentInfo)
        throws JSONException{

            // invokes static helper in ApiValidationHandler to validate studentInfo
            final Optional<String> studentInfoValidationResult = this._validator.getApiBindingError(studentInfo);

            // checks if errors are present (empty optional)
            if(studentInfoValidationResult.isEmpty())
                return new Triplet<Boolean, Optional<String>, Optional<JSONArray>>(
                        true,
                        Optional.empty(),
                        Optional.empty()
                );

            // checks if error string is generic
            // if so then pass string into first optional
            // else second optional will retain the JSONArray -- try catch use but will never be required
            try{

                return SharedValidationState.isGenericErrorMessage(studentInfoValidationResult.get()) ?
                        new Triplet<Boolean, Optional<String>, Optional<JSONArray>>(
                                false,
                                studentInfoValidationResult,
                                Optional.empty()
                        ) :
                        new Triplet<Boolean, Optional<String>, Optional<JSONArray>>(
                                false,
                                Optional.empty(),
                                Optional.of(new JSONArray(studentInfoValidationResult.get()))
                        );

            }
            catch(JSONException ex){

                // will never occur
                System.out.println("Internal Error: (StudentBookInfoValidationImpl -- handleStudentInfoValidation)\n" +
                        "A JsonException occurred in converting the non-generic error String (which by system rules should be a JSONArray" +
                        " string into a JSONArray. Please check logs and outputs for more info.");

                throw ex;
            }
        }

    }
