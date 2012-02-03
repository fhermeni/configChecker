package btrpcc.configChecker;

import org.antlr.runtime.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * ANTLR based checher for conformance.
 *
 * @author Fabien Hermenier
 */
public class ANTLRAsciiConfigChecker implements AsciiConfigChecker {

    private static final AsciiConfigChecker instance = new ANTLRAsciiConfigChecker();

    /**
     * Singleton, use {@link #getInstance()}.
     */
    private ANTLRAsciiConfigChecker() {
    }

    @Override
    public boolean check(File f) throws IOException {
        return check(new ANTLRFileStream(f.getAbsolutePath()));
    }

    @Override
    public boolean check(String str) {
        return check(new ANTLRStringStream(str));
    }

    @Override
    public boolean check(Reader r) throws IOException {
        return check(new ANTLRReaderStream(r));
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

    /**
     * Get the unique instance for the checker.
     *
     * @return the checker instance.
     */
    public static AsciiConfigChecker getInstance() {
        return instance;
    }
}
