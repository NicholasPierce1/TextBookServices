package com.webapp.TextBook.repository.shared_respository_helper;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.DataAccessConversionException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Supplier;


/**
 * <h1>DataAccessConversionHelper</h1>
 * <h2>Type: Class</h2>
 *
 * Implementing Helper methods to create data access objects(DAOs)
 * from the data from the database
 */
public class DataAccessConversionHelper {

    /**
     *<p>
     * This is a Helper method that takes the data from the database and
     * the constructor of the specified entity to create multiple data access objects
     *</p>
     *
     * @param data: Object[] data representing the information from database.
     * @param dataList: generic list of data access objects
     * @param typeConstructor: Lambda typeConstructor representing the constructor for the specified entity type.
     */
    public static <U extends List<T>, T extends DataAccessConversion> void createDataAccessObjects(
            @NotNull final List<? extends Object[]> data, @NotNull final U dataList,
            @NotNull final Supplier<T> typeConstructor) throws DataAccessConversionException, IllegalArgumentException {
        // making sure the list is == 0 before adding DAOs
        if(dataList.size() != 0)
            throw new IllegalArgumentException("list size of data access must be 0");
        // loops through data and adds each DAO created to the list
        try{
            for (Object[] objectData : data) {
                dataList.add(createDataAccessObject(objectData, typeConstructor));
            }
        }
        // checks to make sure DDL is matches up properly
        catch(Exception e){
            throw new DataAccessConversionException("" +
                    "DataAccess type conversion failed. Make sure Object[] matches the column" +
                    "order of the table's DDL implementation and that the DataAccessConversion implementation" +
                    "matches the table's DDL implementation.");
        }

    }
    /**
     *<p>
     * This is a Helper method that takes the data from the database and
     * the constructor of the specified entity to create a data access object
     *</p>
     *
     * @param data: Object[] data representing the information from database.
     * @param typeConstructor: Lambda typeConstructor representing the constructor for the specified entity type.
     * @return  Returning the created data access object
     */

    public static <T extends DataAccessConversion> @NotNull T createDataAccessObject(
            @NotNull final Object[] data,
            @NotNull final Supplier<T> typeConstructor)
            throws DataAccessConversionException, IllegalArgumentException {
        // takes the specified type and then updates the DAO with the data
        T returnValue = typeConstructor.get();
        returnValue.updateDataAccessObject(data);
        return returnValue;

    }

}
