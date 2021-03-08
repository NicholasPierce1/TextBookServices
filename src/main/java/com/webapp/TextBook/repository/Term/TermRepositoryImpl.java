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

public class TermRepositoryImpl implements TermRepositoryCustom{

    private final EntityManagerFactory _entityManagerFactory;


    // defines an autowired constructor (render by Spring Application Context as a Bean configuration)
    // to established injected member for singleton, repository interaction
    // note: 'new' instances shall never be created
    @Autowired
    TermRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    @Override
    public @NotNull String GetTableName() {
        return "\"STVTERM\"";
    }

    @Override
    public @NotNull Pair<Optional<Term>, StatusCode> getTermWithTermCode(@NotNull String termCode) {
        final String TABLE_NAME = GetTableName();

        try{
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"STVTERM_CODE\" = ?";

            Query query1 = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            query1.setParameter(1, "'" + termCode + "'");

            Object[] record = (Object[])query1.getSingleResult();

            Term returnType = DataAccessConversionHelper.createDataAccessObject(record,Term::new);

            return new Pair<Optional<Term>,StatusCode>(Optional.of(returnType), StatusCode.OK);
        }
        catch (NoResultException ex) {
            System.out.println("soft error in get term by termCode\n" + ex.getMessage());
            return new Pair<Optional<Term>, StatusCode>(Optional.empty(), StatusCode.UndefinedTerm);
        }
        catch (Exception ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Term>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }
}
