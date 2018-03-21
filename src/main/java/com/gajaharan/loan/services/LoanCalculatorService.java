package com.gajaharan.loan.services;

import java.math.BigDecimal;

/**
 * Created by gsatkunanandan on 20/03/2018.
 */
public interface LoanCalculatorService {
    BigDecimal getAverageLoanRate();

    BigDecimal getMonthlyPayment();

    BigDecimal getTotalPayment();
}
