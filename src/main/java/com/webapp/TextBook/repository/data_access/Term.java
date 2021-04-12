package com.webapp.TextBook.repository.data_access;

import com.webapp.TextBook.repository.DataAccessConversion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * <h1>Term</h1>
 * <h2>Type: Class</h2>
 *
 * Holds information relating to a Term.
 * These values are determined and fully defined from the business context and are explicated within
 * the FRD and traceability document
 */

@Entity
public class Term implements DataAccessConversion {

    /**
     * <p>
     * GeneratedValue creates a unique id for each Term created
     * Usage: Hold primary key value for a unique Term instance
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    /**
     * <p>
     * the number that denotes the year and trimester
     * Usage: Attribute in creation of Term for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String termCode;

    /**
     * <p>
     * the name representation of the term code
     * Usage: Attribute in creation of Term for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String termDescription;

    /**
     * <p>
     *  Creates a Term -- Empty constructor.
     *</p>
     */
    public Term(){this._ID = UUID.randomUUID();}

    /**
     * <p>
     *  Creates a Term -- Full constructor with all variables.
     *</p>
     *
     * @param termCode: String termCode variable
     * @param termDescription: String termDescription variable
     */
    public Term(String termCode, String termDescription) {
        this.termCode = termCode;
        this.termDescription = termDescription;
        this._ID = UUID.randomUUID();
    }

    /**
     *<p>
     *     Sets the termCode to a new String.
     *</p>
     *
     * @param termCode: String termCode variable
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    /**
     *<p>
     *     Returns the termCode.
     *</p>
     *
     * @return String termCode information.
     */
    public String getTermCode() {
        return termCode;
    }

    /**
     *<p>
     *     Returns the termDescription.
     *</p>
     *
     * @return String termDescription information.
     */
    public String getTermDescription() {
        return termDescription;
    }

    /**
     *<p>
     *     Sets the termDescription to a new String.
     *</p>
     *
     * @param termDescription: String termDescription variable
     */
    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }

    /**
     *<p>
     *     Returns the _ID.
     *</p>
     *
     * @return UUID _ID information.
     */
    public UUID get_ID() {
        return _ID;
    }

    /**
     *<p>
     *     Sets the _ID to a new UUID.
     *</p>
     *
     * @param _ID: UUID _ID variable
     */
    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    /**
     *<p>
     * Updates a Java Term object with the values given from the DBO object information.
     *</p>
     *
     * @param values: Generic object holding the DBO information of a Term
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        // order: pkey, termDescription

        this.termCode = (String)values[0];
        this.termDescription = (String)values[1];
    }


    // todo: add doc here
    @Override
    public boolean equals(Object object){

        // todo: add comments
        if(object == null)
            return false;

        else if (object == this)
            return true;

        final Term term = (Term)object;

        return this.getTermCode().equals(term.getTermCode()) && this.getTermDescription().equals(term.getTermDescription());

    }

    @Override
    public String toString(){
        return "TermCode: " + this.getTermCode() +
                "\nTermDescription: " + this.getTermDescription();

    }
}
