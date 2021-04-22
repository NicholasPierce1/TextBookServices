package com.webapp.TextBook.repository.User;

import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>UserRepository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to interact with the database exclusively for the User entity.
 * It also extends JPA for ORM operations and the custom procedures to interact
 * with the User database table.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID>, UserRepositoryCustom {
}
