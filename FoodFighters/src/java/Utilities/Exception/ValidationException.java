package Utilities.Exception;

/**
 * Custom exception class for validation errors.
 */
public class ValidationException extends Exception {
    
    /**
     * Constructs a new ValidationException with the specified detail message.
     * @param message the detail message.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ValidationException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}