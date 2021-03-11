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

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private final EntityManagerFactory _entityManagerFactory;


    // defines an autowired constructor (render by Spring Application Context as a Bean configuration)
    // to established injected member for singleton, repository interaction
    // note: 'new' instances shall never be created
    @Autowired
    PersonRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    public @NotNull Pair<Optional<Student>, StatusCode> getStudentWithCandidateKey(@NotNull final String studentCandidateKey){
        try{
            Pair<Optional<Object[]>, StatusCode> student = getPersonWithCandidateKey(studentCandidateKey);
            Student returnStudent = DataAccessConversionHelper.createDataAccessObject(student.getValue0().get(), Student::new);

            return new Pair<Optional<Student>, StatusCode>(Optional.of(returnStudent), StatusCode.OK);
        }
        catch(RuntimeException ex){
            System.out.println("data conversion error --\n" + ex.getMessage());
            return new Pair<Optional<Student>, StatusCode>(Optional.empty(), StatusCode.DataAccessConversionException);
        }
    }

    public @NotNull Pair< Optional<Student>,  StatusCode> getStudentWithId(@NotNull final String studentId){
        try{
            Pair<Optional<Object[]>, StatusCode> student = getPersonWithId(studentId);
            Student returnStudent = DataAccessConversionHelper.createDataAccessObject(student.getValue0().get(), Student::new);

            return new Pair<Optional<Student>, StatusCode>(Optional.of(returnStudent), StatusCode.OK);
        }
        catch(RuntimeException ex){
            System.out.println("data conversion error --\n" + ex.getMessage());
            return new Pair<Optional<Student>, StatusCode>(Optional.empty(), StatusCode.DataAccessConversionException);
        }
    }

    public @NotNull Pair< Optional<Object[]>, StatusCode > getPartialUserWithId(@NotNull final String userId){
        return getPersonWithId(userId);
    }

    public @NotNull Pair< Optional<Object[]>,  StatusCode> getPartialUserWithCandidateKey(@NotNull final String studentCandidateKey){
        return getPersonWithCandidateKey(studentCandidateKey);
    }

    private @NotNull Pair< Optional<Object[]>, StatusCode> getPersonWithId(@NotNull final String personId){
        final String TABLE_NAME = GetTableName();

        try{
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"SPRIDEN_PIDM\" = ?";
            Query query1 = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));
            query1.setParameter(1, "'" + personId + "'");

            Object[] record = (Object[])query1.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("soft error in get person by pidm\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.UndefinedPerson);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

    private @NotNull Pair< Optional<Object[]>, StatusCode> getPersonWithCandidateKey(@NotNull final String id){
        final String TABLE_NAME = GetTableName();

        try{
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"SPRIDEN_ID\" = ?";
            Query query1 = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));
            query1.setParameter(1, "'" + id + "'");

            Object[] record = (Object[])query1.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("soft error in get person by spriden id\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.PersonCandidateKeyNotFound);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }



    @Override
    public @NotNull String GetTableName() {
        return "\"SPRIDEN\"";
    }
}

