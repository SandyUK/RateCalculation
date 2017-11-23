package com.zopa.service.impl;

import com.zopa.exception.InvalidOfferListException;
import com.zopa.exception.NoSufficientOfferException;
import com.zopa.model.Offer;
import com.zopa.service.BundledOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the BundledOfferService. This implementation sorts all the offers when registering them to the system.
 */
@Service
public class OrderedBundledOfferServiceImpl implements BundledOfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedBundledOfferServiceImpl.class);

    private List<Offer> registeredOffers;

    public OrderedBundledOfferServiceImpl() {
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
        validateRegisteredOffers();
    }

    /**
     * Get a bundle of offers which has the lowest rate and the minimum amount.
     *
     * @param requestAmount the requested amount
     * @return the Offers found
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    @Override
    public List<Offer> getBundledOffers(int requestAmount) {
        validateRegisteredOffers();
        return findOffersBundle(requestAmount);
    }

    /**
     * Get all offers registered in the system.
     *
     * @return the offers
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    @Override
    public List<Offer> getRegisteredOffers() {
        return this.registeredOffers;
    }

    /**
     * Sort the offers registered in the system.
     *
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    private void sortRegisteredOffers() {
        this.registeredOffers.sort((a, b) -> a.compareTo(b));
    }

    /**
     * Find a few offers to cover the request amount.
     *
     * @param requestAmount the request amount
     * @throws NoSufficientOfferException if the sum of all registered offers' available amount cannot cover the request amount
     */
    private List<Offer> findOffersBundle(int requestAmount) {
        List<Offer> result = new ArrayList<>();
        int accumulation = 0;
        for (Offer offer : this.registeredOffers) {
            accumulation += offer.getAvailable();
            result.add(offer);
            if (accumulation >= requestAmount) {
                break;
            }
        }
        if (accumulation >= requestAmount) {
            return result;
        } else {
            throw new NoSufficientOfferException();
        }
    }

    /**
     * Validate the offers registered in the system.
     *
     * @throws InvalidOfferListException if the registered offers are null or empty.
     */
    private void validateRegisteredOffers() {
        if (this.registeredOffers == null || this.registeredOffers.isEmpty()) {
            LOGGER.error("Cannot find any offer registered in the system.");
            throw new InvalidOfferListException();
        }
    }

}
