package com.zopa.service;

import com.zopa.model.Offer;

import java.util.List;

/**
 * The contract which provides offer service to register offers and get a bundle of offers for the loan request amount.
 */
public interface BundledOfferService {

    /**
     * Register a list of Offers to the system.
     *
     * @param offerList the list of offer to be registered to the system
     */
    void registerOffers(List<Offer> offerList);

    /**
     * Get a bundle of offers from the system, the sum of those offers' available amounts covers the request amount.
     *
     * @param requestAmount the requested amount
     */
    List<Offer> getBundledOffers(int requestAmount);

    /**
     * Get all the offers registered in the system.
     */
    List<Offer> getRegisteredOffers();

}
