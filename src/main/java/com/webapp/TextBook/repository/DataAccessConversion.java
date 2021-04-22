package com.webapp.TextBook.repository;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.validation.constraints.NotNull;

/**
 * <h1>DataAccessConversion</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for data access conversion for all entities.
 */
public interface DataAccessConversion {

    /**
     * <p>
     *  Stub for updateDataAccessObject procedure for all specified tables.
     *</p>
     *
     * @param values: Generic object holding the DBO information of specified java class
     */
    public abstract void updateDataAccessObject(@NotNull final Object[] values);

    //todo: add doc
    public @NotNull JSONObject createJsonObjectForm() throws JSONException;

}
