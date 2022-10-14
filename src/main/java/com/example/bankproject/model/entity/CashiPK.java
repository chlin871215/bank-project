package com.example.bankproject.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class CashiPK implements Serializable {

    private String mgniId;
    private String accNo;
    private String ccy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashiPK that = (CashiPK) o;
        return Objects.equals(mgniId, that.mgniId) && Objects.equals(accNo, that.accNo) && Objects.equals(ccy, that.ccy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accNo, ccy, mgniId);
    }


}
