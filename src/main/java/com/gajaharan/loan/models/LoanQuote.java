package com.gajaharan.loan.models;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.services.LenderService;
import com.gajaharan.loan.services.LoanCalculatorService;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import com.gajaharan.loan.services.impl.LoanCalculatorServiceImpl;

import java.text.DecimalFormat;
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

    private LenderService lenderService;
    private LoanCalculatorService quoteCalculatorService;

    public LoanQuote(Integer requestedLoanAmount, String marketData) throws LoanUnavailableException {
        this.requestedLoanAmount = requestedLoanAmount;
        this.lenderService = new LenderServiceImpl(marketData);
        this.lenders = lenderService.getListOfLendersForQuote(requestedLoanAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedLoanAmount, lenders);

        this.loanRate = quoteCalculatorService.getAverageLoanRate();
        this.monthlyRepayment = quoteCalculatorService.getMonthlyPayment();
        this.totalRepayment = quoteCalculatorService.getTotalPayment();
    }

    public Integer getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + getRequestedLoanAmount() + "\n" +
                "Rate:" + Math.round(getLoanRate() * 100) + "%\n" +
                "Monthly repayment: £" + new DecimalFormat("###.##").format(getMonthlyRepayment()) + "\n" +
                "Total repayment: £" + new DecimalFormat("###.##").format(getTotalRepayment());
    }
}
