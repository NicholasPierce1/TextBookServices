package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.Bag.BagRepository;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.Term;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class BagRepositoryTest {

    @Autowired
    private BagRepository _bagRepository;

    @Test
    public void testGetStudentBagWithStudentId(){

        // supply hard coded student id (pidm)
        final String STUDENT_PIDM = "516867";

        try{
            // invoke repository to garner all of bag for a given student id
            final Pair<Optional<Bag>, StatusCode> RESULT =
                    this._bagRepository.getStudentBagWithStudentId(STUDENT_PIDM);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULT.getValue1()); // should be OK
            System.out.println(RESULT.getValue0().isPresent());

            // print result to verify that state of Bag (ORM) works as expected
            if (RESULT.getValue0().isPresent()) {

                final Bag bag = RESULT.getValue0().get();
                System.out.println(bag);
            }

            assert(true);
        }
        catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }


    }
}
