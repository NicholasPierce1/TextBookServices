package com.webapp.TextBook.viewModel.apiViewModel;

import com.sun.istack.Nullable;
import com.webapp.TextBook.validation.ApiValidation.StudnetBookInfoValidation.StudentBookInfoValidationInterface;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.ApiViewModel;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Viewmodel duties for StudnetBookInfo page
 */
@StudentBookInfoValidationInterface
public final class StudentBookInfo extends ApiViewModelCreation{


    /**
     * <p>Nominal key value of StudentBookInfo's barcode for parsing JSON objects
     * of API requests, Form maps, and ErrorBindings</p>
     */
    public static final String NOMINAL_BARCODE = "barcode";

    /**
     * <p>Nominal key value of StudentBookInfo's student info for parsing JSON objects
     * of API requests, Form maps, and ErrorBindings</p>
     */
    public static final String NOMINAL_STUDENT_INFO = "studentInfo";

    /**
     * <p>StudentBookInfo is comprised of a student info, ergo, this field holds state related to a
     * StudentInfo. This class is not subclassed for that StudentBookInfo isn't created from but is defined
     * as a conglomerate of StudentInfo + other input/view models.</p>
     */
    public StudentInfo studentInfo;

    /**
     * Strike Barcode
     */
    public String barCode;

    /**
     * <h1>valueStateSetter</h1>
     * <h3>type: Consumer( type params: Pair (type params: StudentBookInfo & JSONObject)</h3>
     *
     * <p>
     *     Description: From a StudentInfo object & its corresponding JSONObject (should be a
     *    StudentBookInfo reflection/composition in JSON form), attempts to set and update state within
     *     StudentBookInfo from the JSON form.
     * </p>
     *
     * throws: RuntimeException -- if the JSON object does not retain the necessary keys to holistically
     * update a StudentInfo
     */
    private static Consumer<Pair<StudentBookInfo, JSONObject>> valueStateSetter = (studentBookInfoPair -> {
        // parses data from json and set into student book info

        // holds local copy of json object & student book info
        final StudentBookInfo STUDENT_BOOK_INFO = studentBookInfoPair.getValue0();
        final JSONObject DATA = studentBookInfoPair.getValue1();

        // integrity checks that key values required by StudentBookInfo are present
        // throws exception if not
        if(!DATA.has(StudentBookInfo.NOMINAL_STUDENT_INFO)|| !DATA.has(StudentBookInfo.NOMINAL_BARCODE))
            throw new RuntimeException("StudentBookInfo's ValueStateSetter: nominal key values required for" +
                    "StudentBookInfo updating are not present. Please refer to data contract and data definition in controller" +
                    " for clarification of data state expectations.");

        // sets parse state (try - catch BUT will never occur -- runtime check above handles)
        try {

            // sets StudentInfo by utilizing the subparse helper created in StudentInfo -- default supplier palatable
            // parses student info's composite subparse JSON object for API ViewModel creation
            STUDENT_BOOK_INFO.setStudentInfo(StudentInfo.createApiFromJson(
                    DATA.getJSONObject(
                            StudentBookInfo.NOMINAL_STUDENT_INFO
                    )
            ).orElseThrow());

            // sets barcode
            STUDENT_BOOK_INFO.setBarCode(DATA.getString(StudentBookInfo.NOMINAL_BARCODE));
        }
        catch(JSONException ex){
            // will never occur
            System.out.println("Internal Error (StudentBookInfo -- StudentBookInfo's ValueStateSetter):\n" +
                    "an error occurred that should be caught, thrown and handled internally. Please check stack trace " +
                    "for more info." + ex.getLocalizedMessage());
        }

    });

    /**
     * Two Constuctors
     * 1. Blank
     * 2. Takes in both non-static inputs
     */
    public StudentBookInfo(){}

    public StudentBookInfo(String barCode, StudentInfo studentInfo){
        this.barCode = barCode;
        this.studentInfo = studentInfo;
    }

    /**
     * All relevant getters and setters
     */
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    /**
     * <p>
     *     From the JSONObject (should be JSON composition/reflection of StudentInfo's state)
     *     and an optional Supplier, render and sets a StudentBookInfo object to the reflective/corresponding
     *     state within the JSONObject.
     * </p>
     * @param jsonObject JSONObject which is the composition of the state that defines a StudentBookInfo
     * @param studentBookInfoSupplier Supplier(type param: StudentBookInfo) optional function to invoke to supplement
     *                              a StudentInfo to use in creation and state setting.
     * @return Optional(type param: StudentBookInfo) holding the ApiViewModel object. Will be empty if
     * a) JSONObject does not hold the necessary state for a full state setting
     * b) Supplier(type param: StudentBookInfo) throws an internal error
     */
    public static @NotNull Optional<StudentBookInfo> createApiFromJson(
            @NotNull final JSONObject jsonObject,
            @Nullable final Supplier<StudentBookInfo> studentBookInfoSupplier){

        // creates a local supplier based on the predicate if the given supplier is null or not
        // use the blank constructor as a default
        final Supplier<StudentBookInfo> localStudentBookInfoSupplier = studentBookInfoSupplier == null ?
                StudentBookInfo::new :
                studentBookInfoSupplier;

        // invokes parent's static helper to set the state for a StudentBookInfo
        return ApiViewModelCreation.createApiViewModelFromJson(
                jsonObject,
                localStudentBookInfoSupplier,
                StudentBookInfo.valueStateSetter
        );

    }

    /**
     *
     * @param jsonObject
     * @return API from a given JSON object
     */
    public static @NotNull Optional<StudentBookInfo> createApiFromJson(
            @NotNull final JSONObject jsonObject){
        return createApiFromJson(jsonObject, null);
    }

    /**
     * Overrides teh
     * @param expected
     * @return
     */
    @Override
    public boolean equals(Object expected){
        StudentBookInfo sbi = (StudentBookInfo) expected;
        //Pulls out studtent info for comparison
        StudentInfo stu = sbi.studentInfo;
        return(
                this.studentInfo.equals(stu) && this.barCode.equals(sbi.barCode)
                );

    }

    @Override
    public @NotNull JSONObject createJsonObjectForm() throws JSONException {

        // create a new json object, and use the nominal keys to place the instance/field in
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put(StudentBookInfo.NOMINAL_STUDENT_INFO, this.getStudentInfo());
        jsonObject.put(StudentBookInfo.NOMINAL_BARCODE, this.getBarCode());

        return jsonObject;

    }
}
