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
 * <h1>Bag</h1>
 * <h2>Type: Class</h2>
 *
 * Holds information relating to a bag.
 * These values are determined and fully defined from the business context and are explicated within
 * the FRD and traceability document
 */

@Entity
public class Bag implements DataAccessConversion {
    /**
     * <p>
     * GeneratedValue creates a unique id for each bag created
     * Usage: Hold primary key value for a unique bag instance
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    /**
     * <p>
     * unique string-code that's tailored to a student's 919
     * Usage: Attribute in creation of Bag for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String pidm;

    /**
     * <p>
     * the number associated to the student for the current
     * trimester that they are enrolled & verified for.
     * Usage: Attribute in creation of Bag for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public Double bagNumber;

    /**
     * <p>
     *  Creates a Bag -- Empty constructor.
     *</p>
     */
    public Bag(){this._ID = UUID.randomUUID();}

    /**
     * <p>
     *  Creates a Bag -- Full constructor with all variables.
     *</p>
     *
     * @param pidm: String pidm variable
     * @param bagNumber: char bagNumber variable
     */
    public Bag(String pidm, Double bagNumber){
        this._ID = this._ID = UUID.randomUUID();
        this.pidm = pidm;
        this.bagNumber = bagNumber;
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
     *     Sets the pidm to a new string.
     *</p>
     *
     * @param pidm: String pidm variable
     */
    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    /**
     *<p>
     *     Returns the pidm.
     *</p>
     *
     * @return String pidm information.
     */
    public String getPidm() {
        return pidm;
    }

    /**
     *<p>
     *     Returns the bagNumber.
     *</p>
     *
     * @return Double bagNumber information.
     */
    public Double getBagNumber() {
        return bagNumber;
    }

    /**
     *<p>
     *     Sets the bagNumber to a new Double.
     *</p>
     *
     * @param bagNumber: Double bagNumber variable
     */
    public void setBagNumber(Double bagNumber) {
        this.bagNumber = bagNumber;
    }

    /**
     *<p>
     * Updates a Java Bag object with the values given from the DBO object information.
     *</p>
     *
     * @param values: Generic object holding the DBO information of a Bag
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {

        // given: pkey, bagNumber
        this.pidm = (String)values[0];
        this.bagNumber = ((BigDecimal)values[1]).doubleValue();
    }

    // todo: add doc here
    @Override
    public boolean equals(Object object){

        // todo: add comments
        if(object == null)
            return false;

        else if (object == this)
            return true;

        final Bag bag = (Bag)object;

        return this.getPidm().equals(bag.getPidm()) && this.getBagNumber().equals(bag.getBagNumber());

    }
}
