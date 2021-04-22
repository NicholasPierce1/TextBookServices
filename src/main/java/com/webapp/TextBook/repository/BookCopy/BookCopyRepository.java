package com.webapp.TextBook.repository.BookCopy;

import com.webapp.TextBook.repository.data_access.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>BookCopyRepository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to interact with the database exclusively for the BookCopy entity.
 * It also extends JPA for ORM operations and the custom procedures to interact
 * with the BookCopy database table.
 */
@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID>, BookCopyRepositoryCustom{
}
