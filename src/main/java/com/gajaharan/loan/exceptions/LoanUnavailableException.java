package com.gajaharan.loan.exceptions;

/**
 * Created by gsatkunanandan on 19/03/2018.
 */
public class LoanUnavailableException extends Exception {

    /**
     * This exception is thrown when the loan amount is not available from the lenders
     *
     * @param message
     */
    public LoanUnavailableException(String message) {
        super(message);
    }
}
