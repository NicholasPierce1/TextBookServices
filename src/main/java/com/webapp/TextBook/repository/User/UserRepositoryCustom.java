package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.StatusCode;
import org.javatuples.Pair;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepositoryCustom extends Repository {
    @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull final String username, @NotNull final String password);

}
