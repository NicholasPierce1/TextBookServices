package com.webapp.TextBook.repository;

import javax.validation.constraints.NotNull;

public interface DataAccessConversion {

    public abstract void updateDataAccessObject(@NotNull final Object[] values);

}
