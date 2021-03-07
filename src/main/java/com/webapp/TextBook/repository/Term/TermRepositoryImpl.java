package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.data_access.Term;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class TermRepositoryImpl implements TermRepositoryCustom{

    private final EntityManagerFactory _entityManagerFactory;


    // defines an autowired constructor (render by Spring Application Context as a Bean configuration)
    // to established injected member for singleton, repository interaction
    // note: 'new' instances shall never be created
    @Autowired
    TermRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    @Override
    public @NotNull String GetTableName() {
        return null;
    }

    @Override
    public @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull String termCode) {
        return null;
    }
}
