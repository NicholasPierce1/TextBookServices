package com.webapp.TextBook.viewModel.shared;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <h1>ApiViewModel</h1>
 *
 * @param <T>: A type reference of an ApiViewModel
 *
 * <p>
 *     Retains requirements and classification for Api ViewModels; important for manual constraint validators
 *     targeting API ViewModels for JSON sub-parseable creation.
 * </p>
 */
public interface ApiViewModelCreation<T extends ApiVieweModel> {

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
    public @NotNull Optional<T> createApiViewModelFromJson(
            @NotNull JSONObject jsonObject,
            @Nullable Supplier<T> initialInstantiation);

}