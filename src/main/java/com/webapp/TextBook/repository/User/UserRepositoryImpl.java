package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.Person.PersonRepository;
import com.webapp.TextBook.repository.StatusCode;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    private UserRepository userRepository;

    @Override
    public @NotNull String GetTableName() {
        return null;
    }

    @Override
    public @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull String username, @NotNull String password) {
        return null;
    }
}
