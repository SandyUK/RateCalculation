package com.zopa.exception;

public class NoSufficientOfferException extends RuntimeException{

    public NoSufficientOfferException(){
        super("Cannot find any offer matching the request amount.");
    }
}
