package com.gajaharan.loan.services;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gajaharan.loan.config.GeneralConfig.TEST_RESOURCE_LOCATION;
import static org.junit.Assert.assertEquals;

/**
 * Created by gsatkunanandan on 19/03/2018.
 */
public class LenderServiceTest {
    private LenderService lenderService;
    private List<Lender> lenders;
    private List<Lender> expectedLenders = new ArrayList<>();

    @Before
    public void setUp() {
        lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        lenders = lenderService.getLenders();

        expectedLenders.add(lenders.get(1));
        expectedLenders.add(lenders.get(2));
    }

    @Test (expected = LoanUnavailableException.class)
    public void shouldThrowLoanUnavailableExceptionIfAmountGreaterThanMaxLenderAmountTest() throws LoanUnavailableException {
        Integer requestedAmount = new Integer("2331");
        lenderService.getListOfLendersForQuote(requestedAmount);
    }

    @Test
    public void shouldGetMaximumLoanForLendersTest() throws Exception {
        assertEquals(2330.00, lenderService.getMaximumLoanValue(),0.001);
    }

    @Test
    public void shouldGetLenderFromLenderListTest() throws Exception {
        Lender bob = lenders.get(0);
        assertEquals("Bob", bob.getName());
        assertEquals(0.075, bob.getRate(),0.001);
        assertEquals(640, bob.getAvailableAmount(),1);
    }

    @Test
    public void shouldReturnSelectedLendersWithLowestRatesToCoverQuoteTest() throws Exception {
        Integer requestedAmount = new Integer("1000");
        List<Lender> selectedLender = lenderService.getListOfLendersForQuote(requestedAmount);
        assertEquals(expectedLenders, selectedLender);
    }
}
