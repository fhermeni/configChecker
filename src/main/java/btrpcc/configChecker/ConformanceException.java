package btrpcc.configChecker;

/**
 * Exception related to a non well-formed configuration.
 *
 * @author Fabien Hermenier
 */
public class ConformanceException extends Exception {

    /**
     * New exception.
     * @param message the error message to report
     */
    public ConformanceException(String message) {
        super(message);
    }
}
