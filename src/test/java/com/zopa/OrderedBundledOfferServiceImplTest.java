package com.zopa;

import com.zopa.exception.InvalidOfferListException;
import com.zopa.model.Offer;
import com.zopa.service.impl.OrderedBundledOfferServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderedBundledOfferServiceImplTest {

    private static final String LENDER1 = "lender1";
    private static final String LENDER2 = "lender2";
    private static final String LENDER3 = "lender3";
    private static final String LENDER4 = "lender4";
    private static final double LOW_RATE = 0.7;
    private static final double MEDIUM_RATE = 0.8;
    private static final double HIGH_RATE = 0.9;
    private static final int SMALL_OFFER_AMOUNT = 10000;
    private static final int BIG_OFFER_AMOUNT = 20000;
    private static final int SMALL_REQUEST_AMOUNT = 5000;
    private static final int BIG_REQUEST_AMOUNT = 20000;
    private final Offer OFFER1 = new Offer(LENDER1, LOW_RATE, SMALL_OFFER_AMOUNT);
    private final Offer OFFER2 = new Offer(LENDER2, MEDIUM_RATE, SMALL_OFFER_AMOUNT);
    private final Offer OFFER3 = new Offer(LENDER3, MEDIUM_RATE, BIG_OFFER_AMOUNT);
    private final Offer OFFER4 = new Offer(LENDER4, HIGH_RATE, BIG_OFFER_AMOUNT);

    private OrderedBundledOfferServiceImpl RateCalculationService;
    private List<Offer> offers;

    @Before
    public void setUp() {
        this.RateCalculationService = new OrderedBundledOfferServiceImpl();
        this.offers = new ArrayList<>();
    }

    @Test
    public void testGetRegisteredOffers() {
        prepareOfferList(OFFER1,OFFER2,OFFER3,OFFER4);
        this.RateCalculationService.registerOffers(this.offers);
        List<Offer> registeredOffers = this.RateCalculationService.getRegisteredOffers();
        Assert.assertEquals(LENDER1, registeredOffers.get(0).getName());
        Assert.assertEquals(LENDER2, registeredOffers.get(1).getName());
        Assert.assertEquals(LENDER3, registeredOffers.get(2).getName());
        Assert.assertEquals(LENDER4, registeredOffers.get(3).getName());
    }

    @Test
    public void testGetBundledOffer_OneOffer_LowestRate() {
        prepareOfferList(OFFER3,OFFER4);
        this.RateCalculationService.registerOffers(this.offers);
        List<Offer> result = this.RateCalculationService.getBundledOffers(BIG_REQUEST_AMOUNT);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(LENDER3, result.get(0).getName());
    }

    @Test
    public void testGetBundledOffer_OneOffer_MinimunOfferAmount() {
        prepareOfferList(OFFER2,OFFER3);
        this.RateCalculationService.registerOffers(this.offers);
        List<Offer> result = this.RateCalculationService.getBundledOffers(SMALL_REQUEST_AMOUNT);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(LENDER2, result.get(0).getName());
    }

    @Test
    public void testGetBundledOffer_MultipleOffers() {
        prepareOfferList(OFFER1,OFFER2,OFFER3,OFFER4);
        this.RateCalculationService.registerOffers(this.offers);
        List<Offer> result = this.RateCalculationService.getBundledOffers(BIG_REQUEST_AMOUNT);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(LENDER1, result.get(0).getName());
        Assert.assertEquals(LENDER2, result.get(1).getName());
    }

    @Test(expected = InvalidOfferListException.class)
    public void testGetOfferWithLowestRate_OfferListAsNull() {
        this.RateCalculationService.getBundledOffers(SMALL_REQUEST_AMOUNT);
    }

    @Test(expected = InvalidOfferListException.class)
    public void testGetOfferWithLowestRate_EmptyOfferList() {
        this.RateCalculationService.getBundledOffers(SMALL_REQUEST_AMOUNT);
    }


    private void prepareOfferList(Offer... offers) {
        Arrays.stream(offers).forEach(offer -> {
            this.offers.add(offer);
        });
    }
}
