package com.webapp.TextBook.repository.User;

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
 * <h1>UserRepositoryImpl</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods to access the database for functional requirement data
 * pulled from the User entity.
 */
public class UserRepositoryImpl implements UserRepositoryCustom{

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
    UserRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    /**
     * <p>
     *  Implementation of generic method to retrieve the tableName of the User table from the database.
     *</p>
     *
     * @return Name of the User table in the database.
     */
    @Override
    public @NotNull String GetTableName() {
        return "\"UserTable\"";
    }

    /**
     *<p>
     * This is a procedure to query the database for a record from the User table that matches
     * the given username and password.
     *</p>
     *
     * @param username: String username representing their pidm.
     * @param password: String password representing their S_Number.
     * @return Optional generic object with Status Code
     */
    @Override
    public @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull String username, @NotNull String password) {

        final String TABLE_NAME = GetTableName();

        // Try block to catch errors in the process of accessing the database.
        try{
            System.out.println(username);
            System.out.println(password);
            // Create the EntityManager and writing the query to access a Bag record that matches the pidm
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"username\" = ? AND tableName.\"password\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getPartialUserQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the username and password from the method call to the query.
            getPartialUserQuery.setParameter(1, username);
            getPartialUserQuery.setParameter(2, password);

            // Saving the result from the query
            Object[] record = (Object[])getPartialUserQuery.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("Internal Error: UserRepositoryImpl--getPartialUserWithUsernameAndPassword--\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.UserNotFound);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }
}
