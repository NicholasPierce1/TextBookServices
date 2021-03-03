package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BagRepositoryImpl implements BagRepositoryCustom{

    @Autowired
    private BagRepository bagRepository;

    @Override
    public @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull String studentId) {
        return null;
    }

    @Override
    public @NotNull String GetTableName() {
        return null;
    }
}
