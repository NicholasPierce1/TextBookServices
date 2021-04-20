package com.webapp.TextBook.repository.adapter;

import com.webapp.TextBook.repository.Bag.BagRepository;
import com.webapp.TextBook.repository.BookCopy.BookCopyRepository;
import com.webapp.TextBook.repository.Person.PersonRepository;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import com.webapp.TextBook.sharedFiles.StatusCode;
import com.webapp.TextBook.repository.Term.TermRepository;
import com.webapp.TextBook.repository.User.UserRepository;
import com.webapp.TextBook.repository.data_access.*;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * <h1>Adapter</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods required by the event listener
 */

@Service
public final class Adapter {

    private final BookCopyRepository _bookCopyRepository;

    private final PersonRepository _personRepository;

    private final UserRepository _userRepository;

    private final TermRepository _termRepository;

    public final BagRepository _bagRepository;

    @Autowired
    Adapter(BookCopyRepository bookCopyRepository,
            PersonRepository personRepository,
            UserRepository userRepository,
            TermRepository termRepository,
            BagRepository bagRepository){
        this._bookCopyRepository = bookCopyRepository;
        this._personRepository = personRepository;
        this._userRepository = userRepository;
        this._termRepository = termRepository;
        this._bagRepository = bagRepository;
    }

