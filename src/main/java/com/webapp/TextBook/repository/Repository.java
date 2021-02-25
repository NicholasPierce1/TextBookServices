package com.webapp.TextBook.repository;

import javax.validation.constraints.NotNull;

public interface Repository {
    @NotNull String GetTableName();
}
