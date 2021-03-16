package com.webapp.TextBook.viewModel.apiViewModel;

import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.ApiViewModel;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/***
Handles viewmodel duties for the StudentInfo page
 */
public class StudentInfo implements ApiViewModel, ApiViewModelCreation {
    /***
     * Place holder for the word "ID"
     */

    public static final String NOMINAL_ID = "id";
    /***
     * place holder for the word "termCode"
     */
    public static final String NOMINAL_TERM_CODE = "termCode";
    /***
     * Studnet 919 number
     */

    // todo: doc here
    private static Function<Pair<StudentInfo, JSONObject>, Void> valueStateSetter = ( studentInfoPair -> {
        // todo: implement (parse data from json and set into student info)
        return null;
    });

    public String id;
    /***
     * the term code
     */
    public String termCode;
    /***
     * Constructors
     *         1. Blank
     *         2. Takes all inputs
     */
    public StudentInfo(){}
    public StudentInfo(String id, String termCode){
        this.id = id;
        this.termCode = termCode;

    }

    /***
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
    /***
     *  Implements the needed method. Refer to LoginUserInfo for context.
     */
    /*
    public @NotNull Optional<StudentInfo> createApiViewModelFromJson(@NotNull JSONObject jsonObject, Supplier<StudentInfo> initialInstantiation) {
        final StudentInfo student = new StudentInfo();

        // try-catch for JSON key errors in parsing partial user definition
        try{

            student.setId(jsonObject.getString(StudentInfo.NOMINAL_ID));

            student.setTermCode(jsonObject.getString(StudentInfo.NOMINAL_TERM_CODE));

            return Optional.of(student);

        }
        catch(JSONException jsonException){
            // logs (prints now) exception
            System.out.println(jsonException.getMessage());

            return Optional.empty();
        }
    }

     */

    // todo: doc and comment set here
    public static @NotNull Optional<StudentInfo> createApiFromJson(
            @NotNull JSONObject jsonObject,
            @NotNull Supplier<StudentInfo> studentInfoSupplier){
        try{
            return ApiViewModelCreation.createApiViewModelFromJson(
                    jsonObject,
                    studentInfoSupplier,
                    StudentInfo.valueStateSetter
                    );
        }
        catch(RuntimeException ex){
            // todo: log w/ internal error
            return Optional.empty();
        }
    }

}
