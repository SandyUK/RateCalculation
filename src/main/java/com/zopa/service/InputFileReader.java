package com.zopa.service;

import com.zopa.model.Offer;

import java.util.List;

/**
 * The contract which provides file reader functions.
 */
public interface InputFileReader {

    /***
     * Read a list of Offers from the input file in the given path.
     * @param path the path to the input file
     * @return the list offers read from the file
     */
   List<Offer> readOffersFromFile(String path);
}
