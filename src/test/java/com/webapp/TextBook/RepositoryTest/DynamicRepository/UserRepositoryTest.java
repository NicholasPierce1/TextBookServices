package com.webapp.TextBook.RepositoryTest.DynamicRepository;

import com.webapp.TextBook.repository.User.UserRepository;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository _userRepository;

    @Test
    public void testGetPartialUserWithUsernameAndPassword(){

        // supply hard coded student id (pidm)
        final String username = "00000001";

        final String password = "S000001@nwmissouri.edu";

        try{
            // invoke repository to garner partial user from a given username and password
            final Pair<Optional<Object[]>, StatusCode> RESULT =
                    this._userRepository.getPartialUserWithUsernameAndPassword(username, password);

            // prints resulting status code (should be ok) and if the list is present (should be present)
            System.out.println(RESULT.getValue1()); // should be OK
            System.out.println(RESULT.getValue0().isPresent());

            // print result to verify that state of User (ORM) works as expected
            if (RESULT.getValue0().isPresent()) {

                final Object[] user = RESULT.getValue0().get();
                System.out.println(user);
            }

            assert(true);
        }
        catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            assert(false);
        }
    }
}
