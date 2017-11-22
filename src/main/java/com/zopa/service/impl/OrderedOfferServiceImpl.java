package com.zopa.service.impl;

import com.zopa.exception.NoSufficientOfferException;
import com.zopa.model.Offer;
import com.zopa.service.OfferService;
import com.zopa.exception.InvalidOfferListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedOfferServiceImpl implements OfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedOfferServiceImpl.class);

    private List<Offer> registeredOffers;

    public OrderedOfferServiceImpl() {
        this.registeredOffers = new ArrayList<>();
    }

    /**
     * Register the given offers and sort them.
     *
     * @param offers the offers to be registered
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    @Override
    public void registerOffers(List<Offer> offers) {
        this.registeredOffers = offers;
        sortRegisteredOffers();
    }

    /**
     * Get the best offer which has the lowest rate and the minimum amount.
     *
     * @param requestedAmount the requested amount
     * @return the Offer found
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    @Override
    public Offer getBestOffer(int requestedAmount) {
        validateRegistedOffers();
        return this.registeredOffers.stream().filter(offer -> offer.getAvailable() >= requestedAmount).findFirst().orElseThrow(() -> new NoSufficientOfferException());
    }

    /**
     * Get all offers registered in the system.
     *
     * @return the offers
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    @Override
    public List<Offer> getRegisteredOffers() {
        validateRegistedOffers();
        return this.registeredOffers;
    }

    /**
     * Sort the offers registered in the system.
     *
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    private void sortRegisteredOffers() {
        validateRegistedOffers();
        this.registeredOffers.sort((a, b) -> a.compareTo(b));
    }

    /**
     * Validate the offers registered in the system.
     *
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    private void validateRegistedOffers() {
        if (this.registeredOffers == null || this.registeredOffers.isEmpty()) {
            LOGGER.error("Cannot find any offer registered in the system.");
            throw new InvalidOfferListException();
        }
    }
}
