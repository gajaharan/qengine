package com.gajaharan.loan.utils;

import com.gajaharan.loan.models.Lender;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.gajaharan.loan.config.GeneralConfig.DELIMITER;

/**
 * Created by gsatkunanandan on 18/03/2018.
 */
public class CSVParser {
    private static final Logger LOGGER = Logger.getLogger(CSVParser.class.getName());

    /**
     * Parses CSV file in a particular format of lender_name, lender_rate, lender_amount
     *
     * @param csvFilePath
     * @return
     */
    public List<Lender> processCSVFile(String csvFilePath) {

        List<Lender> lendersList = null;
        BufferedReader reader;
        try {
            File file = new File(csvFilePath);
            InputStream inputStream;

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
        String[] details = line.split(DELIMITER);

        String name = details[0];
        double rate = new Double(details[1]);
        Integer availableAmount = new Integer(details[2]);

        return new Lender(name, rate, availableAmount);
    };
}
