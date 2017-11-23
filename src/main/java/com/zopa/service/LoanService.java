package com.zopa.service;

import com.zopa.model.Offer;
import com.zopa.model.Quote;

import java.util.List;

/**
 * A contract provides loan service including generate quote.
 */
public interface LoanService {

   Quote generateQuote(Offer offer, int requestAmount);
}
