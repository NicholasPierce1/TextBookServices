package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Bag;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository

/**
 * <h1>BagRepositoryCustom</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for custom procedure for Bag entity and extends generic
 * repository that holds the getTableName method to use for all entities.
 */
public interface BagRepositoryCustom extends Repository {

    /**
     * <p>
     *  Stub for getStudentBagWithStudentId procedure for Bag database table.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @return Optional Bag object with Status Code
     */
    @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull final String studentId);
}
