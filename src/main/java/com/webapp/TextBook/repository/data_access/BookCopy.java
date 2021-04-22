package com.webapp.TextBook.repository.data_access;

import com.sun.istack.Nullable;
import com.webapp.TextBook.repository.DataAccessConversion;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * <h1>BookCopy</h1>
 * <h2>Type: Class</h2>
 *
 * <p>Structure to hold information relating to a BookCopy and it's state.
 * These values are determined and fully defined from the business context and are explicated within
 * the FRD and traceability document.</p>
 *
 */

@Entity
public class BookCopy implements DataAccessConversion {

    /**
     * <p>
     * Generates a unique ID for BookCopy.
     * Usage: Hold primary key value for a unique BookCopy instance.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    /**
     * <p>
     * String variable to hold the book code representing the department and course number.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String bookCode;

    private static final String NOMINAL_BOOK_CODE = "bookCode";
    private static final String NOMINAL_EDITION_YEAR = "editionYear";
    private static final String NOMINAL_SEQ_NR = "seqNr";
    private static final String NOMINAL_STRIKE_BARCODE = "strikeBarcode";
    private static final String NOMINAL_PIDM = "pidm";
    private static final String NOMINAL_TERM_CODE = "termCode";
    private static final String NOMINAL_DATE_CHECKED_OUT = "dateCheckedOut";
    private static final String NOMINAL_DISPOSITION = "disposition";
    private static final String NOMINAL_BOOK_SALE_PRICE = "bookSalePrice";
    private static final String NOMINAL_PREV_PIDM = "prevPidm";
    private static final String NOMINAL_PREV_TERM_CODE = "prevTermCode";
    private static final String NOMINAL_PREV_DATE_CHECKED_IN = "prevDateCheckedIn";
    private static final String NOMINAL_ACTIVITY_DATE = "activityDate";
    private static final String NOMINAL_BILL_FLAG = "billFlag";
    /**
     * <p>
     * String variable to hold the edition year.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String editionYear;

    /**
     * <p>
     * Integer variable to hold the sequence number of the copy.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public int seqNr;

    /**
     * <p>
     * String variable to hold the strike barcode, NW's specific barcode.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String strikeBarcode;

    /**
     * <p>
     * String variable to hold the unique string code connected to a student's 919 number.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String pidm;

    /**
     * <p>
     * String variable to hold the exact year and trimester.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String termCode;

    /**
     * <p>
     * Date type variable to hold the most current date the copy was checked out.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public Date dateCheckedOut;

    /**
     * <p>
     * Character variable to represent the status of the book copy.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public char disposition;

    /**
     * <p>
     * Double type variable to hold the price of the copy.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public Double bookSalePrice;

    /**
     * <p>
     * Double type variable to hold the previous pidm of the user that owned the book copy before.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public double prevPidm;

    /**
     * <p>
     * String variable to hold the last term that the book copy was used.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String prevTermCode;

    /**
     * <p>
     * Date type variable to hold the last date the book copy was checked in.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public Date prevDateCheckedIn;

    /**
     * <p>
     * Date type variable to hold the last date that the state of the book copy was ammended.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public Date activityDate;

    /**
     * <p>
     * Character variable to signify a bill flag.
     * Usage: Attribute in creation of BookCopy for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public char billFlag;

    /**
     * <p>
     * Creates a BookCopy -- Empty constructor.
     * </p>
     */
    public BookCopy() {
        this._ID = UUID.randomUUID();
    }

