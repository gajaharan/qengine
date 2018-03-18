package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import com.gajaharan.loan.utils.InputValidator;

public class App
{
    public static void main( String[] args ) throws InvalidRequestAmountException {
        String csvFile = args[0];
        Integer loanAmount = new Integer(args[1]);
        InputValidator inputValidator = new InputValidator();

        if (inputValidator.isValidLoanAmount(loanAmount)) {
            // Parse CSV file and create lenders
        }
    }
}
