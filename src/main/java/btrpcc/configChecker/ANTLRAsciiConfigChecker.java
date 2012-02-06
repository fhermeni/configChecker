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

import org.antlr.runtime.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
 * ANTLR based checker for conformance.
 *
 * @author Fabien Hermenier
 */
public class ANTLRAsciiConfigChecker implements AsciiConfigChecker {

    /**
     * The unique instance of the checker.
     */
    private static final AsciiConfigChecker instance = new ANTLRAsciiConfigChecker();

    /**
     * Singleton, use {@link #getInstance()}.
     */
    private ANTLRAsciiConfigChecker() {
    }

    @Override
    public void check(File f) throws ConformanceException, IOException {
        check(new ANTLRFileStream(f.getAbsolutePath()));
    }

    @Override
    public void check(String str) throws ConformanceException {
        check(new ANTLRStringStream(str));
    }

    @Override
    public void check(Reader r) throws ConformanceException, IOException {
        check(new ANTLRReaderStream(r));
    }

    private void check(CharStream cs) throws ConformanceException {
        AsciiConfigLexer l = new AsciiConfigLexer(cs);
        CommonTokenStream s = new CommonTokenStream(l);
        AsciiConfigParser p = new AsciiConfigParser(s);
        try {
            AsciiConfigParser.configuration_return ret = p.configuration();
            if (ret.errors != null && !ret.errors.isEmpty()) {
                StringBuilder buf = new StringBuilder(ret.errors.size());
                for (Iterator<String> ite = ret.errors.iterator(); ite.hasNext(); ) {
                    buf.append(ite.next());
                    if (ite.hasNext()) {
                        buf.append('\n');
                    }
                }
                throw new ConformanceException(buf.toString());
            }
        } catch (Exception e) {
            throw new ConformanceException(e.getMessage());
        }
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
