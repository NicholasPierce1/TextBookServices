package com.webapp.TextBook.repository.Person;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.Student;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@org.springframework.stereotype.Repository

/**
 * <h1>PersonRepositoryCustom</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for custom procedures for the Person entity and extends the generic
 * repository that holds the getTableName method to use for all entities.
 */
public interface PersonRepositoryCustom extends Repository {

    /**
     * <p>
     *  Stub for getStudentBagWithStudentWithCandidateKey procedure for the Student database table.
     *</p>
     *
     * @param studentCandidateKey: String studentCandidateKey representing the 919 number of a student.
     * @return Optional Student object with Status Code
     */
    @NotNull Pair<Optional<Student>,  StatusCode> getStudentWithCandidateKey(@NotNull final String studentCandidateKey);

    /**
     * <p>
     *  Stub for getStudentBagWithStudentWithId procedure for the Student database table.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @return Optional Student object with Status Code
     */
    @NotNull Pair< Optional<Student>,  StatusCode> getStudentWithId(@NotNull final String studentId);

    /**
     * <p>
     *  Stub for getPartialUserWithId procedure for the Person database table.
     *</p>
     *
     * @param userId: String userId representing the unique string for a 919 number.
     * @return Optional generic object with Status Code
     */
    @NotNull Pair< Optional<Object[]>, StatusCode > getPartialUserWithId(@NotNull final String userId);


}
