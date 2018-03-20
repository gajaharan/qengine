package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;

import static com.gajaharan.loan.config.GeneralConfig.*;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class InputValidator {

    /**
     * Tests if the number of arguments passed from command line is valid
     *
     * @param args
     * @return
     * @throws IllegalArgumentException
     */
    public boolean isValidArgumentLength(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException(INVALID_ARGUMENT);
        }
        return true;
    }

    /**
     * Tests if the number is numeric (not alpanumeric)
     * @param requestedAmountArg
     * @return
     * @throws IllegalArgumentException
     */
    public boolean isNumeric(String requestedAmountArg) throws IllegalArgumentException {
        // Could parse number to test NumberFormatException, but keeping it simple.
        if (!requestedAmountArg.matches("-?\\d+(\\.\\d+)?")) {  //match a number with optional '-' and decimal.
            throw new IllegalArgumentException(INVALID_NUMBER);
        }
        return true;
    }

    /**
     * Tests if the loan amount is within the lenders range and incremental amount is valid
     * @param loanAmount
     * @return
     * @throws InvalidRequestAmountException
     */
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
