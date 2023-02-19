package com.abhinav.supplierfinance.exception;

public class InvoiceNotExistsException extends Exception {

    public InvoiceNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}
