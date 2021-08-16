package com.gnaoh.utilities.numerics;

public class Calculator {
    public static Bignum add(Bignum a, Bignum b) {
        if (a.equals(Bignum.zero))
            return b;
        if (b.equals(Bignum.zero))
            return a;

        Bignum result = a.compareTo(b) > 0 ? a.copy() : b.copy(); 

        int carry = 0;
        int i_a = a.length() - 1, i_b = b.length() - 1, i_res = 0;

        for (; i_a >= 0 || i_b >= 0; i_a--, i_b--, i_res++) {
            int digita = 0, digitb = 0;

            if (i_a >= 0)
                digita = a.getDigitAt(i_a);
            if (i_b >= 0)
                digitb = b.getDigitAt(i_b);

            int sum = digita + digitb + carry;
            result.setDigitAt(i_res, sum % 10);

            carry = sum / 10;
        }

        if (carry > 0) {
            result = result.shift(1);
            result.setDigitAt(i_res, carry);
        }

        return result.reverse();
    }

    private static Bignum multiplySingle(Bignum a, int b) {
        Bignum res = a.copy();

        int i_res = 0;
        int carry = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int mul = b * a.getDigitAt(i);
            int sum = carry + mul;

            res.setDigitAt(i_res++, sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            res = res.shift(1);
            res.setDigitAt(res.length() - 1, carry);
        }

        return res.reverse();
    }

    public static Bignum multiply(Bignum a, Bignum b) {
        Bignum res = Bignum.zero;

        if (a.equals(Bignum.zero) || b.equals(Bignum.zero)) {
            return Bignum.zero;
        }

        for (int i = a.length() - 1, shiftTime = 0; i >= 0; i--, shiftTime++) {
            Bignum mul = multiplySingle(b, a.getDigitAt(i));

            res = add(res, mul.shift(shiftTime));
        }

        return res;
    }

    public static Bignum power(Bignum a, long b) {
        Bignum res = new Bignum(1);

        for (; b > 0; b /= 2, a = Calculator.multiply(a, a))
            if (b % 2 == 1)
                res = Calculator.multiply(res, a);
        return res;
    }
}
