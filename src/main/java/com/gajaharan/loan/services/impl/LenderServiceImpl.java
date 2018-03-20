package com.gajaharan.loan.services.impl;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.LenderService;
import com.gajaharan.loan.utils.CSVParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gajaharan.loan.config.GeneralConfig.QUOTE_UNAVAILABLE;

/**
 * Created by gsatkunanandan on 19/03/2018.
 */
public class LenderServiceImpl implements LenderService {
    private List<Lender> lenders;
    private CSVParser csvParser;

    public LenderServiceImpl(String csvFile) {
        csvParser = new CSVParser();
        lenders = csvParser.processCSVFile(csvFile);
    }

    @Override
    public List<Lender> getLenders() {
        return lenders;
    }

    @Override
    public List<Lender> getListOfLendersForQuote(Integer requestedAmount) throws LoanUnavailableException {
        double availableAmountToLend = getMaximumLoanValue();

        if (requestedAmount > availableAmountToLend) {
            throw new LoanUnavailableException(QUOTE_UNAVAILABLE);
        }

        // Sort lenders by lowest rate first
        Collections.sort(lenders);

        Integer remainingAmount = requestedAmount;
        List<Lender> selectedLenders = new ArrayList<>();

        for (Lender lender : lenders) {
            if (remainingAmount <= 0) {
                break;
            }
            if (lender.getAvailableAmount() > remainingAmount) {
                selectedLenders.add(lender);
                remainingAmount = 0;

            } else {
                remainingAmount -= lender.getAvailableAmount();
                selectedLenders.add(lender);
            }
        }
        return selectedLenders;
    }

    @Override
    public double getMaximumLoanValue() {
        return lenders.stream()
                .mapToInt(Lender::getAvailableAmount)
                .sum();

    }
}
