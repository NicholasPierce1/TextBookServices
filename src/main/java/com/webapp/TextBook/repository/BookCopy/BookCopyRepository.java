package com.webapp.TextBook.repository.BookCopy;

import com.webapp.TextBook.repository.data_access.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID>, BookCopyRepositoryCustom{
}
