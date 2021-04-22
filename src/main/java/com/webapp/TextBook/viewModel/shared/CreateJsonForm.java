package com.webapp.TextBook.viewModel.shared;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;

// todo: add doc
public interface CreateJsonForm {

    // todo: add doc
    public @NotNull JSONObject createJsonObjectForm() throws JSONException;

}
