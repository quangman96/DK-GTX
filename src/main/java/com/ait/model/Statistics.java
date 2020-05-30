package com.ait.model;

public class Statistics {
    private String element;
    private Long amount;

    public Statistics(String element, Long amount) {
        this.element = element;
        this.amount = amount;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
