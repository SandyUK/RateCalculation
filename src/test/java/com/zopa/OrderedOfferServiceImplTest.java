package com.zopa;

import com.zopa.model.Offer;
import com.zopa.exception.InvalidOfferListException;
import com.zopa.service.impl.OrderedOfferServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderedOfferServiceImplTest {

    private static final String LENDER1 = "lender1";
    private static final String LENDER2 = "lender2";
    private static final String LENDER3 = "lender3";
    private static final String LENDER4 = "lender4";
    private static final double LOW_RATE = 0.7;
    private static final double MIDDIUM_RATE = 0.8;
    private static final double HIGH_RATE = 0.9;
    private static final int SMALL_AMOUNT = 10000;
    private static final int MIDDIUM_AMOUNT = 15000;
    private static final int BIG_AMOUNT = 20000;

    private OrderedOfferServiceImpl RateCalculationService;
    private List<Offer> offers;

    @Before
    public void setUp() {
        this.RateCalculationService = new OrderedOfferServiceImpl();
        this.offers = new ArrayList<>();
    }


    @Test
    public void testGetOfferWithLowestRate() {
        prepareOfferList(new Offer(LENDER1, LOW_RATE, SMALL_AMOUNT), new Offer(LENDER2, MIDDIUM_RATE, SMALL_AMOUNT));
        this.RateCalculationService.registerOffers(this.offers);
        Offer result = this.RateCalculationService.getBestOffer(5000);
        Assert.assertEquals(LENDER1, result.getName());
    }

    @Test
    public void testGetOfferWithLowestRate_SameRateDifferentAmount() {
        prepareOfferList(new Offer(LENDER1, LOW_RATE, MIDDIUM_AMOUNT), new Offer(LENDER2, LOW_RATE, BIG_AMOUNT));
        this.RateCalculationService.registerOffers(this.offers);
        Offer result = this.RateCalculationService.getBestOffer(5000);
        Assert.assertEquals(LENDER1, result.getName());
    }

    @Test
    public void testSortRegisteredOffers() {
        prepareOfferList(new Offer(LENDER1, LOW_RATE, SMALL_AMOUNT), new Offer(LENDER2, MIDDIUM_RATE, SMALL_AMOUNT),
                         new Offer(LENDER3, MIDDIUM_RATE, BIG_AMOUNT), new Offer(LENDER4, HIGH_RATE, MIDDIUM_AMOUNT));
        this.RateCalculationService.registerOffers(this.offers);
        List<Offer> registedOffers = this.RateCalculationService.getRegisteredOffers();
        Assert.assertEquals(LENDER1, registedOffers.get(0).getName());
        Assert.assertEquals(LENDER2, registedOffers.get(1).getName());
        Assert.assertEquals(LENDER3, registedOffers.get(2).getName());
        Assert.assertEquals(LENDER4, registedOffers.get(3).getName());
    }

    @Test(expected = InvalidOfferListException.class)
    public void testGetOfferWithLowestRate_OfferListAsNull() {
        this.RateCalculationService.getBestOffer(5000);
    }

    @Test(expected = InvalidOfferListException.class)
    public void testGetOfferWithLowestRate_EmptyOfferList() {
        this.RateCalculationService.getBestOffer(5000);
    }

    private void prepareOfferList(Offer... offers) {
        Arrays.stream(offers).forEach(offer -> {
            this.offers.add(offer);
        });
    }
}
