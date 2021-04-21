package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import com.webapp.TextBook.repository.shared_respository_helper.QueryTableNameModifier;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h1>BagRepositoryImpl</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods to access the database for functional requirement data
 * pulled from the Bag entity.
 */
public class BagRepositoryImpl implements BagRepositoryCustom{

    /**
     * <p>
     * Instantiation of an EntityManagerFactory
     * Usage: Used to access the database and manage resources efficiently.
     * </p>
     */
    private final EntityManagerFactory _entityManagerFactory;

    /**
     * defines an autowired constructor (render by Spring Application Context as a Bean configuration)
     * Usage: To established injected member for singleton, repository interaction
     * note: 'new' instances shall never be created
     */
    @Autowired
    BagRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    /**
     *<p>
     * This is a procedure to query the database for a record from the Bag table that matches
     * the given studentId.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @return Optional Bag object with Status Code
     */
    @Override
    public @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull String studentId) {

        final String TABLE_NAME = GetTableName();

        // Try block to catch errors in the process of accessing the database.
        try {

            // Create the EntityManager and writing the query to access a Bag record that matches the pidm
            // In this method the studentId is the pidm requested.
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"NWTXBN_PIDM\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getBagQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the studentId from the method call to the query.
            getBagQuery.setParameter(1, studentId);

            // Saving the result from the query
            Object[] record = (Object[])getBagQuery.getSingleResult();

            // Creates a new Bag object using the information stored in the query result.
            Bag returnType = DataAccessConversionHelper.createDataAccessObject(record,Bag::new);
            
            return new Pair<Optional<Bag>, StatusCode>(Optional.of(returnType), StatusCode.OK);
        }
        catch(NoResultException ex) {
            System.out.println("Internal Error: BagRepositoryImpl--getStudentBagWithStudentId--\n" + ex.getMessage());
            return new Pair<Optional<Bag>, StatusCode>(Optional.empty(), StatusCode.StudentNotVerified);
        }
        catch(Exception ex) {
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Bag>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

    /**
     * <p>
     *  Implementation of generic method to retrieve the tableName of the Bag table from the database.
     *</p>
     *
     * @return Name of the Bag table in the database.
     */
    @Override
    public @NotNull String GetTableName() {
        return "\"NWTXBN\"";
    }
}
