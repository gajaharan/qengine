package com.gajaharan.loan.services;

import com.gajaharan.loan.models.Lender;
import com.gajaharan.loan.services.impl.LenderServiceImpl;
import com.gajaharan.loan.services.impl.LoanCalculatorServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal expectedLoanRate = BigDecimal.valueOf(0.07);
        BigDecimal actualLoanRate = quoteCalculatorService.getAverageLoanRate().setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expectedLoanRate, actualLoanRate);
    }

    @Test
    public void shouldCalculateMonthlyPaymentsBasedOnRateAndRequestedAmountTest() throws Exception {
        Integer requestedAmount = new Integer(1000);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedAmount, lenders);
        BigDecimal expectedMonthlyPayment = BigDecimal.valueOf(30.88);
        BigDecimal actualMonthlyPaymentsToTwoDecimalPlaces = quoteCalculatorService.getMonthlyPayment();
        assertEquals(expectedMonthlyPayment, actualMonthlyPaymentsToTwoDecimalPlaces);
    }

    @Test
    public void shouldCalculateTotalRepaymentFromLoanPeriodTest() throws Exception {
        Integer requestedAmount = new Integer(1000);
        this.lenderService = new LenderServiceImpl(TEST_RESOURCE_LOCATION);
        this.lenders = this.lenderService.getListOfLendersForQuote(requestedAmount);
        this.quoteCalculatorService = new LoanCalculatorServiceImpl(requestedAmount, lenders);
        BigDecimal expectedTotalRepayment = BigDecimal.valueOf(1111.68);
        BigDecimal actualTotalRepayment = quoteCalculatorService.getTotalPayment();
        assertEquals(expectedTotalRepayment, actualTotalRepayment);
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
