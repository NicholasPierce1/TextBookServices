package com.webapp.TextBook.repository.data_access;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.data_access.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * <h1>Student</h1>
 * <h2>Type: Class</h2>
 *
 * Holds information relating to a Student.
 * These values are determined and fully defined from the business context and are explicated within
 * the FRD and traceability document
 */
@Entity
public class Student extends Person {

    /**
     * <p>
     * GeneratedValue creates a unique id for each Student created
     * Usage: Hold primary key value for a unique Student instance
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    /**
     * <p>
     *  Creates a Student -- Empty constructor.
     *</p>
     */
    public Student() {this._ID = UUID.randomUUID(); }

    /**
     * <p>
     *  Creates a Student -- Full constructor with all variables.
     *</p>
     *
     * @param pidm: String pidm variable
     * @param id: String id variable
     * @param firstName: String firstName variable
     * @param lastName: String lastName variable
     * @param middleName: String middleName variable
     */
    public Student(String pidm, String id, String firstName, String lastName, String middleName) {
        super(pidm, id, firstName, lastName, middleName);
        this._ID = UUID.randomUUID();
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
     * Updates a Java Student object with the values given from the DBO object information.
     *</p>
     *
     * @param values: Generic object holding the DBO information of a Student
     */
    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        // given: pkey, id, lastName, firstName, middleName
        super.updateDataAccessObject(values);
    }

}
