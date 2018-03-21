package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidCSVRecordException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gsatkunanandan on 21/03/2018.
 */
public class CSVValidatorTest {
    private CSVValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new CSVValidator();
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderNameTest() throws InvalidCSVRecordException {
        validator.validateName("");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderRateTest() throws InvalidCSVRecordException {
        validator.validateRate("");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderRateWith1percentTest() throws InvalidCSVRecordException {
        validator.validateRate("1");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderRateWithnegative1PercentTest() throws InvalidCSVRecordException {
        validator.validateRate("-1");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderRateWith101PercentTest() throws InvalidCSVRecordException {
        validator.validateRate("101");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordLenderAmountTest() throws InvalidCSVRecordException {
        validator.validateAmount("");
    }

    @Test(expected = InvalidCSVRecordException.class)
    public void validateInvalidCSVRecordZeroLenderAmountTest() throws InvalidCSVRecordException {
        validator.validateAmount("0");
    }
}
