package btrpcc.configChecker;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Interface for a text-based configuration checked.
 *
 * @author Fabien Hermenier
 */
public interface AsciiConfigChecker {

    /**
     * Check whether the file is well-formed or not.
     *
     * @param f the file to check
     * @return {@code true} if the file' content is well-formed. {@code false} otherwise
     * @throws IOException if an error occurred while reading the file.
     */
    boolean check(File f) throws IOException;

    /**
     * Check whether the string is well-formed or not.
     *
     * @param str the str to check
     * @return {@code true} if the string is well-formed. {@code false} otherwise
     */
    boolean check(String str);

    /**
     * Check whether the stream content is well-formed or not.
     *
     * @param r the stream to check
     * @return {@code true} if the stream content is well-formed. {@code false} otherwise
     * @throws IOException if an error occurred while reading the stream.
     */
    boolean check(Reader r) throws IOException;
}
