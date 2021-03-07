package com.webapp.TextBook.repository.BookCopy;

import com.webapp.TextBook.repository.data_access.BookCopy;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


public class BookCopyRepositoryImpl implements BookCopyRepositoryCustom{

    private final EntityManagerFactory _entityManagerFactory;


    // defines an autowired constructor (render by Spring Application Context as a Bean configuration)
    // to established injected member for singleton, repository interaction
    // note: 'new' instances shall never be created
    @Autowired
    BookCopyRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }


    @Override
    public @NotNull String GetTableName(){return "NWTXDT";}

    //Methods
    public @NotNull final Pair<Optional<List<BookCopy>>, StatusCode> getAllCheckedOutBooks(@NotNull final String studentId, @NotNull final String termCode){
        return null;
    }

    public @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode){
        return null;
    }

    public @NotNull StatusCode checkInBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode){
        return null;
    }

    public @NotNull StatusCode sellBook(@NotNull final String barcode, @NotNull final String studentId){
        return null;
    }

    private @NotNull StatusCode changeBookDisposition(@NotNull final String pidm, @NotNull final String termCode, @NotNull final char disposition, @NotNull final char newDisposition){
        return null;
    }

    private @NotNull Pair<Optional<BookCopy>, StatusCode> findBookCopyByStrikeBarcode(@NotNull final String strikeBarcode){
        return null;
    }

}
