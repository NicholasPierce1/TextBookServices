package com.webapp.TextBook.ValidationTest.ApiValidationTest.StudnetBookInfoValidationImplTest;
import com.webapp.TextBook.validation.ApiValidation.ApiValidationHandler.ApiValidationHandler;
import com.webapp.TextBook.validation.Shared.ErrorBinding;
import com.webapp.TextBook.validation.Shared.SharedValidationState;
import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

/** Tests logic concerning StudentBookInfoValidationImpl
 *
 */
@SpringBootTest
public class StudentBookInfoValidationImplTest {
    @Autowired
    private ApiValidationHandler validator;
    final StudentInfo validStudentInput = new StudentInfo("919545753", "202020");
    final StudentInfo invalidStudentIdInput = new StudentInfo("123456123", "202020");
    final StudentInfo invalidStudentBothInput = new StudentInfo("819545753", "102020");
    final String badBarCode = "017510123456";
    final String goodBarcode = "307510209732$";

    //  @Test
    /** Assumes Student info validation is working
     * Tests the isValid logic for StudentBookInfo
     *
     */
//    public void testIsValid(){
//        //Setting up test data
//       // private final ApiValidationHandler _validationHandler ;
//      //  StudentBookInfoValidationImpl studnetBookInfo= new StudentBookInfoValidationImpl();
//        StudentInfo badTerm = new StudentInfo("919545753", "190060");
//        StudentInfo bothInvalid = new StudentInfo("123456123", "6666666");
//        StudentInfo goodStudent = new StudentInfo("919545753", "202020");
//        String badBarcode = "017510123456";
//        String goodBarcode = "317510123456";
//        //CASE ONE A: Invalid student info: both
//        StudentBookInfo badStuInfoPartial =  new StudentBookInfo(goodBarcode,badTerm);
//       // assert !studnetBookInfo.isValid(badStuInfoPartial,);
//        //CASE ONE B: Invalid student info: both
//        StudentBookInfo badStuInfoBoth = new StudentBookInfo(goodBarcode, bothInvalid);
//       // assert !studnetBookInfo.isValid(badStuInfoBoth, );
//        //CASE TWO: bad barcode
//        StudentBookInfo badBarcodeOnly = new StudentBookInfo(badBarcode,goodStudent);
//        //assert !studnetBookInfo.isValid(badBarcodeOnly,);
//        //Case THREE: Everything is wrong
//        StudentBookInfo everythingInvalid = new StudentBookInfo(badBarcode, bothInvalid);
//       // assert  !studnetBookInfo.isValid((everythingInvalid), );

    //}

    /**
     *
     * Going to test for valid input for a given
     * @throws Exception
     */
    @Test
    public void testValid() throws  Exception{
        StudentBookInfo validInfo = new StudentBookInfo(goodBarcode, validStudentInput);
        final Optional<String> validStudnetBookInfoResult = this.validator.getApiBindingError(validInfo);



        // asserts no errors uncovered (optional is empty) and password transformation is of expected

        // Testing to make sure list is empty
       // System.out.println("VALUE: " + validStudnetBookInfoResult.get());

        assert(validStudnetBookInfoResult.isEmpty());


    }
    @Test
    public void testBadStudnetPartial() throws  Exception {
        StudentBookInfo invalidStundetBookInfoPartialStudent = new StudentBookInfo(goodBarcode, invalidStudentIdInput);

        final Optional<String> invalidStudnetBookInfoResult = this.validator.getApiBindingError(invalidStundetBookInfoPartialStudent);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList( //err msg for student info
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_ID, "Invalid 919 format", null
                        )

                )
        );
        assert(invalidStudnetBookInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetBookInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetBookInfoResult.get()));

    }

    /**
     * Tests for bad student IFO if both are bad
     */
    @Test
    public void testStudentInfoBothInvalid() throws Exception{
        StudentBookInfo invalidStundetBookInfoBothStudent = new StudentBookInfo(goodBarcode, invalidStudentBothInput);

        final Optional<String> invalidStudnetBookInfoResult = this.validator.getApiBindingError(invalidStundetBookInfoBothStudent);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList( //err msg for student info
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_ID, "Invalid 919 format", null
                        ),
                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_TERM_CODE, "Invalid term code format",null
                        )

                )
        );
        assert(invalidStudnetBookInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetBookInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetBookInfoResult.get()));
    }

    /**
     * Tests for invalid Barcode
     */
    @Test
    public void testInvalidBarcode() throws  Exception{
        StudentBookInfo invalidStundetBookInfoBarcode = new StudentBookInfo(badBarCode, validStudentInput);

        final Optional<String> invalidStudnetBookInfoResult = this.validator.getApiBindingError(invalidStundetBookInfoBarcode);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList( //err msg for student info
                        new ErrorBinding<String>(
                                StudentBookInfo.NOMINAL_BARCODE, "Invalid Barcode", null
                        )

                )
        );
        assert(invalidStudnetBookInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetBookInfoResult.get()));

        // assert json array error string is expected

        assert(errorBindingJsonString.equals(invalidStudnetBookInfoResult.get()));


    }

    /**
     * Test both invalid inputs, where the studnet is partially wrong
     */
    @Test
    public void testBothInvalid() throws  Exception{
        StudentBookInfo invalidStundetBookInfoBoth = new StudentBookInfo(badBarCode, invalidStudentIdInput);

        final Optional<String> invalidStudnetBookInfoResult = this.validator.getApiBindingError(invalidStundetBookInfoBoth);
        final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
                Arrays.asList( //err msg for student info
                        new ErrorBinding<String>(
                                StudentBookInfo.NOMINAL_BARCODE, "Invalid Barcode", null
                        ),

                        new ErrorBinding<String>(
                                StudentInfo.NOMINAL_ID, "Invalid 919 format", null
                        )

                )
        );
        assert(invalidStudnetBookInfoResult.isPresent());

        // assert error string is not generic
        assert(!SharedValidationState.isGenericErrorMessage(invalidStudnetBookInfoResult.get()));

        // assert json array error string is expected
        System.out.println("EXPECTED: " + errorBindingJsonString);
        System.out.println("ACTUAL: " + invalidStudnetBookInfoResult.get());
        assert(errorBindingJsonString.equals(invalidStudnetBookInfoResult.get()));
    }


}
