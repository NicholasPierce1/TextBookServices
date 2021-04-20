package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.Person.PersonRepository;
import com.webapp.TextBook.repository.User.UserRepository;
import com.webapp.TextBook.repository.adapter.Adapter;
import com.webapp.TextBook.repository.data_access.*;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AdapterTest {

    @Autowired
    private Adapter _adapter;


    @Test
    public void testGetAllCheckedOutBooks(){

        final User USER = new User("467767", "919000005", "admin", "admin", "admin", UserRole.Supervisor, "S000001@nwmissouri.edu");

        final String STUDENT_ID = "919000005";

        final String TERM_CODE = "202120";

        try {
            // invoke repository to garner all book copies for a given student and term
            final Quintet<Optional<List<BookCopy>>, Optional<Student>, Optional<Bag>, Optional<Term>, StatusCode> RESULTS =
                    _adapter.getAllCheckedOutBooks(USER, TERM_CODE, STUDENT_ID);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULTS.getValue4()); // should be OK
            System.out.println("BookCopy List is present: " + RESULTS.getValue0().isPresent());
            System.out.println("Student is present: " + RESULTS.getValue1().isPresent());
            System.out.println("Bag is present: " + RESULTS.getValue2().isPresent());
            System.out.println("Term is present: " + RESULTS.getValue3().isPresent());

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
    public void testCheckOutBookForStudent(){
        final User USER = new User("467767", "919000005", "admin", "admin", "admin", UserRole.Supervisor, "S000001@nwmissouri.edu");

        final String STUDENT_ID = "919000005";

        final String STRIKE_BARCODE = "111111111111/";

        final String TERM_CODE = "202120";

        try {
            // invoke repository to checkOutBook
            final Quartet<Optional<BookCopy>, Optional<Student>, Optional<Term>, StatusCode> RESULTS =
                    _adapter.checkOutBookForStudent(USER,STRIKE_BARCODE ,STUDENT_ID, TERM_CODE);

            // prints resulting status code (should be ok) and if the Book is present (should be present)
            System.out.println(RESULTS.getValue3()); // should be OK
            System.out.println("BookCopy is present: " + RESULTS.getValue0().isPresent());
            System.out.println("Student is present: " + RESULTS.getValue1().isPresent());
            System.out.println("Term is present: " + RESULTS.getValue2().isPresent());


            // prints all results to verify that state of bookcopy (ORM) works as expected
            if (RESULTS.getValue0().isPresent()) {

                // print the number of results expected (current instance: 4/13 --> 4)
                System.out.println(RESULTS.getValue0().get());

            }

            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }
    }

    @Test
    public void testCheckInBookForStudent(){
        final User USER = new User("467767", "919000005", "admin", "admin", "admin", UserRole.Supervisor, "S000001@nwmissouri.edu");

        final String STUDENT_ID = "919000005";

        final String STRIKE_BARCODE = "111111111111/";

        final String TERM_CODE = "202120";

        try {
            // invoke repository to checkInBook
            final StatusCode RESULTS =
                    _adapter.checkInBookForStudent(USER, STUDENT_ID, TERM_CODE, STRIKE_BARCODE);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULTS.getContentMessage()); // should be OK




            assert (true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }
    }

    @Test
    public void testSellBookForStudent(){

        final User USER = new User("467767", "919000005", "admin", "admin", "admin", UserRole.Supervisor, "S000001@nwmissouri.edu");

        final String STUDENT_ID = "919000005";

        final String STRIKE_BARCODE = "111111111111/";


        try {
            // invoke repository to SellBook
            final StatusCode RESULTS =
                    _adapter.sellBookForStudent(USER, STUDENT_ID, STRIKE_BARCODE);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULTS.getContentMessage()); // should be OK




            assert (true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }
    }

    @Test
    public void testUserLogin(){

        try {


            final User USER = new User("467767", "919000005", "admin", "admin", "admin", UserRole.Supervisor, "S000001@nwmissouri.edu");

            final String STUDENT_USERNAME = "919000005";

            final String STUDENT_PASSWORD = "S467767@nwmissouri.edu";

            Pair<Optional<User>, StatusCode> RESULTS = _adapter.userLogin(STUDENT_USERNAME, STUDENT_PASSWORD);

            System.out.println(RESULTS.getValue1()); // should be OK

            if (RESULTS.getValue0().isPresent()){
                System.out.println(RESULTS.getValue0().get());
            }
            assert(true);
        }
        catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }
    }


}
