package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.sharedFiles.StatusCode;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class BagRepositoryImpl implements BagRepositoryCustom{

    @Autowired
    private BagRepository bagRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public List<Bag> getAll() {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = null;

            transaction = em.getTransaction();
            transaction.begin();

            Query query1 = em.createNativeQuery(
                    "SELECT * from NWTXBN WHERE SPRIDEN_PIDM == NWTXBN_PIDM;"
            );
        }
        catch(RuntimeException ex){}
        return null;
    }

    @Override
    public @NotNull Pair<Optional<Bag>, StatusCode> getStudentBagWithStudentId(@NotNull String studentId) {
        return null;
    }

    @Override
    public @NotNull String GetTableName() {
        return null;
    }
}
