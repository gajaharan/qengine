package com.gajaharan.loan.services.impl;

import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.LoanCalculatorService;

import java.util.List;

import static com.gajaharan.loan.config.GeneralConfig.LOAN_TERM_IN_MONTHS;

/**
 * Created by gsatkunanandan on 20/03/2018.
 */
public class LoanCalculatorServiceImpl implements LoanCalculatorService {

    private Integer requestedAmount;
    private List<Lender> lenders;

    public LoanCalculatorServiceImpl(Integer requestedAmount, List<Lender> lenders){
        this.requestedAmount = requestedAmount;
        this.lenders = lenders;
    }

    /**
     *
     * Gets the average loan rate from selected lenders for a given loan amount
     * @return
     */
    @Override
    public double getAverageLoanRate() {
        return this.lenders.stream()
                .mapToDouble(t -> t.getRate())
                .average()
                .getAsDouble();
    }

    /**
     *
     * Gets the monthly payment for a given loan amount and loan rate.
     * @return
     */
    @Override
    public double getMonthlyPayment() {
        double rate = getAverageLoanRate() * 100;
        double monthlyInterestRate = rate / 1200;
        double monthlyPayment = requestedAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), LOAN_TERM_IN_MONTHS)));
        return monthlyPayment;
    }

    /**
     * Gets the total payment for a given loan amount and loan rate.
     * @return
     */
    @Override
    public double getTotalPayment() {
        return getMonthlyPayment() * LOAN_TERM_IN_MONTHS;
    }
}
