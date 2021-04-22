package com.webapp.TextBook.ViewModel;

import com.webapp.TextBook.viewModel.apiViewModel.StudentInfo;
import org.hibernate.boot.model.source.spi.CascadeStyleSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.swing.text.html.Option;
import java.util.Optional;

public class TestStudentInfo {
    /**
     * Tests the view model
     */
    @Test
    public void testStudentInfo() throws Exception{
        JSONObject valid = new JSONObject();
        // testing vlaid
        valid.put(StudentInfo.NOMINAL_ID, "919123456");
        valid.put(StudentInfo.NOMINAL_TERM_CODE, "201920");
        Optional<StudentInfo> result = StudentInfo.createApiFromJson(valid, null);
        assert(!result.isEmpty());
        //Test to see if values are expected
        if(result.isPresent()){
            StudentInfo actual = result.get();
            StudentInfo expected = new StudentInfo("919123456", "201920");
           assert( actual.equals(expected));
        }

        // Testing invalid
        JSONObject invalid = new JSONObject();
        valid.put(StudentInfo.NOMINAL_TERM_CODE, "201921");
        result = StudentInfo.createApiFromJson(invalid, null);
        assert(result.isEmpty());


    }

}
