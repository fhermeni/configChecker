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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Permission;

/**
 * Unit test for the CLI.
 *
 * @author Fabien Hermenier
 */
@Test
public class CCLauncherTest {

    static class ExitException extends SecurityException {
        public final int status;

        public ExitException(int status) {
            super("There is no escape!");
            this.status = status;
        }
    }

    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // allow anything.
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
            // allow anything.
        }

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new ExitException(status);
        }
    }

    @BeforeClass
    protected void setUp() throws Exception {
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @AfterClass
    protected void tearDown() throws Exception {
        System.setSecurityManager(null); // or save and restore original
    }

    /**
     * Test with a single file that is valid.
     */
    public void testSimpleGood() {
        try {
            CCLauncher.main(new String[]{"src/test/resources/sample.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 0);
        }
    }

    /**
     * Test with a single file that is valid.
     */
    public void testBads() {
        try {
            CCLauncher.main(new String[]{"src/test/resources/bad1.cfg", "src/test/resources/bad2.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 1);
        }
    }

    /**
     * Test with a single file that is valid.
     */
    public void testMultipleGoodAndBad() {
        try {
            CCLauncher.main(new String[]{"src/test/resources/sample.cfg", "src/test/resources/bad2.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 1);
        }
    }
}
