package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.data_access.Term;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import com.webapp.TextBook.repository.shared_respository_helper.QueryTableNameModifier;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * <h1>TermRepositoryImpl</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods to access the database for functional requirement data
 * pulled from the Term entity.
 */
public class TermRepositoryImpl implements TermRepositoryCustom{

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
    TermRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    /**
     * <p>
     *  Implementation of generic method to retrieve the tableName of the Term table from the database.
     *</p>
     *
     * @return Name of the Term table in the database.
     */
    @Override
    public @NotNull String GetTableName() {
        return "\"STVTERM\"";
    }

    /**
     *<p>
     * This is a procedure to query the database for a record from the Term table that matches
     * the given termCode.
     *</p>
     *
     * @param termCode: String termCode representing the name of the term code.
     * @return Optional Term object with Status Code
     */
    @Override
    public @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull String termCode) {
        final String TABLE_NAME = GetTableName();

        // Try block to catch errors in the process of accessing the database.
        try{

            // Create the EntityManager and writing the query to access a Term record that matches the termCode
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"STVTERM_CODE\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getTermQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the termCode from the method call to the query.
            getTermQuery.setParameter(1, "'" + termCode + "'");

            // Saving the result from the query
            Object[] record = (Object[])getTermQuery.getSingleResult();

            // Creates a new Term object using the information stored in the query result.
            Term returnType = DataAccessConversionHelper.createDataAccessObject(record,Term::new);

            return new Pair<Optional<Term>,StatusCode>(Optional.of(returnType), StatusCode.OK);
        }
        catch (NoResultException ex) {
            System.out.println("Internal Error: TermRepositoryImpl--getTermWithTermCode--\n" + ex.getMessage());
            return new Pair<Optional<Term>, StatusCode>(Optional.empty(), StatusCode.UndefinedTerm);
        }
        catch (Exception ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Term>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }
}
