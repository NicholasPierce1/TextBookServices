package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.Repository;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * <h1>UserRepositoryCustom</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for custom procedure for User entity and extends generic
 * repository that holds the getTableName method to use for all entities.
 */
@org.springframework.stereotype.Repository
public interface UserRepositoryCustom extends Repository {

    /**
     * <p>
     *  Stub for getPartialUserWithUsernameAndPassword procedure for User database table.
     *</p>
     *
     * @param username: String username representing their pidm.
     * @param password: String password representing their sNumber.
     * @return Optional User object with Status Code
     */
    @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull final String username, @NotNull final String password);

}
