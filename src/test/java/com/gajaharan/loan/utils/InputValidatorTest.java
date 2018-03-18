package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

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
}
