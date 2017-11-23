package com.zopa.service.impl;

import com.zopa.model.Offer;
import com.zopa.model.Quote;
import com.zopa.service.LoanService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The implementation of the Loan Service. This implementation can generate the quote for a 36 month loan based on the monthly compounding interests.
 */
@Service
public class MonthlyCompoundingInterestLoanServiceImpl implements LoanService {

    private static final int COMPOUDNING_TIMES = 36;
    private static final int MONTHS_PER_YEAR = 12;

    /**
     * Generate a loan quote from the given offer for the request amount. The quote is 36 months and use monthly compounding interest.
     *
     * @param offer the offer providing the loan.
     * @param requestAmount the request amount of the loan
     * @return the quote generated
     */
    @Override
    public Quote generateQuote(Offer offer, int requestAmount) {
        Quote result = new Quote();
        double compoundRate = Math.pow(offer.getRate() / MONTHS_PER_YEAR + 1, COMPOUDNING_TIMES);
        double totalPayment = round(requestAmount * compoundRate, 2);
        double monthlyPayment = round(totalPayment / MONTHS_PER_YEAR, 2);
        result.setRequestedAmount(requestAmount);
        result.setRate(offer.getRate());
        result.setTotalPayment(totalPayment);
        result.setMonthlyPayment(monthlyPayment);
        return result;
    }

    /**
     * Round a double to the given number of decimal.
     * @param value the value to be rounded.
     * @param places the number of decimal
     * @return a double value with given number of decimal
     */
    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
