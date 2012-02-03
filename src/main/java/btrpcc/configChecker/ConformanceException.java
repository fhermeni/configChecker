package btrpcc.configChecker;

/**
 * Exception related to a non well-formed configuration.
 *
 * @author Fabien Hermenier
 */
public class ConformanceException extends Exception {

    public ConformanceException(String message) {
        super(message);
    }
}
