package com.zopa;

import com.zopa.exception.InvalidParameterNumberException;
import com.zopa.exception.InvalidRequestAmountException;
import com.zopa.model.Offer;
import com.zopa.service.BundledOfferService;
import com.zopa.service.InputFileReader;
import com.zopa.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

/**
 * The entry point of the Rate-Calculation-System.
 * <p>
 * Assumption:
 * <ul>
 * <li>The system can bundle multiple lenders' offers together to match
 * requested amount.</li>
 * <li>If a lender's offer is more than the requested amount of the loan, the
 * system will give the requested amount rather than the whole offer.</li>
 * <ul>
 */
@SpringBootApplication(scanBasePackages = "com.zopa.service")
public class Application implements CommandLineRunner {

    @Autowired
    public InputFileReader sourceFileReader;


    @Autowired
    public BundledOfferService offerService;

    @Autowired
    public QuoteService quoteService;

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        validateParameterNumber(args);
        String path = args[0];
        int requestAmount = parseRequestAmount(args[1]);
        List<Offer> offersFromFile = sourceFileReader.readOffers(path);
        offerService.registerOffers(offersFromFile);
        List<Offer> offerBundle = offerService.getBundledOffers(requestAmount);

    }

    private void validateParameterNumber(String... args){
        if (args.length != 2) {
            throw new InvalidParameterNumberException("There must be 2 parameters: [path_to_input_file] and [request_amount]");
        }
    }

    private int parseRequestAmount(String arg) {
        try {
            int requestAmount = Integer.valueOf(arg);
            if (requestAmount < 1000 || requestAmount > 15000 || requestAmount % 100 != 0) {
                throw new InvalidRequestAmountException(
                        "The request amount should be between £100 increment between £1000 and £15000 inclusive.");
            }
            return requestAmount;
        } catch (ClassCastException e) {
            throw new InvalidRequestAmountException("The request amount must be an integer.");
        }
    }

}