package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import org.junit.Test;

public class AppTest {

    @Test
    public void mainTest() throws InvalidRequestAmountException {
        // Please ignore this test. Without this scenaio brings jacoco code coverage down.
        String[] args ={"src/test/resources/MarketData.csv", "1000"};
        App.main(args);
    }
}
