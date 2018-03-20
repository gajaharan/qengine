package com.gajaharan.loan.models;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class Lender implements Comparable<Lender> {
    private String name;
    private double rate;
    private Integer availableAmount;

    public Lender(String name, double rate, Integer availableAmount) {
        this.name = name;
        this.rate = rate;
        this.availableAmount = availableAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public int compareTo(Lender lender) {
        return Double.compare(getRate(), lender.getRate());
    }


}
