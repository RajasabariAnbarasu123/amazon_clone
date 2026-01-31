package com.example.amazonclone.exception;

public class CustomExceptions {

    // USER NOT FOUND
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    // PRODUCT NOT FOUND
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    // UNAUTHORIZED ACCESS
    public static class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }

    // PAYMENT FAILED
    public static class PaymentFailedException extends RuntimeException {
        public PaymentFailedException(String message) {
            super(message);
        }
    }
}
