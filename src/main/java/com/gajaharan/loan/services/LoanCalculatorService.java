package com.gajaharan.loan.services;

/**
 * Created by gsatkunanandan on 20/03/2018.
 */
public interface LoanCalculatorService {
    double getAverageLoanRate();

    double getMonthlyPayment();

    double getTotalPayment();
}
