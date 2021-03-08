package com.webapp.TextBook.repository.shared_respository_helper;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.DataAccessConversionException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Supplier;


public class DataAccessConversionHelper {

    public static <U extends List<T>, T extends DataAccessConversion> void createDataAccessObjects(
            @NotNull final List<? extends Object[]> data, @NotNull final U dataList,
            @NotNull final Supplier<T> typeConstructor) throws DataAccessConversionException, IllegalArgumentException {

        if(dataList.size() != 0)
            throw new IllegalArgumentException("list size of data access must be 0");

        try{
            for (Object[] objectData : data) {
                dataList.add(createDataAccessObject(objectData, typeConstructor));
            }
        }
        catch(Exception e){
            throw new DataAccessConversionException("" +
                    "DataAccess type conversion failed. Make sure Object[] matches the column" +
                    "order of the table's DDL implementation and that the DataAccessConversion implementation" +
                    "matches the table's DDL implementation.");
        }

    }

    public static <T extends DataAccessConversion> @NotNull T createDataAccessObject(
            @NotNull final Object[] data,
            @NotNull final Supplier<T> typeConstructor)
            throws DataAccessConversionException, IllegalArgumentException {

        T returnValue = typeConstructor.get();
        returnValue.updateDataAccessObject(data);
        return returnValue;

    }

}
