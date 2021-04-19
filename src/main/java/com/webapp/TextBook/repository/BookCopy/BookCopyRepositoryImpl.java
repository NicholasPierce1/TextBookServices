package com.webapp.TextBook.repository.BookCopy;

import com.sun.istack.Nullable;
import com.webapp.TextBook.repository.data_access.Bag;
import com.webapp.TextBook.repository.data_access.BookCopy;
import com.webapp.TextBook.repository.shared_respository_helper.DataAccessConversionHelper;
import com.webapp.TextBook.repository.shared_respository_helper.QueryTableNameModifier;
import org.apache.tomcat.jni.Local;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.sharedFiles.StatusCode;

import javax.persistence.*;
import javax.transaction.Transaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.awt.print.Book;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <h1>BookCopyRepositoryImpl</h1>
 * <h2>Type: Class</h2>
 *
 * Actual implementation of methods to access the database for functional requirement data
 * pulled from the BookCopy entity.
 */
public class BookCopyRepositoryImpl implements BookCopyRepositoryCustom{

    /**
     * <p>
     * Instantiation of an EntityManagerFactory
     * Usage: Used to access the database and manage resources efficiently.
     * </p>
     */
    private final EntityManagerFactory _entityManagerFactory;

    /**
     * <p>
     * Instantiation of an empty EntityTransaction
     * Usage: Used to initiate a transaction.
     * </p>
     */
    EntityTransaction transaction = null;


    /**
     * Defines an autowired constructor (render by Spring Application Context as a Bean configuration)
     * to established injected member for singleton, repository interaction
     * note: 'new' instances shall never be created
    */
    @Autowired
    BookCopyRepositoryImpl(EntityManagerFactory factory){
        this._entityManagerFactory = factory;
    }


