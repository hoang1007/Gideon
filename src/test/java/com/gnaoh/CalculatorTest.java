package com.gnaoh;

import static org.junit.Assert.assertEquals;

import com.gnaoh.utilities.numerics.Bignum;
import com.gnaoh.utilities.numerics.Calculator;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testMultiply() {
        assertEquals(new Bignum(0), Calculator.multiply(new Bignum(0), new Bignum(56)));
    }

    @Test
    public void testAdd() {
        assertEquals(new Bignum(789), Calculator.add(new Bignum(0), new Bignum(789)));
    }

    @Test
    public void testShift() {
        Bignum bignum = new Bignum(12345);
        Bignum afterShift = bignum.shift(1);
        Bignum expect = new Bignum(123450);
        assertEquals(expect.length(), afterShift.length());
        assertEquals(true, afterShift.equals(expect));
    }
}
