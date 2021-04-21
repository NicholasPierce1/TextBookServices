package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * Tests Verify session user
 * PRE: set session user
 * POST: Remove session user
 */
@SpringBootTest
public class TestVerifySessionUser {
    /**
     * Tets verify method
     */
    @Test
    public void testVerify(){
        final User user = new User("12345678", "919123456", "testPerson", "lastName", "Alex", UserRole.Supervisor, "s123456");

        final LoginUserInfo sessionUser = new LoginUserInfo("919123456", "s123456");

        final String expectedStringError =
                "User state not set or does not match session. Please reset the session by logging in. Not your problem? Please contact an IT Support member.";

        //Setting user
        SharedSessionData.setSessionValueWithKey(SharedSessionData.USER_KEY, user);
        final LoginUserInfo notUser = new LoginUserInfo("123456789", "asdfa");
        Triplet<Boolean, String, Optional<User>> result = VerifySessionUser.isSessionUserValid(sessionUser);
        assert(result.getValue0());
        assert(result.getValue1().equals(""));
        assert(result.getValue2().isPresent());

        final User acquiredUser = result.getValue2().get();

        assert(acquiredUser.getId().equals(user.getId()) && acquiredUser.getPassword().equals(user.getPassword()));

        result = VerifySessionUser.isSessionUserValid(notUser);
        assert(!result.getValue0());
        assert(result.getValue1().equals(expectedStringError));
        assert(result.getValue2().isEmpty());

    }
}
