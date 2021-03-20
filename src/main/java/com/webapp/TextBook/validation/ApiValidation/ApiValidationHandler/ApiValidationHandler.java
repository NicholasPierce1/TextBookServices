package com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler;

import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.shared.ApiViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

/**
 * <h1>ApiValidationHandler</h1>
 *
 * <p>Offers encapsulated validation & validation response for ApiViewModels </p>
 */
@Service
public class ApiValidationHandler {

    /**
     * <p>
     * Generic Validator injected by Spring Context Package Scan
     * (composite of "apiValidationHandler" Bean)
     * </p>
     */
    private final Validator _VALIDATOR;

    /**
     * <p>
     * Autowired constructor to render Bean retaining the generic validator
     * (Bean name: validator).
     * NOTE: this constructor or any other manual "new" instance/s shall NEVER be called.
     * </p>
     * @param validator: bean used to manual, ApiViewModel validation
     */
    @Autowired
    ApiValidationHandler(Validator validator){
        this._VALIDATOR = validator;
    }

    /**
     * <p>Generic method configuration for validating viewmodels (api -- subparse objects require
     * manual instigation).  This is due to a REST response retaining 1:m viewmodels and
     * JSON data, not form data, inability to configure primitive mappings:
     * (ie: int myNum --> { "myNum" : 3 }   will always fail.
     * </p>
     * @param viewModel: ApiViewModel that retains only one, type validator. To be validated.
     * @param <T>: ApiViewModel type constraint. FormViewModels are not sub-parseable, ergo
     *           they do not require manual validation.
     * @return Optional (type parameter: String) retaining a JSONArray or generic error message -
     * varying on the state/success of accessing JSONArray String for ErrorBinding conversions.
     * IF no error uncovered then the optional will be empty.
     */

    public <T extends ApiViewModel> @NotNull Optional<String>
    getApiBindingError(@NotNull final T viewModel){

        Set<ConstraintViolation<T>> violationSet = this._VALIDATOR.validate(viewModel);

        if(!violationSet.isEmpty()){

            if(violationSet.size() != 1)
                throw new RuntimeException("Project Error: size of violation set should only be one." +
                        "Does the view model retain only one validation constraint tag?");

            // small storage optimization : size set to one
            final ArrayList<ConstraintViolation<T>> violation = new ArrayList<ConstraintViolation<T>>(1);

            // iterates over violations (should always be 1 only) and set json array
            // converting set to array loses the type argument integrity
            violation.addAll(violationSet);

            // configures error response
            return Optional.of(violation.get(0).getMessage());

        }

        // no error uncovered in validation -- empty optional
        return Optional.empty();

    }
}

