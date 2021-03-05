package com.webapp.TextBook.repository.Person;

import com.webapp.TextBook.repository.data_access.Student;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    private PersonRepository personRepository;

    public @NotNull Pair<Optional<Student>, StatusCode> getStudentWithCandidateKey(@NotNull final String studentCandidateKey){
        return null;
    }

    public @NotNull Pair< Optional<Student>,  StatusCode> getStudentWithId(@NotNull final String studentId){
        return null;
    }

    public @NotNull Pair< Optional<Object[]>, StatusCode > getPartialUserWithId(@NotNull final String userId){
        return null;
    }

    private @NotNull Pair< Optional<Object[]>, StatusCode> getPersonWithId(@NotNull final String personId){
        return null;
    }


    @Override
    public @NotNull String GetTableName() {
        return null;
    }
}

