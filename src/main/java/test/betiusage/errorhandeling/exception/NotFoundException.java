package test.betiusage.errorhandeling.exception;

public class NotFoundException extends RuntimeException {
    /**
     * This constructor creates a new NotFoundException with a message.
     */
    public NotFoundException(String message) {
        super("NotFound error: " + message);
    }
}