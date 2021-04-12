package com.webapp.TextBook.RepositoryTest.DataAccessTest.BookCopyDataAccessTest;

import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class BookCopyDataAccessTest {

    @Test
    public void testUpdateDataAccessObject() {
        // creates input conglomerate, DBO arrays
        final Object[] inputConglomeration1 = {
                "123", 2020, 456, "789", new BigDecimal("123"), "202020", new Date(), 'c',
                new BigDecimal(34.), new BigDecimal(24.), "201920", new Date(), new Date(), 'w'
        };


        // create the expected outputs
        final BookCopy expectedBookCopy1 = new BookCopy(
                "123",
                2020,
                456,
                "789",
                "123",
                "202020",
                new Date(),
                'c',
                34.,
                24.,
                "201920",
                new Date(),
                new Date(),
                'w'
        );



        // invoke target User method for ORM equality
        assert (DataAccessConversionHelper.createDataAccessObject(inputConglomeration1, BookCopy::new).equals(expectedBookCopy1));
    }
}
