package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import org.junit.Before;
import org.junit.Test;

import static com.gajaharan.loan.config.GeneralConfig.TEST_RESOURCE_LOCATION;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class InputValidatorTest {
    private InputValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new InputValidator();
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validateInvalidRequestBelowAmountExceptionTest() throws InvalidRequestAmountException {
        validator.isValidLoanAmount(999);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validateInvalidRequestAboveAmountExceptionTest() throws InvalidRequestAmountException {
        validator.isValidLoanAmount(15001);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validateInvalidRequestAmountNotIncrementalExceptionTest() throws InvalidRequestAmountException {
        validator.isValidLoanAmount(1111);
    }

    @Test
    public void validateCorrectRequestBelowAmountExceptionTest() throws InvalidRequestAmountException {
        assertTrue(validator.isValidLoanAmount(1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIllegalArgumentTest() throws IllegalArgumentException {
        String[] args ={TEST_RESOURCE_LOCATION};
        validator.isValidArgumentLength(args);
    }

    @Test
    public void validateLegalArgumentTest() {
        String[] args ={TEST_RESOURCE_LOCATION, "1000"};
        assertTrue(validator.isValidArgumentLength(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateLoanAmountIsNotNumericTest() throws IllegalArgumentException {
        validator.isNumeric("ABC");
    }

    @Test
    public void validateLoanAmountIsNumericTest() {
        assertTrue(validator.isNumeric("1000"));
    }
}
