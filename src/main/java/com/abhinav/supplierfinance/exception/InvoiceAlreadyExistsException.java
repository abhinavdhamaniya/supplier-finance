package com.abhinav.supplierfinance.exception;

public class InvoiceAlreadyExistsException extends Exception {

    public InvoiceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
