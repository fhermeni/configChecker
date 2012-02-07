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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;

/**
 * Unit test for {@code ANTLRAsciiConfigCheckerTest}
 *
 * @author Fabien Hermenier
 */
public class ANTLRAsciiConfigCheckerTest {

    private static final File sample = new File("src/test/resources/sample.cfg");

    @DataProvider(name = "goodStrings")
    public Object[][] getGoodStrings() {
        return new Object[][]{
                new Object[]{"N1: VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM6\n"},
                new Object[]{"(N1)\n(N2)\nN3: !VM1 !VM2 !VM3\n?:VM6\n"},
                new Object[]{"(N1)\n(N2)\nN3: !VM1 !VM2 !VM3"}
        };
    }

    @DataProvider(name = "badStrings")
    public Object[][] getBadStrings() {
        return new Object[][]{
                new Object[]{"(N1): VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM6\n"}, //N1 is offline but host VMs
                new Object[]{"N1 VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM6\n"},    //Miss ":"
                new Object[]{"N1: VM1 VM2 (VM3)N2:!VM4\n(N3)\n?:VM6\n"},     //Miss "\n" at the end of the first node
                new Object[]{"!N1: VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM6\n"},  //Not possible: "!N1"
                new Object[]{"N1: VM1 VM2 (VM3)\n N2:!VM4\n(N3)\n? VM6\n"},    //Miss ":" for the waiting VMs
                new Object[]{"N1: VM1 VM2 (VM3)\nN1:!VM4\n(N3)\n?:VM6\n"}, //Duplicate ID
                new Object[]{"N1: VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM1\n"}, //Duplicate ID
                new Object[]{"N1: VM1 VM2 (VM3)\nN2:!VM4\n(N3)\n?:VM4 VM2\n"}, //Duplicate ID
        };
    }


    @Test(dataProvider = "goodStrings")
    public void testCheckFromGoodString(String str) {
        AsciiConfigChecker instance = ANTLRAsciiConfigChecker.getInstance();
        try {
            instance.check(str);
        } catch (ConformanceException e) {
            Assert.fail(str + "\n" + e.getMessage(), e);
        } finally {
            System.err.flush();
        }
    }

    @Test(dataProvider = "badStrings", expectedExceptions = {ConformanceException.class})
    public void testCheckFromBadString(String str) throws ConformanceException {
        AsciiConfigChecker instance = ANTLRAsciiConfigChecker.getInstance();
        instance.check(str);
        //Assert.fail(str);
    }

    @Test(dependsOnMethods = {"testCheckFromBadString", "testCheckFromGoodString"})
    public void testCheckFromFile() throws Exception {
        AsciiConfigChecker instance = ANTLRAsciiConfigChecker.getInstance();
        instance.check(new File("src/test/resources/sample.cfg"));
    }

    @Test(dependsOnMethods = {"testCheckFromBadString", "testCheckFromGoodString"})
    public void testCheckFromReader() throws Exception {
        AsciiConfigChecker instance = ANTLRAsciiConfigChecker.getInstance();
        instance.check(new FileReader(sample));
    }
}
