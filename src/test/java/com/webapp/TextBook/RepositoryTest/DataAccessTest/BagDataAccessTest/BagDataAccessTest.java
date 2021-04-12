package com.webapp.TextBook.RepositoryTest.DataAccessTest.BagDataAccessTest;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * <h1>UserDataAccessTest</h1>
 *
 * <p>
 *     Implements test suites to assess current state and implementation.
 *     At a high-level here's the enumeration of sub-test suite:
 *         -  Assess ORM implementation for Bag creation
 *              (note: this assumes that Shared_Repository_Helper is working properly)
 * </p>
 */

@SpringBootTest
public class BagDataAccessTest {

    @Test
    public void testUpdateDataAccessObject(){

        // creates input conglomerate, DBO arrays
        final Object[] inputConglomeration1 = {
                "123", new BigDecimal(12.34)
        };

        final Object[] inputConglomeration2 = {
                "456", new BigDecimal(34.)
        };

        // create the expected outputs
        final Bag expectedBag1 = new Bag(
                "123",
                12.34
        );

        final Bag expectedBag2 = new Bag(
                "456",
                34.
        );

        // invoke target User method for ORM equality
        assert(DataAccessConversionHelper.createDataAccessObject(inputConglomeration1, Bag::new).equals(expectedBag1));
        assert(DataAccessConversionHelper.createDataAccessObject(inputConglomeration2, Bag::new).equals(expectedBag2));

    }
}
