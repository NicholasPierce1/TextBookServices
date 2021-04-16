package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookCopyRepositoryTest {

    // dynamic: function/s that interact and/or manipulate state from a centralized service/location
    // static (not here): invoke a function that does NOT manipulate - or interact - with any centralized state  (session, db, cache)

    // hard: expected outputs are asserted
    // soft: print (stdout) and independently verifying that results are expected

    // read
    // want to use a pre-existing instance/data (ie: not creating new data)

    // CUD
    // oracle db will create (in sql developer) new data
    /*
    ex: change disposition - create a test book, create a test student, tie the together,
    use those pk/candidate keys to perform operation.
    Why? There is potential for data corruption--> WE DO NOT WANT!!
     */

    @Autowired
    private BookCopyRepository _bookCopyRepository;

    @Test
    public void testGetAllCheckoutBooks(){

        // supply hard coded student id (pidm) & term code
        final String STUDENT_PIDM = "467767";

        final String TERM_CODE = "202120";

        try {
            // invoke repository to garner all book copies for a given student and term
            final Pair<Optional<List<BookCopy>>, StatusCode> RESULTS =
                    this._bookCopyRepository.getAllCheckedOutBooks(STUDENT_PIDM, TERM_CODE);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULTS.getValue1()); // should be OK
            System.out.println(RESULTS.getValue0().isPresent());

            // prints all results to verify that state of bookcopy (ORM) works as expected
            if (RESULTS.getValue0().isPresent()) {

                // print the number of results expected (current instance: 4/13 --> 4)
                System.out.println(RESULTS.getValue0().get().size());

                for (final BookCopy bookCopy : RESULTS.getValue0().get())
                    System.out.println(bookCopy);
            }

            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }

    }

    @Test
    public void testCheckoutBookForStudentAndTerm(){

        // enumerates local, final state to denote a new student within a term + a targeted "dummy" bookcopy
        final String studentId = "12345678";
        final String currentTermCode = "202120";
        final String targetedBookCopyStrikeBarcode = "111111111111/";

        // encapsulates process within try-catch
        // if no errors yielded then test passed
        try{

            // checks out the pre-test created book to the dummy student
            final Pair<Optional<BookCopy>, StatusCode> results = this._bookCopyRepository.checkOutBook(
                    targetedBookCopyStrikeBarcode,
                    studentId,
                    currentTermCode
            );

            // manual print to verify state is palatable
            System.out.println("status code of result (should be ok in local trial (cannot run without pre-test state creation):" +
                    results.getValue1());

            if(results.getValue0().isPresent())
                // 919 and term code should match from local constant denoted above + strike code is the same
                System.out.println("returned book copy: " + results.getValue0().get());

            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }


    }

}
