package com.webapp.TextBook.repository.data_access;

import com.webapp.TextBook.repository.DataAccessConversion;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class BookCopy implements DataAccessConversion {
    //Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public String bookCode;
    public int editionYear;
    public int seqNr;
    public String strikeBarcode;
    public String pidm;
    public String termCode;
    public Date dateCheckedOut;
    public char disposition;
    public double bookSalePrice;
    public double prevPidm;
    public String prevTermCode;
    public Date prevDateCheckedIn;
    public Date activityDate;
    public char billFlag;

    //Constructors
    public BookCopy() {
        this._ID = UUID.randomUUID();
    }

    public BookCopy(String bookCode, int editionYear, int seqNr, String strikeBarcode, String pidm, String termCode, Date dateCheckedOut, char disposition, double bookSalePrice, double prevPidm, String prevTermCode, Date prevDateCheckedIn, Date activityDate, char billFlag) {
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

    //Getters and Setters

    public String getBookCode() {
        return bookCode;
    }

    public char getBillFlag() {
        return billFlag;
    }

    public char getDisposition() {
        return disposition;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public Date getDateCheckedOut() {
        return dateCheckedOut;
    }

    public Date getPrevDateCheckedIn() {
        return prevDateCheckedIn;
    }

    public double getBookSalePrice() {
        return bookSalePrice;
    }

    public double getPrevPidm() {
        return prevPidm;
    }

    public int getEditionYear() {
        return editionYear;
    }

    public int getSeqNr() {
        return seqNr;
    }

    public String getPidm() {
        return pidm;
    }

    public String getPrevTermCode() {
        return prevTermCode;
    }

    public String getStrikeBarcode() {
        return strikeBarcode;
    }

    public String getTermCode() {
        return termCode;
    }

    public UUID get_ID() {
        return _ID;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public void setBillFlag(char billFlag) {
        this.billFlag = billFlag;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public void setBookSalePrice(double bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    public void setDateCheckedOut(Date dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    public void setDisposition(char disposition) {
        this.disposition = disposition;
    }

    public void setEditionYear(int editionYear) {
        this.editionYear = editionYear;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public void setPrevDateCheckedIn(Date prevDateCheckedIn) {
        this.prevDateCheckedIn = prevDateCheckedIn;
    }

    public void setPrevPidm(double prevPidm) {
        this.prevPidm = prevPidm;
    }

    public void setPrevTermCode(String prevTermCode) {
        this.prevTermCode = prevTermCode;
    }

    public void setSeqNr(int seqNr) {
        this.seqNr = seqNr;
    }

    public void setStrikeBarcode(String strikeBarcode) {
        this.strikeBarcode = strikeBarcode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {

    }

}
