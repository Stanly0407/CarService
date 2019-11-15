package ru.mail.shelestova;

import java.io.Serializable;

public abstract class Cost implements Serializable, Cloneable {
    protected int cost;

    public Cost() {
    }

    public Cost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
