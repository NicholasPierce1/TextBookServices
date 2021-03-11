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

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final EntityManagerFactory _entityManagerFactory;


    // defines an autowired constructor (render by Spring Application Context as a Bean configuration)
    // to established injected member for singleton, repository interaction
    // note: 'new' instances shall never be created
    @Autowired
    UserRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    @Override
    public @NotNull String GetTableName() {
        return "\"UserTable\"";
    }

    @Override
    public @NotNull Pair<Optional<Object[]>, StatusCode> getPartialUserWithUsernameAndPassword(@NotNull String username, @NotNull String password) {

        final String TABLE_NAME = GetTableName();

        try{
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"username\" = ? AND tableName.\"password\" = ?";
            Query query1 = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));
            query1.setParameter(1, "'" + username + "'");
            query1.setParameter(2, "'" + password + "'");


            Object[] record = (Object[])query1.getSingleResult();

            return new Pair<Optional<Object[]>,StatusCode>((Optional.of(record)), StatusCode.OK);
        }
        catch(NoResultException ex){
            System.out.println("soft error in UserTable by username and password\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.UserNotFound);
        }
        catch(RuntimeException ex){
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<Object[]>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }
}
