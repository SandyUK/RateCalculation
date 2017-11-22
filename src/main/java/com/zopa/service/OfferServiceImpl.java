package com.zopa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zopa.model.Offer;

@Service
public class OfferServiceImpl implements OfferService {
	List<Offer> offers;

	public OfferServiceImpl() {
		// this.lenders = CsvReader.readyLendersFromCsv();
		this.offers = new ArrayList<Offer>();
	}

	public Offer getOfferWithLowestRate(List<Offer> offers, int amount) {
		return offers.stream().filter(offer -> offer.getAvailable() >= amount).findFirst().get();
	}

	public void sortOffers(List<Offer> offers) {
		offers.sort((a, b) -> a.compareTo(b));
	}

}
