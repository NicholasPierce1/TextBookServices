package com.webapp.TextBook.viewModel.shared;

import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.javatuples.Pair;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h1>ApiViewModel</h1>
 *
 * <p>
 *     Retains requirements and classification for Api ViewModels; important for manual constraint validators
 *     targeting API ViewModels for JSON sub-parseable creation.
 * </p>
 */

public abstract class ApiViewModelCreation implements ApiViewModel{

    /**
     * <p>Creates an instance of
     * itself (implemented concrete class) from the provided JSONObject.
     * Provides an option for a custom supplier, if not then the blank/default constructor will be invoked.</p>
     *
     * @param jsonObject: data source to fully define the initial instantiation for the given api view model.
     *
     * @param initialInstantiation: provides optionality for custom ability to create an initial api
     *                            view model. If null (none is given) then the default, empty constructor
     *                            is used.
     *
     * @return an Optional (type param: A type reference of itself in class header) of the fully consturcted
     * view model. If the data source (JSONObject) retains null fields then an empty optional is given.
     */
    public static <T extends ApiViewModel> @NotNull Optional<T> createApiViewModelFromJson(
            @NotNull final JSONObject jsonObject,
            @NotNull final Supplier<T> initialInstantiation,
            @NotNull final Consumer<Pair<T, JSONObject>> valueStateUpdater){

        try{

            // renders api view model generic (to be used in value state setting in sub-parsable)
            final T apiViewModel = initialInstantiation.get();

            // invokes consumer with given json object (JSON version of ApiViewModel's state)
            // to set/update ApiViewModel
            valueStateUpdater.accept(new Pair<T, JSONObject>(apiViewModel, jsonObject));

            return Optional.of(apiViewModel);

        }
        catch(Exception ex){

            // logs internal error for errors that occurred in consumer-json parsing
            System.out.println("Internal Error (ApiViewModelCreation -- createApiViewModelFromJson):\n" +
                    "Could not create or set the value state comprised in the JSON request body. Check stack trace to see which field/s" +
                    "threw the error." + ex.getMessage());

            return Optional.empty();
        }
    };



}
