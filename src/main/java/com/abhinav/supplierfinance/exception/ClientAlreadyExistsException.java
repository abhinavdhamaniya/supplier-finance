package com.abhinav.supplierfinance.exception;

public class ClientAlreadyExistsException extends Exception {

    public ClientAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
