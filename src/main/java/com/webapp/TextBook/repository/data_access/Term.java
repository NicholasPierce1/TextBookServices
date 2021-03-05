package com.webapp.TextBook.repository.data_access;

import com.webapp.TextBook.repository.DataAccessConversion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Term implements DataAccessConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public String termCode;
    public String termDescription;

    public Term(){this._ID = UUID.randomUUID();}

    public Term(String termCode, String termDescription) {
        this.termCode = termCode;
        this.termDescription = termDescription;
        this._ID = UUID.randomUUID();
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermCode() {
        return termCode;
    }

    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }

    public UUID get_ID() {
        return _ID;
    }

    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {
        // order: pkey, termDescription

        this.termCode = (String)values[0];
        this.termDescription = (String)values[1];
    }
}
