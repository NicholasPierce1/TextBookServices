package com.webapp.TextBook.RepositoryTest.DataAccessTest.UserDataAccessTest;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.data_access.User;
import com.webapp.TextBook.repository.data_access.UserRole;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>UserDataAccessTest</h1>
 *
 * <p>
 *     Implements test suites to assess current state and implementation.
 *     At a high-level here's the enumeration of sub-test suites:
 *         -  Assess static helper to conglomerate DBO's (user & spriden) combine properly
 *         -  Assess ORM implementation for User creation
 *              (note: this assumes that Shared_Repository_Helper is working properly)
 * </p>
 */
@SpringBootTest
public class UserDataAccessTest {

    /**
     * <p>
     *     Test to assess static helper to conglomerate DBO's (user & spriden) combine properly.
     *
     *     Given/Assumptions:
     *         - Spriden and User's respective DBO will be in correct form.
     *
     *     Test case enumeration (outputs):
     *         - two Spriden arrays (both configured correctly as given) &
     *         two user arrays (both configured correctly as given).
     *
     *     Test case enumeration (transformation):
     *         -  no transformation occur in any input scenario
     * </p>
     */
    @Test
    public void testConglomerateDbo(){

        // Spriden defintion:
        // order: pkey, id, lastName, firstName, middleName

        // User (partial) definition:
        // username, password, userRole

        // note: username is id (919)

        // create two Spriden DBOs
        final Object[] spridenDbo1 = {"key1", "id1", "lastName1", "firstName1", "middle"};
        final Object[] spridenDbo2 = {"key2", "id2", "lastName2", "firstName2", "middle"};

        // create two User (partial) DBOs
        final Object[] userDbo1 = {"username1", "password1", UserRole.Supervisor.get_NOMINAL_VALUE()};
        final Object[] userDbo2 = {"username2", "password2", UserRole.StudentEmployee.get_NOMINAL_VALUE()};

        // creates expected results
        final Object[] expectedConglomeration1 = {
                "key1", "id1", "lastName1", "firstName1", "middle",
                "password1", UserRole.Supervisor.get_NOMINAL_VALUE()
        };

        final Object[] expectedConglomeration2 = {
                "key2", "id2", "lastName2", "firstName2", "middle",
                "password2", UserRole.StudentEmployee.get_NOMINAL_VALUE()
        };

        // invoke targeted User method and assert equality
        assert(Arrays.equals(User.conglomerateDboArrays(spridenDbo1, userDbo1), expectedConglomeration1));
        assert(Arrays.equals(User.conglomerateDboArrays(spridenDbo2, userDbo2), expectedConglomeration2));

    }

    /**
     * <p>
     *     Test to assess ORM conversion to procure User objects from a conglomerate DBO partial
     *
     *     Given/Assumptions:
     *         - conglomerate of Spriden and User's respective DBO will be in correct form.
     *
     *     Test case enumeration (outputs):
     *         - two conglomerations are given and resulting User is expected
     *
     *     Test case enumeration (transformation):
     *         -  no transformation occur in any input scenario
     * </p>
     */
    @Test
    public void testUpdateDataAccessObject(){

        // creates input conglomerate, DBO arrays
        final Object[] inputConglomeration1 = {
                new BigDecimal("123"), "id1", "lastName1", "firstName1", "middle",
                "password1", UserRole.Supervisor.get_NOMINAL_VALUE()
        };

        final Object[] inputConglomeration2 = {
                new BigDecimal("456"), "id2", "lastName2", "firstName2", "middle",
                "password2", UserRole.StudentEmployee.get_NOMINAL_VALUE()
        };

        // create the expected outputs
        final User expectedUser1 = new User(
                "123",
                "id1",
                "firstName1",
                "lastName1",
                "middle",
                UserRole.Supervisor,
                "password1");

        final User expectedUser2 = new User(
                "456",
                "id2",
                "firstName2",
                "lastName2",
                "middle",
                UserRole.StudentEmployee,
                "password2");

        // invoke target User method for ORM equality
        assert(DataAccessConversionHelper.createDataAccessObject(inputConglomeration1, User::new).equals(expectedUser1));
        assert(DataAccessConversionHelper.createDataAccessObject(inputConglomeration2, User::new).equals(expectedUser2));
    }

}
