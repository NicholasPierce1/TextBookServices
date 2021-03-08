package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.data_access.Bag;
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
            Query query1 = em.createNativeQuery( "SELECT  ?.* FROM ? WHERE ?.\"NWTXBN_PIDM\" = ?");

            for(int i = 1; i < 4; i++)
                query1.setParameter(i, GetTableName());

            query1.setParameter(4, "'" + studentId + "'");

            List<Object[]> records = query1.getResultList();

//            if() {
//
//            }

            List<Bag> recordsArray = new ArrayList<Bag>();
            for(int i = 0; i< records.size(); i++){
                recordsArray.add(new Bag());
                recordsArray.get(i).updateDataAccessObject(records.get(i));
            }

        }
        catch(RuntimeException ex) {

        }

        return null;
    }

    @Override
    public @NotNull String GetTableName() {
        return "\"NWTXBN\"";
    }
}
