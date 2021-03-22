package com.webapp.TextBook.repository.Person;

import com.webapp.TextBook.repository.BookCopy.BookCopyRepositoryCustom;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.data_access.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>PersonRepository</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to interact with the database exclusively for the Person entity.
 * It also extends JPA for ORM operations and the custom procedures to interact
 * with the Person database table.
 */
@Repository
public interface PersonRepository extends JpaRepository<Student, UUID>, PersonRepositoryCustom {

}
