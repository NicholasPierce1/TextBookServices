package com.webapp.TextBook.repository.data_access;

import com.webapp.TextBook.repository.DataAccessConversion;
import com.webapp.TextBook.repository.data_access.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public Student() {this._ID = UUID.randomUUID(); }

    public Student(String pidm, String id, String firstName, String lastName, String middleName) {
        super(pidm, id, firstName, lastName, middleName);
        this._ID = UUID.randomUUID();
    }

    public UUID get_ID() {
        return _ID;
    }

    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        super.updateDataAccessObject(values);
    }

}
