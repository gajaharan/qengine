package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;

import static com.gajaharan.loan.config.GeneralConfig.*;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class InputValidator {

    public boolean isValidArgumentLength(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException(INVALID_ARGUMENT);
        }
        return true;
    }

    public boolean isNumeric(String requestedAmountArg) throws IllegalArgumentException {
        // Could parse number to test NumberFormatException, but keeping it simple.
        if (!requestedAmountArg.matches("-?\\d+(\\.\\d+)?")) {  //match a number with optional '-' and decimal.
            throw new IllegalArgumentException(INVALID_NUMBER);
        }
        return true;
    }

    public boolean isValidLoanAmount(Integer loanAmount) throws InvalidRequestAmountException {
        if (loanAmount < MIN_LOAN || loanAmount >= MAX_LOAN) {
            throw new InvalidRequestAmountException(INVALID_LOAN_AMOUNT);
        }

        if (loanAmount % INCREMENT_AMOUNT != 0) {
            throw new InvalidRequestAmountException(INVALID_INCREMENT_AMOUNT);
        }
        return true;
    }

}