    /**
     * <p>
     * Creates a BookCopy -- Full constructor with all variables.
     * </p>
     *
     * @param bookCode:          String bookCode variable
     * @param editionYear:       int editionYear variable
     * @param seqNr:             int seqNr variable
     * @param strikeBarcode:     String strikeBarcode variable
     * @param pidm:              String pidm variable
     * @param termCode:          String termCode variable
     * @param dateCheckedOut:    Date dateCheckedOut variable
     * @param disposition:       char disposition variable
     * @param bookSalePrice:     double bookSalePrice variable
     * @param prevPidm:          double prevPidm variable
     * @param prevTermCode:      String prevTermCode variable
     * @param prevDateCheckedIn: Date prevDateCheckedIn variable
     * @param activityDate:      Date activityDate variable
     * @param billFlag:          char billFlag variable
     */
    public BookCopy(String bookCode, String editionYear, int seqNr, String strikeBarcode, String pidm, String termCode, Date dateCheckedOut, char disposition, Double bookSalePrice, double prevPidm, String prevTermCode, Date prevDateCheckedIn, Date activityDate, char billFlag) {
        this._ID = UUID.randomUUID();
        this.bookCode = bookCode;
        this.editionYear = editionYear;
        this.seqNr = seqNr;
        this.strikeBarcode = strikeBarcode;
        this.pidm = pidm;
        this.termCode = termCode;
        this.dateCheckedOut = dateCheckedOut;
        this.disposition = disposition;
        this.bookSalePrice = bookSalePrice;
        this.prevPidm = prevPidm;
        this.prevTermCode = prevTermCode;
        this.prevDateCheckedIn = prevDateCheckedIn;
        this.activityDate = activityDate;
        this.billFlag = billFlag;
    }

    /**
     * <p>
     * Returns the bookCode.
     * </p>
     *
     * @return String bookCode information.
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * <p>
     * Returns the billFlag.
     * </p>
     *
     * @return char billFlag information.
     */
    public char getBillFlag() {
        return billFlag;
    }

    /**
     * <p>
     * Returns the disposition.
     * </p>
     *
     * @return char disposition information.
     */
    public char getDisposition() {
        return disposition;
    }

    /**
     * <p>
     * Returns the activityDate.
     * </p>
     *
     * @return Date activityDate information.
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     * <p>
     * Returns the dateCheckedOut.
     * </p>
     *
     * @return Date dateCheckedOut information.
     */
    public Date getDateCheckedOut() {
        return dateCheckedOut;
    }

    /**
     * <p>
     * Returns the prevDateCheckedIn.
     * </p>
     *
     * @return Date prevDateCheckedIn information.
     */
    public Date getPrevDateCheckedIn() {
        return prevDateCheckedIn;
    }

    /**
     * <p>
     * Returns the bookSalePrice.
     * </p>
     *
     * @return Double bookSalePrice information.
     */
    public Double getBookSalePrice() {
        return bookSalePrice;
    }

    /**
     * <p>
     * Returns the prevPidm.
     * </p>
     *
     * @return Double prevPidm information.
     */
    public double getPrevPidm() {
        return prevPidm;
    }

    /**
     * <p>
     * Returns the editionYear.
     * </p>
     *
     * @return String editionYear information.
     */
    public String getEditionYear() {
        return editionYear;
    }

    /**
     * <p>
     * Returns the seqNr.
     * </p>
     *
     * @return int seqNr information.
     */
    public int getSeqNr() {
        return seqNr;
    }

    /**
     * <p>
     * Returns the pidm.
     * </p>
     *
     * @return String pidm information.
     */
    public String getPidm() {
        return pidm;
    }

    /**
     * <p>
     * Returns the prevTermCode.
     * </p>
     *
     * @return String prevTermCode information.
     */
    public String getPrevTermCode() {
        return prevTermCode;
    }

    /**
     * <p>
     * Returns the strikeBarcode.
     * </p>
     *
     * @return String strikeBarcode information.
     */
    public String getStrikeBarcode() {
        return strikeBarcode;
    }

    /**
     * <p>
     * Returns the termCode.
     * </p>
     *
     * @return String termCode information.
     */
    public String getTermCode() {
        return termCode;
    }

    /**
     * <p>
     * Returns the _ID.
     * </p>
     *
     * @return UUID _ID information.
     */
    public UUID get_ID() {
        return _ID;
    }

    /**
     * <p>
     * Sets the activityDate to a new date.
     * </p>
     *
     * @param activityDate: Date activityDate variable
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    /**
     * <p>
     * Sets the billFlag to a new character.
     * </p>
     *
     * @param billFlag: char billFlag variable
     */
    public void setBillFlag(char billFlag) {
        this.billFlag = billFlag;
    }

