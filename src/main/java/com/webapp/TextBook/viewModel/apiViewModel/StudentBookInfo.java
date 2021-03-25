package com.webapp.TextBook.viewModel.apiViewModel;

import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import com.webapp.TextBook.viewModel.shared.ApiViewModelCreation;
import com.webapp.TextBook.viewModel.shared.ApiViewModel;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/***
 * Viewmodel duties for StudnetBookInfo page
 */
public class StudentBookInfo extends ApiViewModelCreation{
    /***
     * placeholder for the word "id" and "barcode"
     */

    public static final String NOMINAL_ID = "id";
    public  static final String NOMINAL_BARCODE = "barCode";
    /***
     * 919 number
     */
    public  String id;
    /***
     * Strike Barcdoe
     */

    // todo: doc here
    private static Consumer<Pair<StudentBookInfo, JSONObject>> valueStateSetter = (studentBookInfoPair -> {
        // todo: implement (parse data from json and set into student info)

    });

    public String barCode;
    /***
     * Two Constuctors
     * 1. Blank
     * 2. Takes in both non-static inputs
     */
    public StudentBookInfo(){}
    public StudentBookInfo(String id, String barCode){
        this.id = id;
        this.barCode = barCode;
    }
    /***
     * All relevant getters and setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    /***
     *  Implements the needed method. Refer to LoginUserInfo for context.
     */
    /*
    public @NotNull Optional<StudentBookInfoValidationInterface> createApiViewModelFromJson(@NotNull JSONObject jsonObject, Supplier<StudentBookInfoValidationInterface> initialInstantiation) {
        final StudentBookInfoValidationInterface bookInfo = new StudentBookInfoValidationInterface();

        // try-catch for JSON key errors in parsing partial user definition
        try{

            bookInfo.setId(jsonObject.getString(StudentBookInfoValidationInterface.NOMINAL_ID));

            bookInfo.setBarCode(jsonObject.getString(StudentBookInfoValidationInterface.NOMINAL_BARCODE));

            return Optional.of(bookInfo);

        }
        catch(JSONException jsonException){
            // logs (prints now) exception
            System.out.println(jsonException.getMessage());

            return Optional.empty();
        }
    }

     */


    // todo: doc and comment set here
    public static @NotNull Optional<StudentBookInfo> createApiFromJson(
            @NotNull JSONObject jsonObject,
            @NotNull Supplier<StudentBookInfo> studentInfoSupplier){
        try{
            return ApiViewModelCreation.createApiViewModelFromJson(
                    jsonObject,
                    studentInfoSupplier,
                    StudentBookInfo.valueStateSetter
            );
        }
        catch(RuntimeException ex){
            // todo: log w/ internal error
            return Optional.empty();
        }
    }
}