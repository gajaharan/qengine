package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import com.gajaharan.loan.models.LoanQuote;
import com.gajaharan.loan.utils.InputValidator;

public class App {
    public static void main( String[] args ) throws IllegalArgumentException, InvalidRequestAmountException {
        InputValidator inputValidator = new InputValidator();

        if (inputValidator.isValidArgumentLength(args) && inputValidator.isNumeric(args[1])) {
            String csvFile = args[0];
            Integer loanAmount = new Integer(args[1]);

            if (inputValidator.isValidLoanAmount(loanAmount)) {
                System.out.println(new LoanQuote(loanAmount, csvFile));
            }
        }

    }
}
