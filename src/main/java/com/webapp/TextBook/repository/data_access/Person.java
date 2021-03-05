package com.webapp.TextBook.repository.data_access;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.webapp.TextBook.repository.DataAccessConversion;

import java.math.BigDecimal;
import java.util.UUID;


public abstract class Person implements DataAccessConversion {



    //Attributes
    public String pidm;
    public String id;
    public String firstName;
    public String lastName;
    public String middleName;

    public Person() { }

    public Person(String pidm, String id, String firstName, String lastName, String middleName) {
        this.pidm = pidm;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getPidm() {
        return pidm;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

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
