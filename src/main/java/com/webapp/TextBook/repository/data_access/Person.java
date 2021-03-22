package com.webapp.TextBook.repository.data_access;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.webapp.TextBook.repository.DataAccessConversion;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * <h1>Person</h1>
 * <h2>Type: Abstract Class</h2>
 *
 * <p> This is a parent class to create a general person. This is not a database entity
 * as each person is then given a more specific role to coincide with the database.</p>
 *
 */
public abstract class Person implements DataAccessConversion {

    /**
     * <p>
     * String variable to hold the unique string code connected to a student's 919 number.
     * Usage: Attribute in creation of a Person for later connection to an entity in the database
     * for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String pidm;

    /**
     * <p>
     * String variable to hold a person's 919 number.
     * Usage: Attribute in creation of a Person for later connection to an entity in the database
     * for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String id;

    /**
     * <p>
     * String variable to hold the first name of a person.
     * Usage: Attribute in creation of a Person for later connection to an entity in the database
     * for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String firstName;

    /**
     * <p>
     * String variable to hold the last name of a person.
     * Usage: Attribute in creation of a Person for later connection to an entity in the database
     * for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String lastName;

    /**
     * <p>
     * String variable to hold the middle name of a person.
     * Usage: Attribute in creation of a Person for later connection to an entity in the database
     * for ORM conversion of Oracle's DBO into DA POJO.
     * </p>
     */
    public String middleName;

    /**
     * <p>
     *  Creates a Person -- Empty constructor.
     *</p>
     */
    public Person() { }

    /**
     * <p>
     *  Creates a Person -- Full constructor with all variables.
     *</p>
     *
     * @param pidm: String pidm variable
     * @param id: String id variable
     * @param firstName: String firstName variable
     * @param lastName: String lastName variable
     * @param middleName: String middleName variable
     */
    public Person(String pidm, String id, String firstName, String lastName, String middleName) {
        this.pidm = pidm;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    /**
     *<p>
     *     Returns the firstName.
     *</p>
     *
     * @return String firstName information.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *<p>
     *     Returns the id.
     *</p>
     *
     * @return String id information.
     */
    public String getId() {
        return id;
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
     *     Returns the lastName.
     *</p>
     *
     * @return String lastName information.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *<p>
     *     Returns the middleName.
     *</p>
     *
     * @return String middleName information.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     *<p>
     *     Sets the id with the given id.
     *</p>
     *
     * @param id: String id variable
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *<p>
     *     Sets the firstName with the given name.
     *</p>
     *
     * @param firstName: String firstName variable
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *<p>
     *     Sets the middleName with the given name.
     *</p>
     *
     * @param middleName: String middleName variable
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *<p>
     *     Sets the lastName with the given name.
     *</p>
     *
     * @param lastName: String lastName variable
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *<p>
     *     Sets the pidm with the given pidm.
     *</p>
     *
     * @param pidm: String pidm variable
     */
    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    /**
     *<p>
     * Parent method to update generic person information. This can be used to update
     * specific entities that also contain this information.
     *</p>
     *
     * @param values: Generic object holding the DBO information of a Person.
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        // set person attributes here in order of spriden table

        // order: pkey, id, lastName, firstName, middleName
        this.pidm = String.valueOf(((BigDecimal)values[0]).doubleValue());
        this.id = (String)values[1];
        this.lastName = (String)values[2];
        this.firstName = (String)values[3];
        this.middleName = (String)values[4];
    }
}
