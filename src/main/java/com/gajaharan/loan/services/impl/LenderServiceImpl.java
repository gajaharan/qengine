package com.gajaharan.loan.services.impl;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.LenderService;
import com.gajaharan.loan.utils.CSVParser;

import java.math.BigDecimal;
import java.math.MathContext;
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

    /**
     *
     * @return All lenders from market data file
     */
    @Override
    public List<Lender> getLenders() {
        return lenders;
    }

    /**
     * Gets list of all lenders who match the requested amount from the quote
     *
     * @param amount
     * @return
     * @throws LoanUnavailableException
     */
    @Override
    public List<Lender> getListOfLendersForQuote(Integer amount) throws LoanUnavailableException {
        BigDecimal availableAmountToLend = getMaximumLoanValue();
        BigDecimal requestedAmount = new BigDecimal(amount);

        if (requestedAmount.compareTo(availableAmountToLend) > 0) {
            throw new LoanUnavailableException(QUOTE_UNAVAILABLE);
        }

        // Sort lenders by lowest rate first
        Collections.sort(lenders);

        BigDecimal remainingAmount = requestedAmount;
        List<Lender> selectedLenders = new ArrayList<>();

        for (Lender lender : lenders) {
            if (remainingAmount.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
            if (lender.getAvailableAmount().compareTo(remainingAmount) > 0) {
                selectedLenders.add(lender);
                remainingAmount = BigDecimal.ZERO;

            } else {
                MathContext mc = new MathContext(2);
                remainingAmount = remainingAmount.subtract(lender.getAvailableAmount(), mc);
                selectedLenders.add(lender);
            }
        }
        return selectedLenders;
    }

    /**
     * Gets the total combined loan amount from all lenders
     *
     * @return
     */
    @Override
    public BigDecimal getMaximumLoanValue() {
        return lenders.stream()
                .map(Lender::getAvailableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
