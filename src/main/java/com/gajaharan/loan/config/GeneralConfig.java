package com.gajaharan.loan.config;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class GeneralConfig {
    public static final String DELIMITER = ",";

    public static final int INCREMENT_AMOUNT = 100;
    public static final int MIN_LOAN = 1000;
    public static final int MAX_LOAN = 15000;
    public static final int LOAN_TERM_IN_MONTHS = 36;

    public static final String INVALID_ARGUMENT = "Please specify in the format of quote-engine.jar [market_file] [loan_amount]";
    public static final String INVALID_LOAN_AMOUNT = "The loan amount should be between 1000 and 15000";
    public static final String INVALID_INCREMENT_AMOUNT = "The loan amount is not an incremental of 100";
    public static final String INVALID_NUMBER = "The loan amount is not valid number";
    public static final String QUOTE_UNAVAILABLE = "Sorry, it is not possible to provide a loan at this time.";

    public static final String TEST_RESOURCE_LOCATION = "src/test/resources/MarketData.csv";
}
