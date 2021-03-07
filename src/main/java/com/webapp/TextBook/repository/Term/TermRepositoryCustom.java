package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Term;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface TermRepositoryCustom extends Repository {

    @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull final String termCode);
}
