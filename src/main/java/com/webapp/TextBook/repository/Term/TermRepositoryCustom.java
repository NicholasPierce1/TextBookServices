package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Term;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * <h1>TermRepositoryCustom</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for custom procedure for Term entity and extends generic
 * repository that holds the getTableName method to use for all entities.
 */
@org.springframework.stereotype.Repository
public interface TermRepositoryCustom extends Repository {

    /**
     * <p>
     *  Stub for getTermWithTermCode procedure for Term database table.
     *</p>
     *
     * @param termCode: String termCode representing the number that denotes the year and trimester.
     * @return Optional Term object with Status Code
     */
    @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull final String termCode);
}
