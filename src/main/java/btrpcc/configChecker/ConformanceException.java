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

/**
 * Exception related to a non well-formed configuration.
 *
 * @author Fabien Hermenier
 */
public class ConformanceException extends Exception {

    /**
     * New exception.
     *
     * @param message the error message to report
     */
    public ConformanceException(String message) {
        super(message);
    }
}
