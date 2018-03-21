package com.gajaharan.loan.models;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.services.LenderService;
import com.gajaharan.loan.services.LoanCalculatorService;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import com.gajaharan.loan.services.impl.LoanCalculatorServiceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class LoanQuote {

    private Integer requestedLoanAmount;
    private BigDecimal loanRate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    private List<Lender> lenders;

    private LenderService lenderService;
    private LoanCalculatorService quoteCalculatorService;

    public LoanQuote(Integer requestedLoanAmount, String marketData) throws LoanUnavailableException {
        this.requestedLoanAmount = requestedLoanAmount;
        this.lenderService = new LenderServiceImpl(marketData);
        this.lenders = lenderService.getListOfLendersForQuote(requestedLoanAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedLoanAmount, lenders);

        this.loanRate = quoteCalculatorService.getAverageLoanRate().scaleByPowerOfTen(2);
        this.monthlyRepayment = quoteCalculatorService.getMonthlyPayment();
        this.totalRepayment = quoteCalculatorService.getTotalPayment();
    }

    public Integer getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + getRequestedLoanAmount() + "\n" +
                "Rate:" + getLoanRate() + "%\n" +
                "Monthly repayment: £" + getMonthlyRepayment() + "\n" +
                "Total repayment: £" + getTotalRepayment();
    }
}
