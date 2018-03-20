package com.gajaharan.loan.services;

import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import com.gajaharan.loan.services.impl.LoanCalculatorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gajaharan.loan.config.GeneralConfig.TEST_RESOURCE_LOCATION;
import static org.junit.Assert.assertEquals;

/**
 * Created by gsatkunanandan on 20/03/2018.
 */
public class LoanCalculatorServiceTest {
    private List<Lender> lenders = new ArrayList<>();
    private LenderService lenderService;
    private LoanCalculatorServiceImpl quoteCalculatorService;

    @Test
    public void shouldGetAverageLoanRateFormLenderListTest() throws Exception {
        Integer requestedAmount = new Integer(1000);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedAmount, lenders);
        double expectedLoanRate = 0.07;
        double actualLoanRate = quoteCalculatorService.getAverageLoanRate();
        assertEquals(expectedLoanRate, actualLoanRate, 0.01);
    }

    @Test
    public void shouldCalculateMonthlyPaymentsBasedOnRateAndRequestedAmountTest() throws Exception {
        Integer requestedAmount = new Integer(1000);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedAmount, lenders);
        double expectedMonthlyPayment = 30.88;
        double actualMonthlyPaymentsToTwoDecimalPlaces = quoteCalculatorService.getMonthlyPayment();
        assertEquals(expectedMonthlyPayment, actualMonthlyPaymentsToTwoDecimalPlaces, 0.01);
    }

    @Test
    public void shouldCalculateTotalRepaymentFromLoanPeriodTest() throws Exception {
        Integer requestedAmount = new Integer(1000);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedAmount, lenders);
        double expectedTotalRepayment = 1111.57;
        double actualTotalRepayment = quoteCalculatorService.getTotalPayment();
        assertEquals(expectedTotalRepayment, actualTotalRepayment, 0.01);
    }

    @Test
    public void shouldReturnSingleLenderWithLowestRateTest() throws Exception {
        Integer requestedAmount = new Integer(100);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        Lender jane = lenders.get(0);
        List<Lender> expectedLender = Collections.singletonList(jane);
        List<Lender> requiredLender = this.lenderService.getListOfLendersForQuote(requestedAmount);
        assertEquals(expectedLender, requiredLender);
    }
}
