package com.webapp.TextBook.viewModel.apiViewModel;

import com.sun.istack.Nullable;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationInterface;
import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.ApiViewModel;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
Handles viewmodel duties for the StudentInfo page
 */
@StudentInfoValidationInterface
public class StudentInfo extends ApiViewModelCreation {
    /***
     * Place holder for the word "ID"
     */

    public static final String NOMINAL_ID = "id";
    /***
     * place holder for the word "termCode"
     */
    public static final String NOMINAL_TERM_CODE = "termCode";


    /**
     * <h1>valueStateSetter</h1>
     * <h3>type: Consumer( type params: Pair (type params: StudentInfo & JSONObject)</h3>
     *
     * <p>
     *     Description: From a StudentInfo object & its corresponding JSONObject (should be a
     *     StudentInfo reflection/composition in JSON form), attempts to set and update state within
     *     StudentInfo from the JSON form.
     * </p>
     *
     * throws: RuntimeException -- if the JSON object does not retain the necessary keys to holistically
     * update a StudentInfo
     */
    private static Consumer<Pair<StudentInfo, JSONObject>> valueStateSetter = (studentInfoPair -> {
        // parses data from json and set into login user info

        // holds local copy of json object & login user info
        final StudentInfo STUDENT_INFO = studentInfoPair.getValue0();
        final JSONObject DATA = studentInfoPair.getValue1();
        // integrity checks that key values required by LoginUserInfo are present
        // throws exception if not
        if(!DATA.has(StudentInfo.NOMINAL_ID) || !DATA.has(StudentInfo.NOMINAL_TERM_CODE))
            throw new RuntimeException("StudentInfo's ValueStateSetter: nominal key values required for" +
                    "StudentInfo updating are not present. Please refer to data contract and data definition in controller" +
                    " for clarification of data state expectations.");
        //Parsing
        try{
            STUDENT_INFO.setId(DATA.getString(StudentInfo.NOMINAL_ID));
            STUDENT_INFO.setTermCode(DATA.getString(StudentInfo.NOMINAL_TERM_CODE));

        }
        //This won't happen but we need it to make the rest of the code happy.
        catch (JSONException e){
            System.out.println("Internal Error (LoginUserInfo -- LoginUserInfo's ValueStateSetter):\n" +
                    "an error occurred that should be caught, thrown and handled internally. Please check stack trace " +
                    "for more info." + e.getLocalizedMessage());
        }
    });
    /**
     * Student 919 number
     */
    public String id;
    /**
     * <p>Refers to a term code input. Term code is classified as string-input that denotes a specific
     * term within a give year (ie: 202010 --> Fall 2020). Refer to design documents for more info.</p>
     */
    public String termCode;
    /**
     * Constructors
     *         1. Blank
     *         2. Takes all inputs
     */
    public StudentInfo(){}
    public StudentInfo(String id, String termCode){
        this.id = id;
        this.termCode = termCode;

    }

    /**
     * All neccessary getters and setters for non-final values
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }
    /**
     *  Implements the needed method. Refer to LoginUserInfo for context.
     */



    /**
     * <p>
     *     From the JSONObject (should be JSON composition/reflection of StudentInfo's state)
     *     and an optional Supplier, render and sets a StudentInfo object to the reflective/corresponding
     *     state within the JSONObject.
     * </p>
     * @param jsonObject JSONObject which is the composition of the state that defines a StudentInfo
     * @param studentInfoSupplier(type param: StudentInfo) optional function to invoke to supplement
     *                              a StudentInfo to use in creation and state setting.
     * @return Optional(type param: StudentInfo) holding the ApiViewModel object. Will be empty if
     * a) JSONObject does not hold the necessary state for a full state setting
     * b) Supplier(type param: StudentInfo) throws an internal error
     */
    public static @NotNull Optional<StudentInfo> createApiFromJson(
            @NotNull final JSONObject jsonObject,
            @Nullable final Supplier<StudentInfo> studentInfoSupplier){

        // creates a local supplier based on the predicate if the given supplier is null or not
        // use the blank constructor as a default
        final Supplier<StudentInfo> localStudentInfoSupplier = studentInfoSupplier == null ?
                StudentInfo::new :
                studentInfoSupplier;

        // invokes parent's static helper to set the state for a studenInfo
        return ApiViewModelCreation.createApiViewModelFromJson(
                jsonObject,
                localStudentInfoSupplier,
                StudentInfo.valueStateSetter
                );

    }


    public static @NotNull Optional<StudentInfo> createApiFromJson(
            @NotNull final JSONObject jsonObject){

        return createApiFromJson(jsonObject,null);
    }

    /**
     * equals mehtod for Testing. Compares paramters
     * @param stuInfo
     * @return
     */
    @Override
    public boolean equals(Object stuInfo){
        StudentInfo expected = (StudentInfo)stuInfo ;
        return(
                this.id.equals(expected.id) && this.termCode.equals(expected.termCode)
                );

    }

    @Override
    public @NotNull JSONObject createJsonObjectForm() throws JSONException {

        // create a new json object, and use the nominal keys to place the instance/field in
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put(StudentInfo.NOMINAL_TERM_CODE, this.getTermCode());
        jsonObject.put(StudentInfo.NOMINAL_ID, this.getId());

        return jsonObject;

    }
}
