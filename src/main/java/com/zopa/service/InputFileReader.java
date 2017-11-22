package com.zopa.service;

import com.zopa.model.Offer;

import java.util.List;

/**
 * The contract which provides file reader functions.
 */
public interface InputFileReader {

    /***
     * Read a list of Offers from the file in the given path.
     * @param path
     * @return
     */
   List<Offer> readOffers(String path);
}
