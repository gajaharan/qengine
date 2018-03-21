package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidCSVRecordException;
import com.gajaharan.loan.models.Lender;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.gajaharan.loan.config.GeneralConfig.INVALID_TEST_RESOURCE_LOCATION;
import static com.gajaharan.loan.config.GeneralConfig.TEST_RESOURCE_LOCATION;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class CSVParserTest {

    private CSVParser csvParser;

    @Before
    public void setUp() throws Exception {
        csvParser = new CSVParser();
    }

    @Test
    public void processCSVFileTest() {
        assertEquals(7,csvParser.processCSVFile(TEST_RESOURCE_LOCATION).size());
    }

    @Test
    public void processInvalidCSVFilePathTest() {
        assertNull(csvParser.processCSVFile(""));
    }

    @Test
    public void validateInvalidCSVRecordZeroLenderAmountTest() {
        List<Lender> lendersList = csvParser.processCSVFile(INVALID_TEST_RESOURCE_LOCATION);
        assertEquals(lendersList.size(), 0);
    }
}