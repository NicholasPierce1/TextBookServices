package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepositoryCustom;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>TermRepository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to interact with the database exclusively for the Term entity.
 * It also extends JPA for ORM operations and the custom procedures to interact
 * with the Term database table.
 */
@Repository
public interface TermRepository extends JpaRepository<Term, UUID>, TermRepositoryCustom {
}