    /**
     * <p>
     * Sets the bookCode to a new string.
     * </p>
     *
     * @param bookCode: String bookCode variable
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    /**
     * <p>
     * Sets the bookSalePrice to a new double.
     * </p>
     *
     * @param bookSalePrice: double bookSalePrice variable
     */
    public void setBookSalePrice(Double bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    /**
     * <p>
     * Sets the dateCheckedOut to a new date.
     * </p>
     *
     * @param dateCheckedOut: Date dateCheckedOut variable
     */
    public void setDateCheckedOut(Date dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    /**
     * <p>
     * Sets the disposition to a new character.
     * </p>
     *
     * @param disposition: char disposition variable
     */
    public void setDisposition(char disposition) {
        this.disposition = disposition;
    }

    /**
     * <p>
     * Sets the editionYear to a new date.
     * </p>
     *
     * @param editionYear: int editionYear variable
     */
    public void setEditionYear(String editionYear) {
        this.editionYear = editionYear;
    }

    /**
     * <p>
     * Sets the pidm to a new string.
     * </p>
     *
     * @param pidm: String pidm variable
     */
    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    /**
     * <p>
     * Sets the prevDateCheckedIn to a new date.
     * </p>
     *
     * @param prevDateCheckedIn: Date prevDateCheckedIn variable
     */
    public void setPrevDateCheckedIn(Date prevDateCheckedIn) {
        this.prevDateCheckedIn = prevDateCheckedIn;
    }

    /**
     * <p>
     * Sets the prevPidm to a new double.
     * </p>
     *
     * @param prevPidm: double prevPidm variable
     */
    public void setPrevPidm(double prevPidm) {
        this.prevPidm = prevPidm;
    }

    /**
     * <p>
     * Sets the prevTermCode to a new string.
     * </p>
     *
     * @param prevTermCode: String prevTermCode variable
     */
    public void setPrevTermCode(String prevTermCode) {
        this.prevTermCode = prevTermCode;
    }

    /**
     * <p>
     * Sets the seqNr to a new int.
     * </p>
     *
     * @param seqNr: int seqNr variable
     */
    public void setSeqNr(int seqNr) {
        this.seqNr = seqNr;
    }

    /**
     * <p>
     * Sets the strikeBarcode to a new string.
     * </p>
     *
     * @param strikeBarcode: String strikeBarcode variable
     */
    public void setStrikeBarcode(String strikeBarcode) {
        this.strikeBarcode = strikeBarcode;
    }

    /**
     * <p>
     * Sets the termCode to a new string.
     * </p>
     *
     * @param termCode: String termCode variable
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    /**
     * <p>
     * Sets the _ID to a new UUID.
     * </p>
     *
     * @param _ID: UUID _ID variable
     */
    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    /**
     * <p>
     * Updates a Java BookCopy object with the values given from the DBO object information.
     * </p>
     *
     * @param values: Generic object holding the DBO information of a BookCopy
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        // The int index value for values[] represents the corresponding column in the table and follows in order.
        // order: pkey (bookCode, editionYear, seqNr), strikeBarcode, dateCheckedOut, disposition,
        // bookSalePrice, prevPidm, prevTermCode, prevDateCheckedIn, activityDate, billFlag
        this.bookCode = (String) values[0];
        this.editionYear = ((String)values[1]);
        if(values[2] != null)
            this.seqNr = ((BigDecimal) values[2]).intValue();
        this.strikeBarcode = (String) values[3];
        if(values[4] != null)
            this.pidm = (String) values[4];
        if(values[5] != null)
            this.termCode = (String) values[5];
        if(values[6] != null)
            this.dateCheckedOut = (Date) values[6];
        if(values[7] != null)
            this.disposition = ((String) values[7]).charAt(0);
        if(values[8] != null)
            this.bookSalePrice = ((BigDecimal) values[8]).doubleValue();
        if(values[9] != null)
            this.prevPidm = ((BigDecimal) values[9]).doubleValue();
        if(values[10] != null)
            this.prevTermCode = (String) values[10];
        if(values[11] != null)
            this.prevDateCheckedIn = (Date) values[11];
        if(values[12] != null)
            this.activityDate = (Date) values[12];
        if(values[13] != null)
            this.billFlag = ((String) values[13]).charAt(0);
    }

    @Override
    public @NotNull JSONObject createJsonObjectForm() throws JSONException {
        JSONObject bookCopy = new JSONObject();
        bookCopy.put(NOMINAL_BOOK_CODE,this.getBookCode());
        bookCopy.put(NOMINAL_EDITION_YEAR,this.getEditionYear());
        bookCopy.put(NOMINAL_SEQ_NR,this.getSeqNr());
        bookCopy.put(NOMINAL_STRIKE_BARCODE,this.getStrikeBarcode());
        bookCopy.put(NOMINAL_PIDM,this.getPidm());
        bookCopy.put(NOMINAL_TERM_CODE,this.getTermCode());
        bookCopy.put(NOMINAL_DATE_CHECKED_OUT,this.getDateCheckedOut());
        bookCopy.put(NOMINAL_DISPOSITION,this.getDisposition());
        bookCopy.put(NOMINAL_BOOK_SALE_PRICE,this.getBookSalePrice());
        bookCopy.put(NOMINAL_PREV_PIDM,this.getPrevPidm());
        bookCopy.put(NOMINAL_PREV_TERM_CODE,this.getPrevTermCode());
        bookCopy.put(NOMINAL_PREV_DATE_CHECKED_IN,this.getPrevDateCheckedIn());
        bookCopy.put(NOMINAL_ACTIVITY_DATE,this.getActivityDate());
        bookCopy.put(NOMINAL_BILL_FLAG,this.getBillFlag());

        return bookCopy;
    }

    // todo: add doc
    @Override
    public boolean equals(Object object) {

        // todo: add comments
        if (object == null)
            return false;

        else if (object == this)
            return true;

        final BookCopy book = (BookCopy) object;

        return
                this.getBookCode().equals(book.getBookCode()) &&
                        this.getEditionYear().equals(book.getEditionYear()) &&
                        this.getSeqNr() ==  book.getSeqNr() &&
                        this.getStrikeBarcode().equals(book.getStrikeBarcode()) &&
                        this.getPidm().equals(book.getPidm()) &&
                        this.getTermCode().equals(book.getTermCode()) &&
                        this.getDateCheckedOut().equals(book.getDateCheckedOut()) &&
                        this.getDisposition() == book.getDisposition() &&
                        this.getBookSalePrice().equals(book.getBookSalePrice()) &&
                        this.getPrevPidm() == book.getPrevPidm() &&
                        this.getPrevTermCode().equals(book.getPrevTermCode()) &&
                        this.getPrevDateCheckedIn().equals(book.getPrevDateCheckedIn()) &&
                        this.getActivityDate().equals(book.getActivityDate()) &&
                        this.getBillFlag() == book.getBillFlag();
    }

    // todo: add doc
    private static @Nullable String convertDateToDateString(@Nullable java.util.Date date){

        if(date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1); // 0 index so add one
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" +
                String.valueOf(calendar.get(Calendar.MONTH)) + "-" +
                String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public String toString(){
        return
                "bookCode: " + this.getBookCode() +
                        "\neditionYear: " + this.getEditionYear() +
                        "\nseqNr: " + this.getSeqNr() +
                        "\nstrikeBarcode: " + this.getStrikeBarcode() +
                        "\npidm: " + this.getPidm() +
                        "\ntermCode: " + this.getTermCode() +
                        "\ndateCheckedOut: " + BookCopy.convertDateToDateString(this.getDateCheckedOut()) +
                        "\ndisposition: " + this.getDisposition() +
                        "\nbookSalePrice: " + this.getBookSalePrice() +
                        "\nprevPidm: " + this.getPrevPidm() +
                        "\nprevTermCode: " + this.getPrevTermCode() +
                        "\nprevDateCheckedIn: " + BookCopy.convertDateToDateString(this.getPrevDateCheckedIn()) +
                        "\nactivityDate: " + BookCopy.convertDateToDateString(this.getActivityDate()) +
                        "\nbillFlag: " + this.getBillFlag();
    }



}