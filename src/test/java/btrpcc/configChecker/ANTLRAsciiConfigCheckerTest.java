package btrpcc.configChecker;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for {@code ANTLRAsciiConfigCheckerTest}
 *
 * @author Fabien Hermenier
 */
public class ANTLRAsciiConfigCheckerTest {

    @DataProvider(name = "goodStrings")
    public Object[][] getGoodStrings() {
        return new Object[][]{
                new Object[]{"N1: VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM6"},
                new Object[]{"(N1)\n(N2)\nN3: !VM1 ! VM2 !VM3\n?:VM6"}
        };
    }


    @Test(dataProvider = "goodStrings")
    public void testCheckFromGoodString(String str) throws Exception {
        AsciiConfigChecker instance = ANTLRAsciiConfigChecker.getInstance();
        Assert.assertTrue(instance.check(str));
    }

    @Test
    public void testCheckFromFile() throws Exception {

    }

    @Test
    public void testCheckFromReader() throws Exception {

    }
}
