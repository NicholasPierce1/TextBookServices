package com.webapp.TextBook.repository.data_access;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class User extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public UserRole userRole;

    public User() {this._ID = UUID.randomUUID(); }

    public User(String pidm, String id, String firstName, String lastName, String middleName, String pidm1, UserRole userRole) {
        super(pidm, id, firstName, lastName, middleName);
        this._ID = UUID.randomUUID();
        this.userRole = userRole;
    }

    public UUID get_ID() {
        return _ID;
    }

    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public static @NotNull Object[] conglomerateDboArrays( @NotNull final Object[] spridenDbo, @NotNull final Object[] textbookServiceLoginDbo){
        return null;
    }

    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {

    }
}
