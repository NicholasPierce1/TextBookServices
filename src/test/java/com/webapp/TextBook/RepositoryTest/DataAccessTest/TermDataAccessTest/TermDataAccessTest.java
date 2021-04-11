package com.webapp.TextBook.RepositoryTest.DataAccessTest.TermDataAccessTest;

import com.webapp.TextBook.repository.data_access.Term;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest
public class TermDataAccessTest {

    @Test
    public void testUpdateDataAccessObject(){

        // creates input conglomerate, DBO arrays
        final Object[] inputConglomeration1 = {
                "123", "202120"
        };

        // create the expected outputs
        final Term expectedTerm1 = new Term(
                "123",
                "202120"
        );

        // invoke target User method for ORM equality
        assert(DataAccessConversionHelper.createDataAccessObject(inputConglomeration1, Term::new).equals(expectedTerm1));
    }
}
