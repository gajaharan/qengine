package com.gajaharan.loan.models;

import com.gajaharan.loan.utils.CSVParser;

import java.util.List;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class LoanQuote {

    private Integer requestedLoanAmount;
    private double loanRate;
    private double monthlyRepayment;
    private double totalRepayment;

    private List<Lender> lenders;

    private CSVParser csvParser = new CSVParser();

    public LoanQuote(Integer requestedLoanAmount, String marketData) {
        this.requestedLoanAmount = requestedLoanAmount;
        this.lenders = csvParser.processCSVFile(marketData);
    }

    public Integer getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(Integer requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + requestedLoanAmount + "\n" +
                "Rate:" + getLoanRate() + "%\n" +
                "Monthly repayment: £" + getMonthlyRepayment() + "\n" +
                "Total repayment: £" + getTotalRepayment();
    }
}
