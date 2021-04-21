package com.webapp.TextBook.RepositoryTest.DataAccessTest.StudentDataAccessTest;

import com.webapp.TextBook.repository.data_access.Student;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class StudentDataAccessTest {

    @Test
    public void testUpdateDataAccessObject() {
        // creates input conglomerate, DBO arrays
        final Object[] inputConglomeration1 = {
                new BigDecimal("123"), "id1", "lastName1", "firstName1", "middle1"
        };

        final Object[] inputConglomeration2 = {
                new BigDecimal("456"), "id2", "lastName2", "firstName2", "middle2"
        };

        // create the expected outputs
        final Student expectedStudent1 = new Student(
                "123",
                "id1",
                "firstName1",
                "lastName1",
                "middle1"
        );

        final Student expectedStudent2 = new Student(
                "456",
                "id2",
                "firstName2",
                "lastName2",
                "middle2"
        );

        // invoke target User method for ORM equality
        assert (DataAccessConversionHelper.createDataAccessObject(inputConglomeration1, Student::new).equals(expectedStudent1));
        assert (DataAccessConversionHelper.createDataAccessObject(inputConglomeration2, Student::new).equals(expectedStudent2));
    }
}
