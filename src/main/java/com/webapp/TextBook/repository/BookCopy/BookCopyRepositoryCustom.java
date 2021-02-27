package com.webapp.TextBook.repository.BookCopy;

import com.webapp.TextBook.repository.Repository;
import com.webapp.TextBook.repository.StatusCode;
import com.webapp.TextBook.repository.data_access.BookCopy;
import org.javatuples.Pair;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BookCopyRepositoryCustom extends Repository {
    @NotNull Pair<Optional<List<BookCopy>>, StatusCode> getAllCheckedOutBooks(@NotNull final String studentId, @NotNull final String termCode);
    @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);
    @NotNull StatusCode checkInBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode);
    @NotNull StatusCode sellBook(@NotNull final String barcode, @NotNull final String studentId);
}