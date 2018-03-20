package com.gajaharan.loan.exceptions;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class InvalidRequestAmountException extends Exception {

    /**
     * This exception is thrown when an invalid amount is given
     *
     * @param message
     */
    public InvalidRequestAmountException(String message) {
        super(message);
    }
}
