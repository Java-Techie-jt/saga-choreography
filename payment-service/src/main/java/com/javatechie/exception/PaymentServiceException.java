package com.javatechie.exception;

public class PaymentServiceException extends RuntimeException{

    public PaymentServiceException(String message) {
        super(message);
    }
}
