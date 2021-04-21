package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepositoryCustom;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>BagRepository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to interact with the database exclusively for the Bag entity.
 * It also extends JPA for ORM operations and the custom procedures to interact
 * with the Bag database table.
 */
@Repository
public interface   BagRepository extends JpaRepository<Bag, UUID>, BagRepositoryCustom {
}
