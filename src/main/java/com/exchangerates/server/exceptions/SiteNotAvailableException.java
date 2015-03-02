package com.exchangerates.server.exceptions;


public class SiteNotAvailableException extends Exception {
    public SiteNotAvailableException() {
        super();
    }

    public SiteNotAvailableException(String message) {
        super(message);
    }
}
