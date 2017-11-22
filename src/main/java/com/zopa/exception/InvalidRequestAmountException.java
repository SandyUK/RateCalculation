package com.zopa.exception;

public class InvalidRequestAmountException extends RuntimeException {
    public InvalidRequestAmountException(String message){
        super(message);
    }
}
