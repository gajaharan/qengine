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

    /**
     *
     * @return Lender's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Lender's current rate
     */
    public double getRate() {
        return rate;
    }

    /**
     *
     * @return Lender's available amount from lenders
     */
    public Integer getAvailableAmount() {
        return availableAmount;
    }

    /**
     * Compares the different lender's rates
     */
    @Override
    public int compareTo(Lender lender) {
        return Double.compare(getRate(), lender.getRate());
    }


}
