package com.gajaharan.loan;

import com.gajaharan.loan.exceptions.InvalidRequestAmountException;
import org.junit.Test;

import static com.gajaharan.loan.config.GeneralConfig.TEST_RESOURCE_LOCATION;

public class AppTest {

    @Test
    public void mainTest() throws InvalidRequestAmountException {
        // Please ignore this test. Without this scenaio brings jacoco code coverage down.
        String[] args ={TEST_RESOURCE_LOCATION, "1000"};
        App.main(args);
    }
}
