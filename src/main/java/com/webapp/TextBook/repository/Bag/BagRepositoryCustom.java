package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Bag;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface BagRepositoryCustom extends Repository {
    @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull final String studentId);
}
