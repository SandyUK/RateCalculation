package com.zopa.service;

import com.zopa.model.Offer;

import java.util.List;

/**
 * The contract of the source file reader which can read the file.
 */
public interface SourceFileReader {

    /***
     * Read a list of Offers from the file in the given path.
     * @param path
     * @return
     */
   List<Offer> readOffers(String path);
}
