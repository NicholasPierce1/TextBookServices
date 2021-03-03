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
public class Bag implements DataAccessConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _ID;

    public String pidm;
    public Double bagNumber;

    public Bag(){this._ID = UUID.randomUUID();}

    public Bag(String pidm, Double bagNumber){
        this._ID = this._ID = UUID.randomUUID();
        this.pidm = pidm;
        this.bagNumber = bagNumber;
    }

    public void set_ID(UUID _ID) {
        this._ID = _ID;
    }

    public UUID get_ID() {
        return _ID;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getPidm() {
        return pidm;
    }

    public Double getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(Double bagNumber) {
        this.bagNumber = bagNumber;
    }

    @Override
    public void updateDataAccessObject(@NotNull Object[] values) {

        // given: pkey, bagNumber
        this.pidm = (String)values[0];
        this.bagNumber = ((BigDecimal)values[1]).doubleValue();
    }
}
