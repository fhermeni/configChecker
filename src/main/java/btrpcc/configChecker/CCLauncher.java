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

/**
 * Main class to interact with the checker from the command line.
 *
 * @author Fabien Hermenier
 */
public class CCLauncher {

    private static final String HELP_FLAG = "-h";

    private static final String VERSION_FLAG = "-v";

    private static final String VERSION = "configChecker v1.0";

    /**
     * Print the cli usage.
     */
    private static void usage() {
        System.out.println("Usage: configChecker input_files");
        System.out.println("Check the conformance of each of the given files to the configuration EBNF");
        System.out.println("Returns 0 if all of the given files are well formed");
        System.out.println("Optional flags:");
        System.out.println("\t" + VERSION_FLAG + ": print version");
        System.out.println("\t" + HELP_FLAG + ": print this help");
    }

    public static void main(String[] args) {

        boolean ret = true;
        if (args.length == 0) {
            usage();
            System.exit(1);
        }
        for (String arg : args) {
            if (arg.equals(HELP_FLAG)) {
                usage();
                System.exit(0);
            } else if (arg.equals(VERSION_FLAG)) {
                System.out.println(VERSION);
                System.exit(0);
            } else {
                //Other arguments are supposed to be files
                try {
                    ANTLRAsciiConfigChecker.getInstance().check(new File(arg));
                } catch (IOException e) {
                    System.out.println(arg + " :\n" + e.getMessage());
                    ret = false;
                } catch (ConformanceException e) {
                    System.out.println(arg + " :\n" + e.getMessage());
                    ret = false;
                }
            }
        }
        System.exit(ret ? 0 : 1);
    }
}
