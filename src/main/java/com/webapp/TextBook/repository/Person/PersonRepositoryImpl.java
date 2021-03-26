package com.webapp.TextBook.repository.Person;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.Person;
import com.webapp.TextBook.repository.data_access.Student;
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
import java.lang.reflect.Constructor;
import java.util.Optional;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Constructor;

/**
 * <h1>PersonRepositoryImpl</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods to access the database for functional requirement data
 * pulled from the Person entity.
 */
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    /**
     * <p>
     * Instantiation of an EntityManagerFactory
     * Usage: Used to access the database and manage resources efficiently.
     * </p>
     */
    private final EntityManagerFactory _entityManagerFactory;


    /**
     * Defines an autowired constructor (render by Spring Application Context as a Bean configuration)
     * Usage: To established injected member for singleton, repository interaction
     * note: 'new' instances shall never be created
     */
    @Autowired
    PersonRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    /**
     *<p>
     * This method calls a generic query request for a person using a 919 number
     * and then transitions that person into a student.
     *</p>
     *
     * @param studentCandidateKey: String studentCandidateKey representing the 919 number.
     * @return Optional Student object with Status Code
     */
    public @NotNull Pair<Optional<Student>, StatusCode> getStudentWithCandidateKey(@NotNull final String studentCandidateKey){
        // Try block to catch errors in the process of converting a Person record into a Student.
        try{
            Pair<Optional<Object[]>, StatusCode> student = getPersonWithCandidateKey(studentCandidateKey);

            // Creates a new Student object using the Person object retrieved.
            Student returnStudent = DataAccessConversionHelper.createDataAccessObject(student.getValue0().get(), Student::new);

            return new Pair<Optional<Student>, StatusCode>(Optional.of(returnStudent), StatusCode.OK);
        }
        catch(RuntimeException ex){
            System.out.println("Internal Error: PersonRepositoryImpl--getStudentWithCandidateKey--\n" + ex.getMessage());
            return new Pair<Optional<Student>, StatusCode>(Optional.empty(), StatusCode.DataAccessConversionException);
        }
    }

    /**
     *<p>
     * This method calls a generic query request for a person using the unique string of a 919 number
     * and then transitions that person into a student.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @return Optional Student object with Status Code
     */
    public @NotNull Pair< Optional<Student>,  StatusCode> getStudentWithId(@NotNull final String studentId){
        // Try block to catch errors in the process of converting a Person record into a Student.
        try{
            Pair<Optional<Object[]>, StatusCode> student = getPersonWithId(studentId);

            // Creates a new Student object using the Person object retrieved.
            Student returnStudent = DataAccessConversionHelper.createDataAccessObject(student.getValue0().get(), Student::new);

            return new Pair<Optional<Student>, StatusCode>(Optional.of(returnStudent), StatusCode.OK);
        }
        catch(RuntimeException ex){
            System.out.println("Internal Error: PersonRepositoryImpl--getStudentWithId--\n" + ex.getMessage());
            return new Pair<Optional<Student>, StatusCode>(Optional.empty(), StatusCode.DataAccessConversionException);
        }
    }

    /**
     *<p>
     * This method calls a generic query request for a person using a unique string for a 919 number
     * and then returns that record as an Object that has not been converted to a User yet, but
     * reduces the need to search for a User when a User is also a Person.
     *</p>
     *
     * @param userId: String userId representing the unique string for a 919 number.
     * @return Optional Object with Status Code
     */
    public @NotNull Pair< Optional<Object[]>, StatusCode > getPartialUserWithId(@NotNull final String userId){
        return getPersonWithId(userId);
    }

    /**
     *<p>
     * This method calls a generic query request for a person using a 919 number
     * and then returns that record as an Object that has not been converted to a User yet, but
     * reduces the need to search for a User when a User is also a Person.
     *</p>
     *
     * @param studentCandidateKey: String studentCandidateKey representing a 919 number.
     * @return Optional Object with Status Code
     */
    public @NotNull Pair< Optional<Object[]>,  StatusCode> getPartialUserWithCandidateKey(@NotNull final String studentCandidateKey){
        return getPersonWithCandidateKey(studentCandidateKey);
    }

    /**
     *<p>
     * This is a procedure for a generic query request for a Person using a unique string for a 919 number.
     *</p>
     *
     * @param personId: String personId representing the unique string for a 919 number.
     * @return Optional Object with Status Code
     */
    private @NotNull Pair< Optional<Object[]>, StatusCode> getPersonWithId(@NotNull final String personId){
        final String TABLE_NAME = GetTableName();

        // Try block to catch errors in the process of accessing the database.
        try{

            // Create the EntityManager and writing the query to access a Person record that matches the personId
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"SPRIDEN_PIDM\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getPersonQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the personId from the method call to the query.
            getPersonQuery.setParameter(1, personId);

            // Saving the result from the query
            Object[] record = (Object[])getPersonQuery.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("Internal Error: PersonRepositoryImpl--getPersonWithId--\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.UndefinedPerson);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

    /**
     *<p>
     * This is a procedure for a generic query request for a Person using a 919 number.
     *</p>
     *
     * @param id: String id representing a 919 number.
     * @return Optional Object with Status Code
     */
    private @NotNull Pair< Optional<Object[]>, StatusCode> getPersonWithCandidateKey(@NotNull final String id){
        final String TABLE_NAME = GetTableName();

        // Try block to catch errors in the process of accessing the database.
        try{

            // Create the EntityManager and writing the query to access a Person record that matches the id
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"SPRIDEN_ID\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getPersonQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the id from the method call to the query.
            getPersonQuery.setParameter(1, id);

            // Saving the result from the query
            Object[] record = (Object[])getPersonQuery.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("Internal Error: PersonRepositoryImpl--getPersonWithCandidateKey--\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.PersonCandidateKeyNotFound);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }


    /**
     * <p>
     *  Implementation of generic method to retrieve the tableName of the Person table from the database.
     *</p>
     *
     * @return Name of the Person table in the database.
     */
    @Override
    public @NotNull String GetTableName() {
        return "\"SPRIDEN\"";
    }
}

