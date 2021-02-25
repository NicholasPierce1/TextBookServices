package com.webapp.TextBook.repository.data_access;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class DataAccessKeyManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public DataAccessKeyManager(){
        this._ID = UUID.randomUUID();
    }
}
