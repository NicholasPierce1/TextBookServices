package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.Person.PersonRepository;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.Student;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonRepositoryTest {


    @Autowired
    private PersonRepository _personRepository;

    @Test
    public void testGetStudentWithCandidateKey(){

        final String STUDENT_CANDIDATE_KEY = "919000001";

        try {
            // invoke repository to garner all of a person for a given studentCandidateKey
            final Pair<Optional<Student>, StatusCode> RESULT =
                    this._personRepository.getStudentWithCandidateKey(STUDENT_CANDIDATE_KEY);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULT.getValue1()); // should be OK
            System.out.println(RESULT.getValue0().isPresent());

            // prints all results to verify that state of Person (ORM) works as expected
            if (RESULT.getValue0().isPresent()) {

                final Student student = RESULT.getValue0().get();
                System.out.println(student);
            }

            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }


    }




    @Test
    public void testGetPartialUserWithCanidateKey(){

        // supply hard coded student id (pidm) & term code
        final String USER_ID = "919000001";



        try {
            // invoke repository to garner all book copies for a given student and term
            final Pair< Optional<Object[]>, StatusCode >  RESULTS =
                    this._personRepository.getPartialUserWithCandidateKey(USER_ID);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULTS.getValue1()); // should be OK
            System.out.println(RESULTS.getValue0().isPresent());

            // prints all results to verify that state of bookcopy (ORM) works as expected
            if (RESULTS.getValue0().isPresent()) {

                // print the number of results expected (current instance: 4/13 --> 4)
                System.out.println(RESULTS.getValue0().get().length);

                for (final Object o: RESULTS.getValue0().get())
                    System.out.println(o);
            }

            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }



    }

}
