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

public class BagRepositoryImpl implements BagRepositoryCustom{


    private final EntityManagerFactory _entityManagerFactory;

    @Autowired
    BagRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }

    @Override
    public @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull String studentId) {

        final String TABLE_NAME = GetTableName();

        try {
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT  tableName.* FROM tableName WHERE tableName.\"NWTXBN_PIDM\" = ?";

            Query query1 = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            query1.setParameter(1, "'" + studentId + "'");

            List<Object[]> records = query1.getResultList();

            if(records.size() != 1) {
                return new Pair<Optional<Bag>, StatusCode>(Optional.empty(), StatusCode.StudentNotVerified);
            }

            List<Bag> recordsBagList = new ArrayList<Bag>();
            DataAccessConversionHelper.createDataAccessObjects(records,recordsBagList,Bag::new);
            
            return new Pair<Optional<Bag>, StatusCode>(Optional.of(recordsBagList.get(0)), StatusCode.OK);
        }
        catch(RuntimeException ex) {
            return new Pair<Optional<Bag>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

    @Override
    public @NotNull String GetTableName() {
        return "\"NWTXBN\"";
    }
}
