package com.webapp.TextBook.repository;

import javax.validation.constraints.NotNull;

/**
 * <h1>Repository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stub method to use for all data access repositories
 */
public interface Repository {

    /**
     * <p>
     *  Stub for GetTableName method to get the necessary database table name needed.
     *</p>
     *
     * @return String, which will be the database table name in query format for the specific table needed.
     */
    @NotNull String GetTableName();
}
