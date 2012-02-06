package btrpcc.configChecker;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Interface for a text-based configuration conformance checker.
 *
 * @author Fabien Hermenier
 */
public interface AsciiConfigChecker {

    /**
     * Check whether the file is well-formed or not.
     *
     * @param f the file to check
     * @throws IOException          if an error occurred while reading the file.
     * @throws ConformanceException if the configuration is not well-formed
     */
    void check(File f) throws IOException, ConformanceException;

    /**
     * Check whether the string is well-formed or not.
     *
     * @param str the str to check
     * @throws ConformanceException if the configuration is not well-formed
     */
    void check(String str) throws ConformanceException;

    /**
     * Check whether the stream content is well-formed or not.
     *
     * @param r the stream to check
     * @throws IOException          if an error occurred while reading the stream.
     * @throws ConformanceException if the configuration is not well-formed
     */
    void check(Reader r) throws ConformanceException, IOException;
}
