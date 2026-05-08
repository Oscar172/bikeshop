package se.kth.iv1350.integration.exceptions;

/**
 * Thrown when the search for a secific customer fails because
 * the provided phone number does not exist in the customer regisrty.
 * This is a checked exception as it represents a predictable error that
 * the application is expected to handle.
 */
public class UserNotFoundException extends Exception {
    /**
     * Creates a new instance with a message that describe the error.
     * @param message A message explaining what went wrong.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
