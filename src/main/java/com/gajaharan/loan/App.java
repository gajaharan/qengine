package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.utils.CSVParser;
import com.gajaharan.loan.utils.InputValidator;

import java.util.List;

public class App {
    public static void main( String[] args ) throws InvalidRequestAmountException {
        String csvFile = args[0];
        Integer loanAmount = new Integer(args[1]);
        InputValidator inputValidator = new InputValidator();
        CSVParser csvParser = new CSVParser();

        if (inputValidator.isValidLoanAmount(loanAmount)) {
            List<Lender> lenders = csvParser.processCSVFile(csvFile);
        }
    }
}
