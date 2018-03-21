package com.gajaharan.loan.services.impl;

import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.LoanCalculatorService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
     * Gets the average loan rate from selected lenders for a given loan amount
     * @return
     */
    @Override
    public BigDecimal getAverageLoanRate() {
        MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);
        return lenders.stream()
                .map(Lender::getRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(lenders.size()), mathContext);
    }

    /**
     * Gets the monthly payment for a given loan amount and loan rate.
     * @return
     */
    @Override
    public BigDecimal getMonthlyPayment() {
        MathContext mathContext = new MathContext(4, RoundingMode.HALF_DOWN);
        BigDecimal monthlyInterestRate = getAverageLoanRate().divide(new BigDecimal(12), 100, RoundingMode.HALF_DOWN);
        return (new BigDecimal(requestedAmount)
                .multiply(monthlyInterestRate))
                .divide(BigDecimal.valueOf((1 - Math.pow(1 / (1 + monthlyInterestRate.doubleValue()), LOAN_TERM_IN_MONTHS))), mathContext)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Gets the total payment for a given loan amount and loan rate.
     * @return
     */
    @Override
    public BigDecimal getTotalPayment() {
        return getMonthlyPayment()
                .multiply(BigDecimal.valueOf(LOAN_TERM_IN_MONTHS))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
