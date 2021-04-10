package com.webapp.TextBook.ValidationTest.ApiValidationTest.StudentInfoValidationTest;
import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/** Testing StudentInfoValidatin
 * UsesSpringBootTest framework
 */
@SpringBootTest
public class StudentInfoValidationImplTest {

    /**
     * Going to test the isValid method for Student Info.
     * We will use four cases
     */
    @Test
    public void testIsValid(){
        StudentInfoValidationImpl infoValidation = new StudentInfoValidationImpl();

        //1 CASE ONE: Invalid term code
         StudentInfo badTerm = new StudentInfo("919545753", "190060");
        // assert !infoValidation.isValid(badTerm, ) ;
         //CASE TWO: Invalid 919
        StudentInfo badId = new StudentInfo("123456789", "201810");
     //   assert !infoValidation.isValid(badId, ) ;

        //CASE THREE: Both are invalid
        StudentInfo bothInvalid = new StudentInfo("123456123", "6666666");
       // assert !infoValidation.isValid((bothInvalid,);
        //CASE FOUR: Both Valid
        StudentInfo good = new StudentInfo("919545753", "202020");
        //assert infoValidation.isValid(good,);

    }

}
