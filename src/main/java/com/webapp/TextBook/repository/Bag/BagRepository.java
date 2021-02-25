package com.webapp.TextBook.repository.Bag;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepositoryCustom;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BagRepository extends JpaRepository<Bag, UUID>, BagRepositoryCustom {
}
