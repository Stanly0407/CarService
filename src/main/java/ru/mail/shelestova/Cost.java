package ru.mail.shelestova;

import java.io.Serializable;

public abstract class Cost implements Serializable, Cloneable {
    private int cost;

    public Cost() {
    }

    public Cost(int cost) {
        this.cost = cost;
    }

    public String getCompanyOffice() {
        return cost;
    }

    public void CompanyOffice(int cost) {
        this.cost = cost;
    }
}
