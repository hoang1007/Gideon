package com.gnaoh;

import static org.junit.Assert.assertEquals;

import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;
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
    public void testPow() {
        assertEquals(new Bignum(1024), Calculator.power(new Bignum(2), 10));
    }
}
