package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.data_access.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<BookCopy, UUID>, UserRepositoryCustom {
}
