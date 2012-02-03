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

    private static final String VERBOSE_FLAG = "-v";

    private static void usage() {
        System.out.println("Usage: configChecker input_files");
        System.out.println("Check the conformance of each of the given files to the configuration EBNF");
        System.out.println("By default, the program returns 0 if all of the given files are well formed");
        System.out.println("Flags:");
        System.out.println("-v: verbose mode");
        System.out.println("-h: print this help");
    }

    public static void main(String[] args) {

        boolean verbose = false;
        boolean ret = true;
        if (args.length == 0) {
            usage();
            System.exit(1);
        }
        for (String arg : args) {
            if (arg.equals(HELP_FLAG)) {
                usage();
                System.exit(0);
            } else if (arg.equals(VERBOSE_FLAG)) {
                verbose = true;
            } else { //Supposed to be a file
                try {
                    boolean myRet = ANTLRAsciiConfigChecker.getInstance().check(new File(arg));
                    ret &= myRet;
                    if (verbose) {
                        System.out.println(arg + " : " + (myRet ? "valid" : "invalid"));
                    }
                } catch (IOException e) {
                    System.out.println(arg + " : " + e.getMessage());
                }
            }
        }
        System.exit(ret ? 0 : 1);
    }
}
