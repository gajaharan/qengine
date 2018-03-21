package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidCSVRecordException;

import java.math.BigDecimal;

/**
 * Created by gsatkunanandan on 21/03/2018.
 */
public class CSVValidator {

    public void validateName(String name) throws InvalidCSVRecordException {
        if (null == name || name.trim().length() == 0) {
            throw new InvalidCSVRecordException("Invalid CSV file. No lender name");
        }
    }

    public void validateRate(String rate) throws InvalidCSVRecordException {
        if (null == rate || rate.trim().length() == 0) {
            throw new InvalidCSVRecordException("Invalid CSV file. No lender rate");
        }

        if (isMoreThanOrEqualTo(new BigDecimal(rate), BigDecimal.ONE) || isLessThanOrEqualTo(new BigDecimal(rate), BigDecimal.ZERO)) {
            throw new InvalidCSVRecordException("Lender has a rate of more than 100% or less than 0%");
        }
    }


    public void validateAmount(String amount) throws InvalidCSVRecordException  {
        if (null == amount || amount.trim().length() == 0) {
            throw new InvalidCSVRecordException("Invalid CSV file. No lender amount");
        }

        if (isLessThanOrEqualTo(new BigDecimal(amount), BigDecimal.ZERO)) {
            throw new InvalidCSVRecordException("Lender had 0 or less amount");
        }
    }

    private Boolean isMoreThanOrEqualTo(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) >= 0;
    }

    private Boolean isLessThanOrEqualTo(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) <= 0;
    }
}
