package com.gajaharan.loan.utils;

import com.gajaharan.loan.exceptions.InvalidCSVRecordException;
import com.gajaharan.loan.models.Lender;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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
    private CSVValidator csvValidator = new CSVValidator();
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
            lendersList = reader.lines()
                    .skip(1)
                    .map(mapToLender)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

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

        try {
            if (validateCSVRecord(details)) {
                String name = details[0];
                BigDecimal rate = new BigDecimal(details[1]);
                BigDecimal availableAmount = new BigDecimal(details[2]);
                return new Lender(name, rate, availableAmount);
            }
        } catch (InvalidCSVRecordException icre) {
            LOGGER.log(Level.SEVERE, "Error with CSV format", icre.getMessage());
        }

        return null;
    };

    private boolean validateCSVRecord(String[] details) throws InvalidCSVRecordException {
        csvValidator.validateName(details[0]);
        csvValidator.validateRate(details[1]);
        csvValidator.validateAmount(details[2]);
        return true;
    }
}
