package com.relyon.a1010.model;

public class Criteria {

    private String criteria;
    private Float sumOfValuations;

    public Criteria() {
    }

    public Criteria(String criteria, Float sumOfValuations) {
        this.criteria = criteria;
        this.sumOfValuations = sumOfValuations;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Float getSumOfValuations() {
        return sumOfValuations;
    }

    public void setSumOfValuations(Float sumOfValuations) {
        this.sumOfValuations = sumOfValuations;
    }
}
