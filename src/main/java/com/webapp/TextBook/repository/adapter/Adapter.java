package com.webapp.TextBook.repository.adapter;

import com.webapp.TextBook.repository.Bag.BagRepository;
import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.Person.PersonRepository;
import com.webapp.TextBook.repository.StatusCode;
import com.webapp.TextBook.repository.Term.TermRepository;
import com.webapp.TextBook.repository.User.UserRepository;
import com.webapp.TextBook.repository.data_access.*;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public final class Adapter {

    public static Adapter adapter = new Adapter();

    private Adapter(){}

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private BagRepository bagRepository;

    public @NotNull Quintet<Optional<List<BookCopy>>, Optional<Student>, Optional<Bag>, Optional<Term>, StatusCode> getAllCheckedOutBooks(@NotNull final String termCode, @NotNull final String studentID){
        return null;
    }

    public @NotNull Quartet<Optional<BookCopy>, Optional<Student>, Optional<Term>, StatusCode> checkOutBookForStudent(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull final String termCode){
        return null;
    }

    public @NotNull StatusCode checkInBookForStudent(@NotNull final String studentId, @NotNull final String barcode){
        return null;
    }

    public @NotNull StatusCode sellBookForStudent(@NotNull final String studentId, @NotNull final String barcode){
        return null;
    }

    public @NotNull Pair<Optional<User>, StatusCode> userLogin(@NotNull final String username, @NotNull final String password){
        return null;
    }

    public @NotNull Pair<Optional<Student>, StatusCode> getStudentWithStudentCandidateKey(@NotNull final String studentCandidateKey){
        return null;
    }

    private @NotNull Pair<Optional<Student>,  StatusCode> getStudentById(@NotNull final String studentId){
        return null;
    }

    private @NotNull Pair<Optional<Term>,  StatusCode> getTermByTermCode(@NotNull final String termCode){
        return null;
    }
}
