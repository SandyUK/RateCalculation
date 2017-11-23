package com.zopa;

import com.zopa.model.Offer;
import com.zopa.model.Quote;
import com.zopa.service.impl.QuoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuoteServiceImplTest {
    private static final String LENDER1 = "lender1";
    private static final String LENDER2 = "lender2";
    private static final String LENDER3 = "lender3";
    private static final String LENDER4 = "lender4";
    private static final double LOW_RATE = 0.07;
    private static final double MEDIUM_RATE = 0.08;
    private static final double HIGH_RATE = 0.09;
    private static final int SMALL_OFFER_AMOUNT = 10000;
    private static final int BIG_OFFER_AMOUNT = 20000;
    private static final int SMALL_REQUEST_AMOUNT = 5000;
    private static final int BIG_REQUEST_AMOUNT = 20000;
    private final Offer OFFER1 = new Offer(LENDER1, LOW_RATE, SMALL_OFFER_AMOUNT);
    private final Offer OFFER2 = new Offer(LENDER2, MEDIUM_RATE, SMALL_OFFER_AMOUNT);
    private final Offer OFFER3 = new Offer(LENDER3, MEDIUM_RATE, BIG_OFFER_AMOUNT);
    private final Offer OFFER4 = new Offer(LENDER4, HIGH_RATE, BIG_OFFER_AMOUNT);

    private QuoteServiceImpl quoteService;
    private List<Offer> offers;

    @Before
    public void setUp() {
        this.quoteService = new QuoteServiceImpl();
        this.offers = new ArrayList<>();
    }

    @Test
    public void testGenerateQuote(){
        this.offers.add(OFFER1);
        Quote quote = this.quoteService.generateQuote(this.offers, SMALL_REQUEST_AMOUNT);
        Assert.assertNotNull(quote);
        Assert.assertEquals(SMALL_REQUEST_AMOUNT,quote.getRequestedAmount());
        Assert.assertNotEquals(SMALL_REQUEST_AMOUNT,quote.getTotalPayment());
    }
}
