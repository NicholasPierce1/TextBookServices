package com.webapp.TextBook.ValidationTest.Shared;

import com.webapp.TextBook.validation.Shared.ErrorBinding;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.util.ArrayList;

public class TestErrorBindingHelper{
    @Test
    public void testJsonStringConverter() throws Exception{
        String msg = "test";
        String title  = "title";
//            final String errorBindingJsonString = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(
//                    Arrays.asList(
//                            new ErrorBinding<String>(
//                                    "TEST", test,"String"
//                            ),
//                            new ErrorBinding<Integer>(
//                                    "TEST", test,(Integer)123
//                            ),
//                            new ErrorBinding<Double>(
//                                    "TEST", test,(Double) 4.4
//                            )
//                    )
//            );
        ArrayList<ErrorBinding<?>> ebList = new ArrayList<>();
        ebList.add(new ErrorBinding<String>(title,msg,"String"));
        ebList.add(new ErrorBinding<Double>(title,msg,(Double)4.4));
        ebList.add(new ErrorBinding<Integer>(title, msg, (Integer)44));
        String actual = ErrorBinding.ErrorBindingJsonHelper.CreateJsonStringFromErrorBindings(ebList);
        JSONArray expected = new JSONArray();


        for(ErrorBinding eb: ebList){
            expected.put(eb.toJsonString());
        }
        assert(actual.equals(expected.toString()));


    }



}
