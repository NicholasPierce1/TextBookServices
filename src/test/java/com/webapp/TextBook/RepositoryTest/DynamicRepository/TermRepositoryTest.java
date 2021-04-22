package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.Term.TermRepository;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.Term;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TermRepositoryTest {

    @Autowired
    private TermRepository _TermRepository;

    @Test
    public void testGetTermWithTermCode(){

        // supply hard coded term code
        final String TERM_CODE = "198930";

        try{
            // invoke repository to garner all of term for a given term code
            final Pair<Optional<Term>, StatusCode> RESULT =
                    this._TermRepository.getTermWithTermCode(TERM_CODE);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULT.getValue1()); // should be OK
            System.out.println(RESULT.getValue0().isPresent());

            // print result to verify that state of Term (ORM) works as expected
            if (RESULT.getValue0().isPresent()) {

                final Term term = RESULT.getValue0().get();
                    System.out.println(term);
            }

            assert(true);
        }
        catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }

    }
}
