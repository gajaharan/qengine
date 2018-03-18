package com.gajaharan.loan.utils;

import org.junit.Before;
import org.junit.Test;

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
        assertEquals(7,csvParser.processCSVFile("src/test/resources/MarketData.csv").size());
    }

    @Test
    public void processInvalidCSVFilePathTest() {
        assertNull(csvParser.processCSVFile(""));
    }
}