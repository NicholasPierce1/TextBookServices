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
     * <p>
     *  Stub for getAllCheckedOutBooks procedure for BookCopy database table.
     *</p>
     *
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the exact year and trimester.
     * @return List of Optional BookCopy objects with Status Codes.
     */
    @NotNull Pair<Optional<List<BookCopy>>, StatusCode> getAllCheckedOutBooks(@NotNull final String studentId, @NotNull final String termCode);

    /**
     * <p>
     *  Stub for checkOutBook procedure for BookCopy database table.
     *</p>
     *
     * @param strikeBarcode: String strikeBarcode representing the strike barcode, NW's specific barcode.
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the exact year and trimester.
     * @return Optional BookCopy object with Status Code.
     */
    @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);

    /**
     * <p>
     *  Stub for checkInBook procedure for BookCopy database table.
     *</p>
     *
     * @param strikeBarcode: String strikeBarcode representing the strike barcode, NW's specific barcode.
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @param termCode: String termCode representing the exact year and trimester.
     * @return StatusCode of a BookCopy.
     */
    @NotNull StatusCode checkInBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);

    /**
     * <p>
     *  Stub for sellBook procedure for BookCopy database table.
     *</p>
     *
     * @param barcode: String barcode representing the book copy's barcode.
     * @param studentId: String studentId representing the unique string for a 919 number.
     * @return StatusCode of a BookCopy.
     */
    @NotNull StatusCode sellBook(@NotNull final String barcode, @NotNull final String studentId);
}
