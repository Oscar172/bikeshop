package se.kth.iv1350.integration.exceptions;

public class DatabaseFailureException extends RuntimeException {
    public DatabaseFailureException (String message) {
        super(message);
    }
}
