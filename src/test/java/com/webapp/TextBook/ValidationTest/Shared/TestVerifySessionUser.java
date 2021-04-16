package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.sharedFiles.SharedSessionData;
import com.webapp.TextBook.sharedFiles.VerifySessionUser;
import com.webapp.TextBook.viewModel.sharedViewModel.loginUserInfo.LoginUserInfo;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Tests Verify session user
 * PRE: set session user
 * POST: Remove session user
 */
public class TestVerifySessionUser {
    /**
     * Tets verify method
     */
    @Test
    public void testVerfiy(){
        LoginUserInfo user = new LoginUserInfo("919123454", "s123456");
        //Setting user
        SharedSessionData.setSessionValueWithKey(SharedSessionData.USER_KEY, user);
        LoginUserInfo notUser = new LoginUserInfo("123456789", "asdfa");
        Triplet<Boolean, String, Optional<User>> result = VerifySessionUser.isSessionUserValid(user);
        assert(result.getValue0());
        result = VerifySessionUser.isSessionUserValid(notUser);
        assert(!result.getValue0());
    }
}