    /**
     * <p>
     *     From a given student id (919 number) and a term code, attempts to acquire all checked out books
     *     for that student. Furthermore, will partially return acquired database state of any partials
     *     note: partials does not mean a partial user (User) but rather have a subset of the state within the
     *     return type will be set and given.
     * </p>
     * @param user (type User) represents the session user that's currently interacting with the system (should already be logged in)
     * @param termCode (type String) represents a term code denoting a specific term for a given year (ie: 202010 -- Fall 2020)
     * @param studentID (type String) represents a student id.
     *                 Note: student id is not the database's informal primary key (PIDM), but rather
     *                  the 919 number (candidate key)
     * @return Quintet( type parameters:
     *  Optional (type parameter:
     *      List (type parameter: BookCopy)),
     *  Optional (type parameter: Student),
     *  Optional (type parameter: Bag),
     *  Optional (type parameter: Term),
     *  StatusCode ) representing all the checked out books for a given student id and term.
     *  Will partially return acquired database state of any partials
     *  note: partials does not mean a partial user (User) but rather have a subset of the state within the
     *  return type will be set and given.
     */
    public @NotNull Quintet<Optional<List<BookCopy>>, Optional<Student>, Optional<Bag>, Optional<Term>, StatusCode>
    getAllCheckedOutBooks(
            @NotNull final User user,
            @NotNull final String termCode,
            @NotNull final String studentID) {

        // creates default error response for streamline error response interpretation/manipulation
        Quintet<
                Optional<List<BookCopy>>,
                Optional<Student>,
                Optional<Bag>,
                Optional<Term>,
                StatusCode> returnValue =
                new Quintet<
                Optional<List<BookCopy>>,
                Optional<Student>,
                Optional<Bag>,
                Optional<Term>,
                StatusCode>(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                StatusCode.DatabaseError
        );

        // verifies session user, whom instigated this request, meets desired permissions to
        // perform this operation
        final Optional<StatusCode> USER_PERMISSION_AUTHORIZATION_RESULT =
                Adapter.isUserPrivilegeValid(
                        user.userRole,
                        UserRole.StudentEmployee
                );
        if(USER_PERMISSION_AUTHORIZATION_RESULT.isPresent())
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt4(USER_PERMISSION_AUTHORIZATION_RESULT.get());

        // invoke student repository to acquire a student given a student id
        final Pair<Optional<Student>, StatusCode> optionalStudentCodePair =
                this._personRepository.getStudentWithCandidateKey(studentID);

        // evaluates if status code is ok (else return in guard block)
        if(optionalStudentCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt4(optionalStudentCodePair.getValue1());

        // sets the acquired student within the return value (will always exist at this point)
        returnValue = returnValue.setAt1(optionalStudentCodePair.getValue0());

        // invokes term repository to get the term via the term code (should be in helper later)
        final Pair<Optional<Term>, StatusCode> optionalTermCodePair = this._termRepository.getTermWithTermCode(termCode);

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt4(optionalTermCodePair.getValue1());

        // sets the acquired term within the return value (will always exist at this point)
        returnValue = returnValue.setAt3(optionalTermCodePair.getValue0());

        // invokes bag repository to acquire a bag given a student id/ candidate key (919)
        final Pair<Optional<Bag>, StatusCode> optionalBagCodePair = this._bagRepository.getStudentBagWithStudentId(
                returnValue.getValue1().orElseThrow().getPidm() // will never throw -- student created above
        );

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt4(optionalBagCodePair.getValue1());

        // sets the acquired bag within the return value (will always exist at this point)
        returnValue = returnValue.setAt2(optionalBagCodePair.getValue0());

        // invokes book copy repository to acquire all checked out books for a given student id and term
        final Pair<Optional<List<BookCopy>>, StatusCode> optionalBookCopyListCodePair =
                this._bookCopyRepository.getAllCheckedOutBooks(
                        returnValue.getValue1().orElseThrow().getPidm(),
                        returnValue.getValue3().orElseThrow().getTermCode()
                ); // will never throw (always exist in code execution above)

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt4(optionalBagCodePair.getValue1());

        // sets the acquired bag within the return value (will always exist at this point)
        returnValue = returnValue.setAt0(optionalBookCopyListCodePair.getValue0());

        // resets status code to OK and return
        return returnValue.setAt4(StatusCode.OK);
    }

    /**
     * <p>
     *     From a given strike barcode, student id (919 number), and a term code, attempts to check out specified book
     *     for that student.
     * </p>
     * @param user (type User) represents the session user that's currently interacting with the system (should already be logged in)
     * @param strikeBarcode (type String) representing the strike barcode, NW's specific barcode for books.
     * @param studentId (type String) represents a student id.
     *      Note: student id is not the database's informal primary key (PIDM), but rather
     *      the 919 number (candidate key)
     * @param termCode (type String) represents a term code denoting a specific term for a given year (ie: 202010 -- Fall 2020)
     *
     * @return Quartet(
     *  Optional (type parameter: BookCopy)
     *  Optional (type parameter: Bag),
     *  Optional (type parameter: Term),
     *  StatusCode ) representing the specific checked out book for a given strike barcode, student id, and term.
     */
    public @NotNull Quartet<
            Optional<BookCopy>,
            Optional<Student>,
            Optional<Term>,
            StatusCode> checkOutBookForStudent(
                    @NotNull final User user,
                    @NotNull final String strikeBarcode,
                    @NotNull final String studentId,
                    @NotNull final String termCode) {

        // creates default error response for streamline error response interpretation/manipulation
        Quartet<
                Optional<BookCopy>,
                Optional<Student>,
                Optional<Term>,
                StatusCode> returnValue =
                new Quartet<
                        Optional<BookCopy>,
                        Optional<Student>,
                        Optional<Term>,
                        StatusCode>(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        StatusCode.DatabaseError
                );

        // verifies session user, whom instigated this request, meets desired permissions to
        // perform this operation
        final Optional<StatusCode> USER_PERMISSION_AUTHORIZATION_RESULT =
                Adapter.isUserPrivilegeValid(
                        user.userRole,
                        UserRole.StudentEmployee
                );

        if(USER_PERMISSION_AUTHORIZATION_RESULT.isPresent())
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt3(USER_PERMISSION_AUTHORIZATION_RESULT.get());

        // invoke student repository to acquire a student given a student id
        final Pair<Optional<Student>, StatusCode> optionalStudentCodePair =
                this._personRepository.getStudentWithCandidateKey(studentId);

        // evaluates if status code is ok (else return in guard block)
        if(optionalStudentCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt3(optionalStudentCodePair.getValue1());

        // sets the acquired student within the return value (will always exist at this point)
        returnValue = returnValue.setAt1(optionalStudentCodePair.getValue0());

        // invokes term repository to get the term via the term code (should be in helper later)
        final Pair<Optional<Term>, StatusCode> optionalTermCodePair =
                this._termRepository.getTermWithTermCode(termCode);

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt3(optionalTermCodePair.getValue1());

        // sets the acquired term within the return value (will always exist at this point)
        returnValue = returnValue.setAt2(optionalTermCodePair.getValue0());

        // invokes book copy repository to acquire all checked out books for a given student id and term
        final Pair<Optional<BookCopy>, StatusCode> optionalBookCopyCodePair =
                this._bookCopyRepository.checkOutBook(
                        strikeBarcode,
                        returnValue.getValue1().orElseThrow().getPidm(),
                        returnValue.getValue2().orElseThrow().getTermCode()
                ); // will never throw (always exist in code execution above)

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // sets error status code in return value (return new tuple of type match)
            return returnValue.setAt3(optionalTermCodePair.getValue1());

        // sets the acquired book within the return value (will always exist at this point)
        returnValue = returnValue.setAt0(optionalBookCopyCodePair.getValue0());

        // resets status code to OK and return
        return returnValue.setAt3(StatusCode.OK);
    }

    /**
     * <p>
     *     From a given student id (919 number), a term code, and strike barcode, attempts to check in specified book
     *     for that student.
     * </p>
     * @param user (type User) represents the session user that's currently interacting with the system (should already be logged in)
     * @param studentId (type String) represents a student id.
     *      Note: student id is not the database's informal primary key (PIDM), but rather
     *      the 919 number (candidate key)
     * @param barcode  (type String) representing the strike barcode, NW's specific barcode for books.
     * @param termCode (type String) represents a term code denoting a specific term for a given year (ie: 202010 -- Fall 2020)
     *
     * @return StatusCode representing that the specific book for a given student id, term, and barcode has been checked in.
     */
    public @NotNull StatusCode checkInBookForStudent(
            @NotNull final User user,
            @NotNull final String studentId,
            @NotNull final String termCode,
            @NotNull final String barcode){

        // verifies session user, whom instigated this request, meets desired permissions to
        // perform this operation
        final Optional<StatusCode> USER_PERMISSION_AUTHORIZATION_RESULT =
                Adapter.isUserPrivilegeValid(
                        user.userRole,
                        UserRole.StudentEmployee
                );

        // verify that permissions supplied are authorized -- return otherwise
        if(USER_PERMISSION_AUTHORIZATION_RESULT.isPresent())
            return USER_PERMISSION_AUTHORIZATION_RESULT.get();

        // invoke student repository to acquire a student given a student id
        final Pair<Optional<Student>, StatusCode> optionalStudentCodePair =
                this._personRepository.getStudentWithCandidateKey(studentId);

        // evaluates if status code is ok (else return in guard block)
        if(optionalStudentCodePair.getValue1() != StatusCode.OK)
            // returns error status code
            return optionalStudentCodePair.getValue1();

        // invokes term repository to get the term via the term code (should be in helper later)
        final Pair<Optional<Term>, StatusCode> optionalTermCodePair =
                this._termRepository.getTermWithTermCode(termCode);

        // evaluates if status code is ok (else return in guard block)
        if(optionalTermCodePair.getValue1() != StatusCode.OK)
            // returns error status code
            return optionalTermCodePair.getValue1();

        // invoke book copy repository to check in a book for a given student within a term via a strike barcode
        return this._bookCopyRepository.checkInBook(barcode, studentId, termCode);

    }

    /**
     * <p>
     *     From a given student id (919 number) and strike barcode, attempts to sell specified book
     *     for that student.
     * </p>
     * @param user (type User) represents the session user that's currently interacting with the system (should already be logged in)
     * @param studentId (type String) represents a student id.
     *      Note: student id is not the database's informal primary key (PIDM), but rather
     *      the 919 number (candidate key)
     * @param barcode  (type String) representing the strike barcode, NW's specific barcode for books.
     *
     * @return StatusCode representing that the specific book for a given student id, term, and barcode has been sold.
     */
    public @NotNull StatusCode sellBookForStudent(
            @NotNull final User user,
            @NotNull final String studentId,
            @NotNull final String barcode){

        // verifies session user, whom instigated this request, meets desired permissions to
        // perform this operation
        final Optional<StatusCode> USER_PERMISSION_AUTHORIZATION_RESULT =
                Adapter.isUserPrivilegeValid(
                        user.userRole,
                        UserRole.StudentEmployee
                );

        // verify that permissions supplied are authorized -- return otherwise
        if(USER_PERMISSION_AUTHORIZATION_RESULT.isPresent())
            return USER_PERMISSION_AUTHORIZATION_RESULT.get();

        // invoke student repository to acquire a student given a student id
        final Pair<Optional<Student>, StatusCode> optionalStudentCodePair =
                this._personRepository.getStudentWithCandidateKey(studentId);

        // evaluates if status code is ok (else return in guard block)
        if(optionalStudentCodePair.getValue1() != StatusCode.OK)
            // returns error status code
            return optionalStudentCodePair.getValue1();

        return this._bookCopyRepository.sellBook(barcode);
    }

    /**
     * <p>
     *     From a given username (PIDM) and password (S_Number), attempts to create specified user
     * </p>
     *
     * @param username: String username representing their pidm.
     * @param password: String password representing their S_Number.
     *
     * @return StatusCode representing that the specific user for a given  username and password has been added.
     */
    public @NotNull Pair<Optional<User>, StatusCode> userLogin(
            @NotNull final String username,
            @NotNull final String password){
        final Pair<
                Optional<User>,
                StatusCode> returnValue =
                new Pair<
                        Optional<User>,
                        StatusCode>(
                        Optional.empty(),
                        StatusCode.DatabaseError
                );

        //
        final Pair<Optional<Object[]>, StatusCode> optionalSpridenCodePair =
                this._personRepository.getPartialUserWithCandidateKey(username);

        // evaluates if status code is ok (else return in guard block)
        if(optionalSpridenCodePair.getValue1() != StatusCode.OK)
            // returns error status code
            return returnValue.setAt1(optionalSpridenCodePair.getValue1());

        final Pair<Optional<Object[]>, StatusCode> optionalUserCodePair =
                this._userRepository.getPartialUserWithUsernameAndPassword(
                        String.valueOf(((BigDecimal)optionalSpridenCodePair.getValue0().orElseThrow()[0])).toString(),
                        password);

        // evaluates if status code is ok (else return in guard block)
        if(optionalUserCodePair.getValue1() != StatusCode.OK)
            // returns error status code
            return returnValue.setAt1(optionalUserCodePair.getValue1());

        User loginUser = DataAccessConversionHelper.createDataAccessObject(
                User.conglomerateDboArrays(
                        optionalSpridenCodePair.getValue0().orElseThrow(),
                        optionalUserCodePair.getValue0().orElseThrow()),
                User::new);

        return returnValue.setAt0(Optional.of(loginUser)).setAt1(StatusCode.OK);
    }

    /**
     * <p>
     *     verifies that user access role is apt for invoked operation
     *     note: this should be the user from shared session state ONLY
     * </p>
     *
     * @param userRole: UserRole userRole representing the password of the logged in user.
     * @param permissionLevelRequired: UserRole permissionLevelRequired representing the required level of access.
     *
     * @return Optional StatusCode representing that the specific user has privilege or
     * the user doesn't have required level of access.
     */
    private static @NotNull Optional<StatusCode> isUserPrivilegeValid(
            @NotNull final UserRole userRole,
            @NotNull final UserRole permissionLevelRequired){

        // if permission level required needed is student employee
        // OR if roles are same then valid credential authorization
        return permissionLevelRequired == UserRole.StudentEmployee ||
                userRole == permissionLevelRequired ? Optional.empty() : Optional.of(StatusCode.UserPermissionLevelInsufficient);
    }

}
