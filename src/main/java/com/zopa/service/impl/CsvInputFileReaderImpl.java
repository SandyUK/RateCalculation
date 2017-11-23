package com.zopa.service.impl;

import com.zopa.model.Offer;
import com.zopa.service.InputFileReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvInputFileReaderImpl implements InputFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvInputFileReaderImpl.class);

    /**
     * Read a list of Offers from the CSV file in the given path.
     * @param path the path to the CSV file
     * @return the list of offers read from the given file
     */
    @Override
    public List<Offer> readOffers(String path) {
        List<Offer> offers = new ArrayList<>();
        File csvFile = new File(path);
        // CSVParser parser;
        try (CSVParser parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.DEFAULT)) {
           offers = parser.getRecords().stream().skip(1).map(csvRecord -> {
                return new Offer(csvRecord.get(0), Double.valueOf(csvRecord.get(1)), Integer.valueOf(csvRecord.get(2)));
            }).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return offers;
    }


}
