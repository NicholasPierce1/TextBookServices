package com.webapp.TextBook.ValidationTest.ApiValidationTest.StudnetBookInfoValidationImplTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.ApiValidation.StudnetBookInfoValidation.StudentBookInfoValidationImpl;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** Tests logic concerning StudentBookInfoValidationImpl
 *
 */
@SpringBootTest
public class StudentBookInfoValidationImplTest {
    @Test
    /** Assumes Student info validation is working
     * Tests the isValid logic for StudentBookInfo
     *
     */
    public void testIsValid(){
        //Setting up test data
       // private final ApiValidationHandler _validationHandler ;
      //  StudentBookInfoValidationImpl studnetBookInfo= new StudentBookInfoValidationImpl();
        StudentInfo badTerm = new StudentInfo("919545753", "190060");
        StudentInfo bothInvalid = new StudentInfo("123456123", "6666666");
        StudentInfo goodStudent = new StudentInfo("919545753", "202020");
        String badBarcode = "017510123456";
        String goodBarcode = "317510123456";
        //CASE ONE A: Invalid student info: both
        StudentBookInfo badStuInfoPartial =  new StudentBookInfo(goodBarcode,badTerm);
       // assert !studnetBookInfo.isValid(badStuInfoPartial,);
        //CASE ONE B: Invalid student info: both
        StudentBookInfo badStuInfoBoth = new StudentBookInfo(goodBarcode, bothInvalid);
       // assert !studnetBookInfo.isValid(badStuInfoBoth, );
        //CASE TWO: bad barcode
        StudentBookInfo badBarcodeOnly = new StudentBookInfo(badBarcode,goodStudent);
        //assert !studnetBookInfo.isValid(badBarcodeOnly,);
        //Case THREE: Everything is wrong
        StudentBookInfo everythingInvalid = new StudentBookInfo(badBarcode, bothInvalid);
       // assert  !studnetBookInfo.isValid((everythingInvalid), );

    }

    /**
     * Tests the logic in HandelStudnetInfoValidation.
     * Assumes Student info validation is working
     *
     */
    @Test
    public void testHandleStudentInfoValidation(){

    }


}
