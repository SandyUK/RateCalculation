package com.zopa.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zopa.model.Offer;

public class CsvReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(CsvReader.class);

	public static List<Offer> readyLendersFromCsv() {
		List<Offer> lenders = new ArrayList<>();
		File csvData = new File("/src/main/resources/Market Data for Exercise.csv");
		// CSVParser parser;
		try (CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.DEFAULT)) {
			for (CSVRecord csvRecord : parser) {
				if (parser.getCurrentLineNumber() == 1) {
					continue;
				}
				lenders.add(new Offer(csvRecord.get(0), Double.valueOf(csvRecord.get(1)), Integer.valueOf(csvRecord
						.get(2))));
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return lenders;
	}

}
