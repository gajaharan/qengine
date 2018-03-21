package com.gajaharan.loan.models;

import java.math.BigDecimal;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class Lender implements Comparable<Lender> {
    private String name;
    private BigDecimal rate;
    private BigDecimal availableAmount;

    public Lender() {

    }

    public Lender(String name, BigDecimal rate, BigDecimal availableAmount) {
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
    public BigDecimal getRate() {
        return rate;
    }

    /**
     *
     * @return Lender's available amount from lenders
     */
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    /**
     * Compares the different lender's rates
     */
    @Override
    public int compareTo(Lender lender) {
        return getRate().compareTo(lender.getRate());
    }


}
