package com.zopa;

import com.zopa.exception.InvalidParameterNumberException;
import com.zopa.exception.InvalidRequestAmountException;
import com.zopa.model.Offer;
import com.zopa.model.Quote;
import com.zopa.service.InputFileReader;
import com.zopa.service.LoanService;
import com.zopa.service.OfferService;
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
 * <li>The system does not bundle multiple lenders' offers together to match
 * requested loan. In other words, the loan is provided by only one offer.</li>
 * <li>If a lender's offer is more than the requested amount of the loan, the
 * system will give the requested amount rather than the whole offer.</li>
 * <ul>
 */
@SpringBootApplication(scanBasePackages = "com.zopa.service")
public class Application implements CommandLineRunner {

    @Autowired
    public OfferService offerService;

    @Autowired
    public InputFileReader sourceFileReader;

    @Autowired
    public LoanService loanService;

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        validateParameterNumber(args);
        String path = args[0];
        int requestAmount = parseRequestAmount(args[1]);
        List<Offer> offers = this.sourceFileReader.readOffersFromFile(path);
        this.offerService.registerOffers(offers);
        Offer bestOffer = this.offerService.getBestOffer(requestAmount);
        Quote quote = this.loanService.generateQuote(bestOffer, requestAmount);
        showQuote(quote);

    }

    private void validateParameterNumber(String... args) {
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

    private void showQuote(Quote quote) {
        System.out.println("Requested amount: £" + quote.getRequestedAmount());
        System.out.println("Rate: " + quote.getRate() * 100 + "%");
        System.out.println("Monthly repayment: £" + quote.getMonthlyPayment());
        System.out.println("Total repayment: £" + quote.getTotalPayment());

    }

}