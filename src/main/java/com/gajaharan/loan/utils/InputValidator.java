package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class InputValidator {

    private static final String INVALID_LOAN_AMOUNT = "The loan amount should be between 1000 and 15000";
    private static final String INVALID_INCREMENT_AMOUNT = "The loan amount is not an incremental of 100";

    public boolean isValidLoanAmount(Integer loanAmount) throws InvalidRequestAmountException {
        if (loanAmount < 1000 || loanAmount >= 15000) {
            throw new InvalidRequestAmountException(INVALID_LOAN_AMOUNT);
        }

        if (loanAmount % 100 != 0) {
            throw new InvalidRequestAmountException(INVALID_INCREMENT_AMOUNT);
        }
        return true;
    }

}
