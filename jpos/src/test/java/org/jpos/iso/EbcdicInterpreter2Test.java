/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2017 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jpos.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class EbcdicInterpreter2Test {

    @Test
    public void testConstructor() throws Throwable {
        new EbcdicInterpreter();
        assertTrue("Test completed without Exception", true);
    }

    @Test
    public void testGetPackedLength() throws Throwable {
        int result = new EbcdicInterpreter().getPackedLength(100);
        assertEquals("result", 100, result);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testInterpret() throws Throwable {
        new EbcdicInterpreter().interpret("", new byte[2], 100);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testInterpretThrowsArrayIndexOutOfBoundsException() throws Throwable {
        new EbcdicInterpreter().interpret("testEbcdicInterpreterData", new byte[1], 100);
    }

    @Test
    public void testInterpretThrowsNullPointerException() throws Throwable {
        try {
            EbcdicInterpreter.INSTANCE.interpret("testEbcdicInterpreterData", null, 100);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException ex) {
            assertNull("ex.getMessage()", ex.getMessage());
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testUninterpret() throws Throwable {
        byte[] rawData = new byte[0];
        String result = EbcdicInterpreter.INSTANCE.uninterpret(rawData, 100, 0);
        assertEquals("result", "", result);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testUninterpretThrowsArrayIndexOutOfBoundsException() throws Throwable {
        new EbcdicInterpreter().uninterpret(new byte[0], 100, 1000);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testUninterpretThrowsNegativeArraySizeException() throws Throwable {
        new EbcdicInterpreter().uninterpret(new byte[2], 100, -1);
    }

    @Test
    public void testUninterpretThrowsNullPointerException() throws Throwable {
        try {
            new EbcdicInterpreter().uninterpret(null, 100, 1000);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException ex) {
            assertNull("ex.getMessage()", ex.getMessage());
        }
    }
}
