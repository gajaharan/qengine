package com.gajaharan.loan.services;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.Lender;

import java.util.List;

/**
 * Created by gsatkunanandan on 19/03/2018.
 */
public interface LenderService {
    List<Lender> getLenders();
    List<Lender> getListOfLendersForQuote(Integer requestedAmount) throws LoanUnavailableException;
    double getMaximumLoanValue();
}
