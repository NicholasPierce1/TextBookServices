package com.webapp.TextBook.repository.Person;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Student;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PersonRepositoryCustom extends Repository {
    @NotNull Pair<Optional<Student>,  StatusCode> getStudentWithCandidateKey(@NotNull final String studentCandidateKey);
    @NotNull Pair< Optional<Student>,  StatusCode> getStudentWithId(@NotNull final String studentId);
    @NotNull Pair< Optional<Object[]>, StatusCode > getPartialUserWithId(@NotNull final String userId);


}
