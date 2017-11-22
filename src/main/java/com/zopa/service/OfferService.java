package com.zopa.service;

import com.zopa.model.Offer;

import java.util.List;

/**
 * The contract which provides offer services including register offers and get lowest rate offer.
 */
public interface OfferService {

    /**
     * Register a list of Offers to the system.
     * @param offerList the list of offer to be registered to the system
     */
    void registerOffers(List<Offer> offerList);

    /**
     * Get the best offer in the system.
     * @param requestedAmount the requested amount
     */
    Offer getBestOffer(int requestedAmount);

    /**
     * Get all the offers registered in the system.
     */
    List<Offer> getRegisteredOffers();

}
