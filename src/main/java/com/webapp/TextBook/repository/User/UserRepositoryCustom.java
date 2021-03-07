package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.Repository;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepositoryCustom extends Repository {
    @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull final String username, @NotNull final String password);

}
