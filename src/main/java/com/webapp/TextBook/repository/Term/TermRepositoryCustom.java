package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.StatusCode;
import com.webapp.TextBook.repository.data_access.Term;
import org.javatuples.Pair;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface TermRepositoryCustom extends Repository {

    @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull final String termCode);
}
