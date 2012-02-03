package btrpcc.configChecker;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Permission;

/**
 * Unit test for the CLI.
 * @author Fabien Hermenier
 */
@Test
public class CCLauncherTest {

    protected static class ExitException extends SecurityException {
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
            CCLauncher.main(new String[] {"src/test/resources/sample.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 0);
        }
    }

    /**
     * Test with a single file that is valid.
     */
    public void testBads() {
        try {
            CCLauncher.main(new String[] {"src/test/resources/bad1.cfg", "src/test/resources/bad2.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 1);
        }
    }

    /**
     * Test with a single file that is valid.
     */
    public void testMultipleGoodAndBad() {
        try {
            CCLauncher.main(new String[] {"src/test/resources/sample.cfg","src/test/resources/bad2.cfg"});
        } catch (ExitException e) {
            Assert.assertEquals(e.status, 1);
        }
    }
}
