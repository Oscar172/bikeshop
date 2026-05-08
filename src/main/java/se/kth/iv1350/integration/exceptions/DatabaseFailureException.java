package se.kth.iv1350.integration.exceptions;

/**
 * Thrown when a database operation fails because of technical issues,
 * for instance a lost connection.
 * This is an unchecked exception, it represents an unpredictable system error
 * that is not expected to be handled in the basic flow of the application.
 */
public class DatabaseFailureException extends RuntimeException {
    /**
     * Creates a new instance with a message that describe the error.
     * @param message A message explaining what went wrong.
     */
    public DatabaseFailureException (String message) {
        super(message);
    }
}
