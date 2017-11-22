package com.zopa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zopa.model.Offer;
import com.zopa.service.OfferServiceImpl;

public class RateCalculationServiceTest {

	private OfferServiceImpl RateCalculationService;
	private Offer offer1 = new Offer("lender1", 0.7, 10000);
	private Offer offer2 = new Offer("lender2", 0.8, 10000);
	private Offer offer3 = new Offer("lender3", 0.8, 5000);
	private Offer offer4 = new Offer("lender4", 0.9, 20000);

	@Before
	public void setUp() {
		this.RateCalculationService = new OfferServiceImpl();

	}

	@Test
	public void testSortLenders() {

		List<Offer> offers = new ArrayList<>();
		offers.add(offer2);
		offers.add(offer1);
		offers.add(offer4);
		offers.add(offer3);

		this.RateCalculationService.sortOffers(offers);

		Assert.assertEquals("lender1", offers.get(0).getName());
		Assert.assertEquals("lender2", offers.get(1).getName());
		Assert.assertEquals("lender3", offers.get(2).getName());
		Assert.assertEquals("lender4", offers.get(3).getName());
	}

	@Test
	public void testGetOfferWithLowestRate() {

		List<Offer> offers = new ArrayList<>();
		offers.add(offer2);
		offers.add(offer1);
		offers.add(offer4);
		offers.add(offer3);

		this.RateCalculationService.sortOffers(offers);
		Offer result = this.RateCalculationService.getOfferWithLowestRate(offers, 4000);
		Assert.assertEquals("lender4", result.getName());
	}
}
