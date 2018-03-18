package com.gajaharan.loan.utils;

import com.gajaharan.loan.models.Lender;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class CSVParser {
    private static final Logger LOGGER = Logger.getLogger(CSVParser.class.getName());

    public List<Lender> processCSVFile(String csvFilePath) {

        List<Lender> lendersList = null;
        BufferedReader reader = null;
        try {
            File file = new File(csvFilePath);
            InputStream inputStream = null;

            inputStream = new FileInputStream(file);

            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Skip csv header
            lendersList = reader.lines().skip(1).map(mapToLender).collect(Collectors.toList());

            reader.close();

        } catch (FileNotFoundException fnfe) {
            LOGGER.log(Level.SEVERE, "File not found: " + csvFilePath, fnfe.getMessage());
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Unable to close BufferedReader", ioe.getMessage());
        }

        return lendersList;
    }

    private Function<String, Lender> mapToLender = (String line) -> {
        String[] details = line.split(",");

        String name = details[0];
        double rate = new Double(details[1]);
        Integer availableAmount = new Integer(details[2]);

        return new Lender(name, rate, availableAmount);
    };
}
