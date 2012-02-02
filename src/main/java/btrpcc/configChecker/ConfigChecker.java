package btrpcc.configChecker;

import org.antlr.runtime.*;

import java.io.File;
import java.io.IOException;

/**
 * Main class to check the conformance of a serie of files to the configuration
 * EBNF in the BtrPlace constraint catalog
 *
 * @author Fabien Hermenier
 */
public class ConfigChecker {

    private static final ConfigChecker instance = new ConfigChecker();

    private static final String HELP_FLAG = "-h";

    private static final String VERBOSE_FLAG = "-v";

    private ConfigChecker() {
    }

    public boolean check(File f) throws IOException {
        return check(new ANTLRFileStream(f.getAbsolutePath()));
    }

    public boolean check(String str) {
        return check(new ANTLRStringStream(str));
    }


    private boolean check(CharStream cs) {
        AsciiConfigLexer l = new AsciiConfigLexer(cs);
        CommonTokenStream s = new CommonTokenStream(l);
        AsciiConfigParser p = new AsciiConfigParser(s);
        try {
            p.configuration();
        } catch (RecognitionException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static ConfigChecker getInstance() {
        return instance;
    }

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
                    boolean myRet = ConfigChecker.getInstance().check(new File(arg));
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
