package com.gajaharan.loan.services;

import com.gajaharan.loan.exceptions.LoanUnavailableException;
import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
        assertEquals(new BigDecimal("2330"), lenderService.getMaximumLoanValue());
    }

    @Test
    public void shouldGetLenderFromLenderListTest() throws Exception {
        Lender bob = lenders.get(0);
        assertEquals("Bob", bob.getName());
        assertEquals(BigDecimal.valueOf(0.075), bob.getRate());
        assertEquals(new BigDecimal("640"), bob.getAvailableAmount());
    }

    @Test
    public void shouldReturnSelectedLendersWithLowestRatesToCoverQuoteTest() throws Exception {
        Integer requestedAmount = new Integer("1000");
        List<Lender> selectedLender = lenderService.getListOfLendersForQuote(requestedAmount);
        assertEquals(expectedLenders, selectedLender);
    }

    @Test
    public void shouldReturnListOfMemberWithLowestRateIfTheyHaveEnoughToCoverQuote() throws Exception {
        Lender jane = lenders.get(1);
        Integer requestedAmount = new Integer("100");
        List<Lender> expectedLender = Collections.singletonList(jane);
        List<Lender> requiredLender = lenderService.getListOfLendersForQuote(requestedAmount);
        assertEquals(expectedLender, requiredLender);
    }



}
