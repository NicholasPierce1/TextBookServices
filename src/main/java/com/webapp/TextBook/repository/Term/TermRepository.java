package com.webapp.TextBook.repository.Term;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepositoryCustom;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TermRepository extends JpaRepository<Term, UUID>, TermRepositoryCustom {
}
