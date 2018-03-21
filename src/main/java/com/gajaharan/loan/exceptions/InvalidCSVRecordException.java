package com.gajaharan.loan.exceptions;

/**
 * Created by gsatkunanandan on 21/03/2018.
 */
public class InvalidCSVRecordException extends Exception {

    /**
     * This exception is thrown when a record in csv file has invalid values
     *
     * @param message
     */
    public InvalidCSVRecordException(String message) {
        super(message);
    }
}

