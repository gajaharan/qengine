package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.LoanQuote;
import com.gajaharan.loan.utils.InputValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println(process(args));

        } catch (IllegalArgumentException iae) {
            LOGGER.log(Level.SEVERE, iae.getMessage(), iae);
            System.out.println(iae.getMessage());
        } catch (LoanUnavailableException lue) {
            LOGGER.log(Level.SEVERE, lue.getMessage(), lue);
            System.out.println(lue.getMessage());
        } catch (InvalidRequestAmountException irae) {
            LOGGER.log(Level.SEVERE, irae.getMessage(), irae);
            System.out.println(irae.getMessage());
        }
    }

    public static LoanQuote process(String[] args) throws IllegalArgumentException, InvalidRequestAmountException, LoanUnavailableException {
        InputValidator inputValidator = new InputValidator();
        LoanQuote quote = null;

        if (inputValidator.isValidArgumentLength(args) && inputValidator.isNumeric(args[1])) {
            String csvFile = args[0];
            Integer loanAmount = new Integer(args[1]);

            if (inputValidator.isValidLoanAmount(loanAmount)) {
                quote = new LoanQuote(loanAmount, csvFile);
            }
        }

        return quote;
    }
}
