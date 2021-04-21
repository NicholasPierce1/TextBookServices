package com.webapp.TextBook.repository.BookCopy;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.data_access.BookCopy;
import org.javatuples.Pair;
import com.webapp.TextBook.sharedFiles.StatusCode;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository

/**
 * <h1>BookCopyRepositoryCustom</h1>
 * <h2>Type: Interface</h2>
 *
 * Interface to create stubs for custom procedure for BookCopy entity and extends generic
 * repository that holds the getTableName method to use for all entities.
 */
public interface BookCopyRepositoryCustom extends Repository {

    /**
     *<p>
     * This is a procedure to query the database for records from the BookCopy table that matches
     * the given studentId and termCode.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the name of the term code.
     * @return Optional List of BookCopy objects with Status Code
     */
    @NotNull Pair<Optional<List<BookCopy>>, StatusCode> getAllCheckedOutBooks(@NotNull final String studentId, @NotNull final String termCode);

    /**
     *<p>
     * This is a procedure to query the database for records from the BookCopy table that matches
     * the given strikeBarcode. Then the given BookCopy is checked out and the table is updated.
     *</p>
     *
     * @param strikeBarcode: String strikeBarcode representing the strike barcode, NW's specific barcode for books.
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the name of the term code.
     * @return Optional BookCopy object with Status Code
     */
    @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);

    /**
     *<p>
     * This is a procedure to query the database for records from the BookCopy table that matches
     * the given strikeBarcode, studentId, and termCode. Then the given BookCopy is checked in and the table is updated.
     *</p>
     *
     * @param strikeBarcode: String strikeBarcode representing the strike barcode, NW's specific barcode for books.
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the name of the term code.
     * @return StatusCode to see if process was successful.
     */
    @NotNull StatusCode checkInBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);

    /**
     * <p>
     *  From a given barcode and a student id, attempts to manipulate the state of the corresponding book
     *  to reflect that the book's current disposition is sold (S).
     *</p>
     *
     * @param barcode: String barcode representing the book copy's strike barcode.
     *               This corresponding book should be checked out by the given student.
     * @return StatusCode of a BookCopy.
     */
    @NotNull StatusCode sellBook(@NotNull final String barcode);
}
