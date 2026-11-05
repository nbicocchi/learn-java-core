package com.nbicocchi.addressbook.client;

public class RestClientException extends RuntimeException {
    private final int statusCode;

    /** A zero status means no usable HTTP response (network, timeout, or interruption). */
    public RestClientException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
