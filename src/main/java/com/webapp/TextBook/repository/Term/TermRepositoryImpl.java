package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.data_access.Term;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class TermRepositoryImpl implements TermRepositoryCustom{

    @Autowired
    private TermRepository termRepository;

    @Override
    public @NotNull String GetTableName() {
        return null;
    }

    @Override
    public @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull String termCode) {
        return null;
    }
}
