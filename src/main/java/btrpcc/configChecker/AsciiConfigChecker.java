/*
 * Copyright (c) INRIA 2012
 * This file is part of configChecker.
 *
 *     configChecker is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ConfigChecker is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>
 */

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
