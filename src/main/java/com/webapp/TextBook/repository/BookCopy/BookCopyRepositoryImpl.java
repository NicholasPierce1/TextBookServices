package com.webapp.TextBook.repository.BookCopy;

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
            System.out.println("db error --\n" + ex.getMessage());
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
    public @NotNull Pair<Optional<BookCopy>, StatusCode> checkOutBook(@NotNull final String strikeBarcode, @NotNull final String studentId, @NotNull String termCode){
        final String TABLE_NAME = GetTableName();
        Date date = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdFormat.format(date);

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode.
            EntityManager em = _entityManagerFactory.createEntityManager();
            Pair<Optional<BookCopy>, StatusCode> result = findBookCopyByStrikeBarcode(strikeBarcode);


            if(result.getValue0().get().pidm == null && result.getValue0().get().termCode == null && result.getValue0().get().dateCheckedOut == null && result.getValue0().get().disposition == 'I'){
                changeBookDisposition(result.getValue0().get().pidm, result.getValue0().get().termCode, result.getValue0().get().disposition, 'O');
                // Initiate transaction
                transaction = em.getTransaction();
                transaction.begin();
                // Query to update BookCopy table with new information, hence "checking out" the book copy.
                String transactionQuery = "UPDATE tablName set tableName.\"NWTXDT_PIDM\" = ?, tableName.\"NWTXDT_TERM\" = ?, tableName.\"NWTXDT_DATE_CHECKED_OUT\" = ? " +
                        "WHERE tableName.\"NWTXDT_BARCODE\" = ?";

                // Adding the BookCopy tableName to the query
                Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                // Adding arguments from method to the query
                updateBookCopyQuery.setParameter(1, studentId);
                updateBookCopyQuery.setParameter(2, termCode);
                updateBookCopyQuery.setParameter(3, currentDate);
                updateBookCopyQuery.setParameter(4, strikeBarcode);


                // Executing and committing the update.
                updateBookCopyQuery.executeUpdate();
                transaction.commit();

                // Returning the Book Copy record that was updated
                return new Pair<Optional<BookCopy>, StatusCode>(result.getValue0(), StatusCode.OK);
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
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
                return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.FailureToUpdateBookCopy);
            }
            System.out.println("db error --\n" + ex.getMessage());
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
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"NWTXDT_BARCODE\" = ? AND tableName.\"NWTXDT_PIDM\" = ? AND tableName.\"NWTXDT_TERM\" = ? AND tableName.\"NWTXDT_DISPOSITION\" = 'O'";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getCheckInBooksQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery, TABLE_NAME));

            // Adds the strikeBarcode, studentId, and termCode from the method call to the query.
            getCheckInBooksQuery.setParameter(1, strikeBarcode);
            getCheckInBooksQuery.setParameter(2, studentId);
            getCheckInBooksQuery.setParameter(3, termCode);

            // Saving the results from the query.
            List<Object[]> records = getCheckInBooksQuery.getResultList();

            // Creates new BookCopy type List.
            List<BookCopy> returnType = new ArrayList<BookCopy>();

            // Using the information stored in the query results, create new BookCopy objects.
            DataAccessConversionHelper.createDataAccessObjects(records, returnType, BookCopy::new);

            // Searching through list of Book Copies to find those that are available.
            for(int i = 0; i < returnType.size(); i++){
                changeBookDisposition(returnType.get(i).pidm,returnType.get(i).termCode, returnType.get(i).disposition, 'I');
                // Initiate transaction
                transaction = em.getTransaction();
                transaction.begin();

                // Query to update BookCopy table with new information, hence "checking in" the book copy.
                String transactionQuery = "UPDATE tablName set tableName.\"NWTXDT_PIDM\" = NULL, tableName.\"NWTXDT_TERM\" = NULL, tableName.\"NWTXDT_DATE_CHECKED_OUT\" = NULL" +
                        "WHERE tableName.\"NWTXDT_PIDM\" = ? AND tableName.\"NWTXDT_TERM\" = ?";

                // Adding the BookCopy tableName to the query
                Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                // Adding arguments from method to the query
                updateBookCopyQuery.setParameter(1, studentId);
                updateBookCopyQuery.setParameter(2, termCode);

                // Executing and committing the update.
                updateBookCopyQuery.executeUpdate();
                transaction.commit();
                // Returning the Book Copy record status that was updated
                return StatusCode.OK;
            }
            return StatusCode.BookCopyLongUndefined;
        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return StatusCode.BookCopyLongUndefined;
        }
        catch (RuntimeException ex) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
                return StatusCode.FailureToUpdateBookCopy;
            }
            System.out.println("db error --\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }

    public @NotNull StatusCode sellBook(@NotNull final String barcode, @NotNull final String studentId){
        final String TABLE_NAME = GetTableName();

        try {
            // Create the EntityManager and writing the query to access BookCopy records that matches the strikeBarcode and pidm and disposition is not S or I.
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"NWTXDT_BARCODE\" = ? AND tableName.\"NWTXDT_PIDM\" = ? AND (tableName.\"NWTXDT_DISPOSITION\" != 'S' OR tableName.\"NWTXDT_DISPOSITION\" != 'I')";

            // Calling the query for the specific table specified in TABLE_NAME.
            Query getBooksToSell = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery,TABLE_NAME));

            // Adds the studentId from the method call to the query.
            getBooksToSell.setParameter(1, barcode);
            getBooksToSell.setParameter(2, studentId);

            List<Object[]> records = getBooksToSell.getResultList();

            // Creates new BookCopy type List.
            List<BookCopy> returnType = new ArrayList<BookCopy>();

            // Using the information stored in the query results, create new BookCopy objects.
            DataAccessConversionHelper.createDataAccessObjects(records, returnType, BookCopy::new);


            // Initiate transaction
            transaction = em.getTransaction();
            transaction.begin();

            // Query to update BookCopy table with new information, hence "checking in" the book copy.
            String transactionQuery = "UPDATE tablName set tableName.\"NWTXDT_DISPOSITION\" = 'S' " +
                    "WHERE tableName.\"NWTXDT_BARCODE\" = ?";

            // Adding the BookCopy tableName to the query
            Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

            // Adding arguments from method to the query
            updateBookCopyQuery.setParameter(1, barcode);

            // Executing and committing the update.
            updateBookCopyQuery.executeUpdate();
            transaction.commit();
            // Returning the Book Copy record status that was updated
            return StatusCode.OK;
        }
        catch(NoResultException noResultException) {
            System.out.println("Internal Error: BookCopyRepositoryImpl--getAllCheckedOutBooks--\n" + noResultException.getMessage());
            return StatusCode.SellBookNotFound;
        }
        catch (RuntimeException ex) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
                return StatusCode.FailureToUpdateBookCopy;
            }
            System.out.println("db error --\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }

    private @NotNull StatusCode changeBookDisposition(@NotNull final String pidm, @NotNull final String termCode, @NotNull final char disposition, @NotNull final char newDisposition){
        final String TABLE_NAME = GetTableName();
        Date date = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdFormat.format(date);

        EntityManager em = _entityManagerFactory.createEntityManager();
        try{
            transaction = em.getTransaction();
            transaction.begin();
            if(newDisposition == 'O') {
                String transactionQuery = "UPDATE tablName set tableName.\"NWTXDT_DISPOSITION\" = 'O', tableName.\"NWTXDT_ACTIVITY_DATE\" = ? " +
                        "WHERE tableName.\"NWTXDT_PIDM\" = ? AND tableName.\"NWTXDT_TERM\" = ?, AND tableName.\"NWTXDT_DISPOSITION\" = 'I'";
                Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                // Adding arguments from method to the query
                updateBookCopyQuery.setParameter(1, currentDate);
                updateBookCopyQuery.setParameter(2, pidm);
                updateBookCopyQuery.setParameter(3, termCode);

                // Executing and committing the update.
                updateBookCopyQuery.executeUpdate();
                transaction.commit();
                // Returning the Book Copy record that was updated
                return StatusCode.OK;
            }
            else if(newDisposition == 'I'){
                String transactionQuery = "UPDATE tablName set tableName.\"NWTXDT_DISPOSITION\" = 'I', tableName.\"NWTXDT_PREV_PIDM\" = ?, tableName.\"NWTXDT_PREV_TERM\" = ?, tableName.\"NWTXDT_PREV_DATE_CHECKED_IN\" = ?, tableName.\"NWTXDT_ACTIVITY_DATE\" = ? " +
                        "WHERE tableName.\"NWTXDT_PIDM\" = ? AND tableName.\"NWTXDT_TERM\" = ? AND tableName.\"NWTXDT_DISPOSITION\" = 'O'";
                Query updateBookCopyQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(transactionQuery, TABLE_NAME));

                // Adding arguments from method to the query
                updateBookCopyQuery.setParameter(1, pidm);
                updateBookCopyQuery.setParameter(2, termCode);

                // Date stuff hard
                updateBookCopyQuery.setParameter(3, currentDate);
                updateBookCopyQuery.setParameter(4, currentDate);

                updateBookCopyQuery.setParameter(5, pidm);
                updateBookCopyQuery.setParameter(6, termCode);

                // Executing and committing the update.
                updateBookCopyQuery.executeUpdate();
                transaction.commit();
                // Returning the Book Copy record that was updated
                return StatusCode.OK;
            }
            else{
                return StatusCode.BookCopyUndefined;
            }
        }
        catch(RuntimeException ex){
            transaction.rollback();
            System.out.println("db error --\n" + ex.getMessage());
            return StatusCode.DatabaseError;
        }
    }

    private @NotNull Pair<Optional<BookCopy>, StatusCode> findBookCopyByStrikeBarcode(@NotNull final String strikeBarcode) {
        final String TABLE_NAME = GetTableName();

        try {
            EntityManager em = _entityManagerFactory.createEntityManager();
            String originalQuery = "SELECT tableName.* FROM tableName WHERE tableName.\"NWTXDT_BARCODE\" = ?";
            Query getCheckInBooksQuery = em.createNativeQuery(QueryTableNameModifier.insertTableNameIntoQuery(originalQuery, TABLE_NAME));
            getCheckInBooksQuery.setParameter(1, strikeBarcode);
            // Saving the result from the query.
            Object[] record = (Object[]) getCheckInBooksQuery.getSingleResult();
            BookCopy returnType = DataAccessConversionHelper.createDataAccessObject(record, BookCopy::new);
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.of(returnType), StatusCode.OK);
        } catch (NoResultException ex) {
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.BookCopyUndefined);
        } catch (Exception ex) {
            System.out.println("db error --\n" + ex.getMessage());
            return new Pair<Optional<BookCopy>, StatusCode>(Optional.empty(), StatusCode.DatabaseError);
        }
    }

}
