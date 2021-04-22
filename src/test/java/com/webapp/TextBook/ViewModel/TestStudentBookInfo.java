package com.webapp.TextBook.ViewModel;

import com.webapp.TextBook.viewModel.apiViewModel.StudentBookInfo;
import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Optional;

/**
 * Tests the Viewmodel for Student Book info
 */
public class TestStudentBookInfo {
    @Test
    public void testtudentBookInfoViiewModel() throws  Exception{
        StudentInfo student = new StudentInfo("919123456", "202010");
        String barcode = "307510209732$";
        JSONObject valid = new JSONObject();
        valid.put(StudentBookInfo.NOMINAL_BARCODE, barcode);
        valid.put(StudentBookInfo.NOMINAL_STUDENT_INFO, student);
        Optional validResult= StudentBookInfo.createApiFromJson(valid, null);
        assert(validResult.isPresent());
        if(validResult.isPresent()){
            //Checks to make sure the vlaues are epxcted
            StudentBookInfo actual = (StudentBookInfo) validResult.get();
            StudentBookInfo expected= new StudentBookInfo(barcode, student);
            actual.equals(expected);
        }
        //Tests for bad or invalid input.
        JSONObject invalid = new JSONObject();
        invalid.put("Garbage", "Nonsense");
        Optional invalidResult = StudentBookInfo.createApiFromJson(invalid, null);
        assert (invalidResult.isEmpty());

    }
}