    /**
     * <p>
     *  Implementation of generic method to retrieve the tableName of the BookCopy table from the database.
     *</p>
     *
     * @return Name of the BookCopy table in the database.
     */
    @Override
    public @NotNull String GetTableName(){return "NWTXDT";}

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
    public @NotNull Pair<Optional<List<BookCopy>>, StatusCode> getAllCheckedOutBooks(@NotNull final String studentId, @NotNull final String termCode){
        final String TABLE_NAME = GetTableName();

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the pidm and termCode.
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"NWTXDT_PIDM\" = ? AND tableName.\"NWTXDT_TERM\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getCheckedOutBooksQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery, TABLE_NAME));

            // Adds the studentId and termcode from the method call to the query.
            getCheckedOutBooksQuery.setParameter(1, studentId);
            getCheckedOutBooksQuery.setParameter(2, termCode);

            // Saving the results from the query.
            List<Object[]> records = getCheckedOutBooksQuery.getResultList();

            System.out.println(records.get(0).length);

            // Creates new BookCopy type List.
            List<BookCopy> returnType = new ArrayList<BookCopy>();

            //Using the information stored in the query results, create new BookCopy objects.
            DataAccessConversionHelper.createDataAccessObjects(records, returnType, BookCopy::new);

            return new Pair<Optional<List<BookCopy>>, StatusCode>(Optional.of(returnType), StatusCode.OK);
        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return new Pair<Optional<List<BookCopy>>, StatusCode>(Optional.empty(), StatusCode.NoCheckOutBooks);
        }
        catch (RuntimeException ex) {
            System.out.println("db error -- getAllCheckedOutBooks()\n" + ex.getMessage());
            return new Pair<Optional<List<BookCopy>>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

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
    public @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(
            @NotNull final String strikeBarcode,
            @NotNull final String studentId,
            @NotNull String termCode){

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode.
            EntityManager em = _entityManagerFactory.createEntityManager();
            Pair<Optional<BookCopy>, StatusCode> result = findBookCopyByStrikeBarcode(strikeBarcode);

            // verifies the status code of operation is OK
            if(result.getValue1() != StatusCode.OK)
                return result; // error occurred, return faulty result

            // capture the book copy and verify integrity state (should never throw)
            final BookCopy bookCopy = result.getValue0().orElseThrow();

            // corroborate book copy is applicable to a checkout operation (not own by someone else)
            /*
            1) disposition is 'I'
            2) no current pidm (student holding book) --> implies term and date checkout are null
             */
            if(
                    bookCopy.pidm == null &&
                    bookCopy.termCode == null &&
                    bookCopy.dateCheckedOut == null &&
                    bookCopy.disposition == 'I'){

                final StatusCode statusCode = changeBookDisposition(bookCopy, studentId, termCode, 'O');
                // Returning the Book Copy record that was updated

                return new Pair<Optional<BookCopy>, StatusCode>(statusCode == StatusCode.OK ? Optional.of(bookCopy) : Optional.empty(), statusCode);
            }
            else {
                return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.BookAlreadyCheckedOut);
            }
        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.CheckedInBookCopyUndefined);
        }
        catch (RuntimeException ex) {
            System.out.println("db error -- checkoutBook()\n" + ex.getMessage());
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

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
    public @NotNull StatusCode checkInBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode){
        final String TABLE_NAME = GetTableName();

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode, studentId, termCode and disposition is 'O'.
            EntityManager em = _entityManagerFactory.createEntityManager();

            Pair<Optional<BookCopy>, StatusCode> result = findBookCopyByStrikeBarcode(strikeBarcode);

            // verifies the status code of operation is OK
            if(result.getValue1() != StatusCode.OK)
                return result.getValue1(); // error occurred, return faulty result

            // capture the book copy and verify integrity state (should never throw)
            final BookCopy bookCopy = result.getValue0().orElseThrow();

            return changeBookDisposition(bookCopy, null, null, 'I');

        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return StatusCode.BookCopyLongUndefined;
        }
        catch (RuntimeException ex) {

            System.out.println("db error -- checkInBook()\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }

    /**
     *<p>
     * This is a procedure to query the database for records from the BookCopy table that matches
     * the given barcode, studentId, and checks the BookCopy disposition. Then the given BookCopy is sold and the table is updated.
     *</p>
     *
     * @param barcode: String barcode representing the strike barcode, NW's specific barcode for books.
     * @return StatusCode to see if process was successful.
     */
    public @NotNull StatusCode sellBook(@NotNull final String barcode){
        final String TABLE_NAME = GetTableName();

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode, studentId, termCode and disposition is 'O'.
            EntityManager em = _entityManagerFactory.createEntityManager();

            Pair<Optional<BookCopy>, StatusCode> result = findBookCopyByStrikeBarcode(barcode);

            // verifies the status code of operation is OK
            if(result.getValue1() != StatusCode.OK)
                return result.getValue1(); // error occurred, return faulty result

            // capture the book copy and verify integrity state (should never throw)
            final BookCopy bookCopy = result.getValue0().orElseThrow();

            return changeBookDisposition(bookCopy, null, null, 'S');
        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return StatusCode.SellBookNotFound;
        }
        catch (RuntimeException ex) {
            System.out.println("db error -- sellBook()\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }
    /**
     *<p>
     * This is a procedure to help in other transactional methods to change the disposition of a BookCopy.
     *</p>
     *
     * @param bookCopy: BookCopy representing the scope/target book copy to modify disposition accordingly (assumed to already exist/verify)
     * @param newPidm: String representing the new student (919) to hold the targeted book (assumes exists)
     * @param newTerm: String representing the current/new term code to hold the targeted book (assumes exists)
     * @param newDisposition: Character newDisposition to represent the new status of the book copy we want to set.
     * @return StatusCode to see if process was successful.
     */
    private @NotNull StatusCode changeBookDisposition(
            @NotNull final BookCopy bookCopy,
            @Nullable final String newPidm,
            @Nullable final String newTerm,
            @NotNull final char newDisposition){

        final String TABLE_NAME = GetTableName();
        //Retrieving current date to use for later attributes.
        Date date = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdFormat.format(date);

        // Create the EntityManager
        EntityManager em = _entityManagerFactory.createEntityManager();

        try{
            switch(newDisposition) {
                case 'I': // want to check in a book copy
                {
                    //Initiate transaction, writing query to update BookCopy record with new disposition
                    transaction = em.getTransaction();
                    transaction.begin();

                    String transactionQuery = "UPDATE tableName set tableName.\"NWTXDT_DISPOSITION\" = 'I', tableName.\"NWTXDT_ACTIVITY_DATE\" = to_date(?, 'DD-MM-RRRR') " +
                            "tableName.\"NWTXDT_PREV_PIDM\" = ?, tableName.\"NWTXDT_PREV_TERM\" = ?, " +
                            "tableName.\"NWTXDT_PIDM\" = null, tableName.\"NWTXDT_TERM\" = null, tableName.\"NWTXDT_DATE_CHECKED_OUT\" = null " +
                            "tableName.\"NWTXDT_PREV_DATE_CHECKED_IN\" = to_date(?, 'DD-MM-RRRR') " +
                            "WHERE tableName.\"NWTXDT_BARCODE\" = ?";
                    Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                    // Adding arguments from method to the query
                    updateBookCopyQuery.setParameter(1, currentDate);
                    updateBookCopyQuery.setParameter(2, bookCopy.getPidm());
                    updateBookCopyQuery.setParameter(3, bookCopy.getTermCode());
                    updateBookCopyQuery.setParameter(4, currentDate);
                    updateBookCopyQuery.setParameter(5, bookCopy.getStrikeBarcode());
                    // Executing and committing the update.
                    final int amountUpdated = updateBookCopyQuery.executeUpdate();

                    if (amountUpdated != 1) // 0 or multiple books amended
                        throw new RuntimeException("Internal Error (BookCopyRepositoryImpl -- ChangeBookDisposition): 0 or many books" +
                                "were updated. Please check logs and current database installation for more info " +
                                "\n(amount of books modified): " + amountUpdated);

                    transaction.commit();
                    // Returning OK if process is successful
                    return StatusCode.OK;
                }
                case 'O': {
                    // want to checkout a book

                    //Initiate transaction, writing query to update BookCopy record with new disposition
                    transaction = em.getTransaction();
                    transaction.begin();

                    // check nullable fields aren't null
                    if (newPidm == null || newTerm == null)
                        throw new RuntimeException("Internal Error (BookCopyRepositoryImpl -- ChangeBookDisposition): " +
                                "trying to check out book where new pidm and/or term aren't set. Please revise.");


                    //Writing query to update BookCopy record with new disposition
                    String transactionQuery = "UPDATE tableName set tableName.\"NWTXDT_DISPOSITION\" = 'O'," +
                            " tableName.\"NWTXDT_ACTIVITY_DATE\" = to_date(?, 'DD-MM-RRRR')," +
                            " tableName.\"NWTXDT_PIDM\" = ?, tableName.\"NWTXDT_TERM\" = ?," +
                            " tableName.\"NWTXDT_DATE_CHECKED_OUT\" = to_date(?, 'DD-MM-RRRR')" +
                            " WHERE tableName.\"NWTXDT_BARCODE\" = ?";
                    Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                    // Adding arguments from method to the query
                    updateBookCopyQuery.setParameter(1, currentDate);
                    updateBookCopyQuery.setParameter(2, newPidm);
                    updateBookCopyQuery.setParameter(3, newTerm);
                    updateBookCopyQuery.setParameter(4, currentDate);
                    updateBookCopyQuery.setParameter(5, bookCopy.getStrikeBarcode());

                    // Executing and committing the update.
                    final int amountUpdated = updateBookCopyQuery.executeUpdate();

                    if (amountUpdated != 1) // 0 or multiple books amended
                        throw new RuntimeException("Internal Error (BookCopyRepositoryImpl -- ChangeBookDisposition): 0 or many books" +
                                "were updated. Please check logs and current database installation for more info " +
                                "\n(amount of books modified): " + amountUpdated);

                    // update state of bookcopy to match the definition that was modified to
                    bookCopy.setDisposition('O');
                    bookCopy.setActivityDate(date);
                    bookCopy.setPidm(newPidm);
                    bookCopy.setTermCode(newTerm);
                    bookCopy.setDateCheckedOut(date);

                    transaction.commit();
                    // Returning OK if process was successful
                    return StatusCode.OK;
                }
                case 'S': // want to sell a book
                {
                    //Initiate transaction, writing query to update BookCopy record with new disposition
                    transaction = em.getTransaction();
                    transaction.begin();

                    // Query to update BookCopy table and changing the disposition to S for sold
                    // ASSUMPTION: current book is being sold to the person who owns it (checked out to)
                    String transactionQuery = "UPDATE tableName set tableName.\"NWTXDT_DISPOSITION\" = 'S' " +
                            "WHERE tableName.\"NWTXDT_BARCODE\" = ?";

                    // Adding the BookCopy tableName to the query
                    Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                    // Adding arguments from method to the query
                    updateBookCopyQuery.setParameter(1, bookCopy.getStrikeBarcode());

                    // Executing and committing the update.
                    final int amountUpdated = updateBookCopyQuery.executeUpdate();

                    if (amountUpdated != 1) // 0 or multiple books amended
                        throw new RuntimeException("Internal Error (BookCopyRepositoryImpl -- ChangeBookDisposition): 0 or many books" +
                                "were updated. Please check logs and current database installation for more info " +
                                "\n(amount of books modified): " + amountUpdated);

                    transaction.commit();
                }
                default:
                    return StatusCode.BookCopyUndefined;
            }
        }
        catch(RuntimeException ex){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
            System.out.println("db error -- changeBookDisposition()\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }

    /**
     *<p>
     * This is a helper procedure to query the database for records from the BookCopy table that matches
     * the given strikeBarcode.
     *</p>
     *
     * @param strikeBarcode: String strikeBarcode representing the strike barcode, NW's specific barcode for books.
     * @return Pair of a Optional BookCopy and it's StatusCode from the resulting query.
     */
    private @NotNull Pair<Optional<BookCopy>, StatusCode> findBookCopyByStrikeBarcode(@NotNull final String strikeBarcode) {
        final String TABLE_NAME = GetTableName();

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode.
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"NWTXDT_BARCODE\" = ?";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getCheckInBooksQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery, TABLE_NAME));

            //Setting the strikeBarcode parameter in the query.
            getCheckInBooksQuery.setParameter(1, strikeBarcode);

            // Saving the result from the query.
            Object[] record = (Object[]) getCheckInBooksQuery.getSingleResult();
            BookCopy returnType = DataAccessConversionHelper.createDataAccessObject(record, BookCopy::new);

            return new Pair<Optional<BookCopy>, StatusCode>(Optional.of(returnType), StatusCode.OK);
        }
        catch (NoResultException ex) {
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.BookCopyUndefined);
        }
        catch (Exception ex) {
            System.out.println("db error -- findBookCopyByStrikeBarcode()\n" + ex.getMessage());
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

}
