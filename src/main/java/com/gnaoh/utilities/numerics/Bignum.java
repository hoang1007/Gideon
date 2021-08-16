package com.gnaoh.utilities.numerics;

import java.util.Comparator;

public class Bignum implements Comparable<Bignum> {
    private StringBuilder raw;
    
    public Bignum(long num) {
        raw = new StringBuilder(Long.toString(num));
    }

    private Bignum(Bignum other) {
        raw = new StringBuilder(other.raw);
    }

    protected Bignum(String numStr) {
        this.raw = new StringBuilder(numStr);
    }

    public final static Bignum zero = new Bignum(0);

    public static Bignum Parse(String numStr) {
        for (int i = 0; i < numStr.length(); i++)
            if (numStr.charAt(i) < '0' || numStr.charAt(i) > '9')
                throw new IllegalArgumentException("Khong phai so. May dua bo may a");

        return new Bignum(numStr);
    }

    public int length() {
        return raw.length();
    }

    public Bignum reverse() {
        Bignum rev = this.copy();

        for (int i = 0; i < rev.length() / 2; i++) {
            char temp = rev.raw.charAt(i);
            rev.raw.setCharAt(i, rev.raw.charAt(rev.raw.length() - i - 1));
            rev.raw.setCharAt(rev.raw.length() - i - 1, temp);
        }

        return rev;
    }

    public Bignum shift(int times) {
        Bignum sft = this.copy();

        for (; times > 0; times--) {
            sft.raw.append('0');
        }

        return sft;
    }

    public int getDigitAt(int index) {
        if (index < 0 || index >= raw.length())
            throw new IllegalArgumentException("Index out of range");
        return Character.getNumericValue(raw.charAt(index));
    }

    public void setDigitAt(int index, int value) {
        if (index < 0 || index >= raw.length())
            throw new IllegalArgumentException("Index out of range");
        if (value < 0 || value >= 10)
            throw new IllegalArgumentException("Value must be between 0 and 10");

        raw.setCharAt(index, Character.forDigit(value, 10));
    }

    @Override
    public String toString() {
        return raw.toString();
    }

    @Override
    public int compareTo(Bignum other) {
        if (this.raw.length() < other.raw.length())
            return -1;
        if (this.raw.length() > other.raw.length())
            return 1;

        for (int i = 0; i < this.raw.length(); i++) {
            if (this.raw.charAt(i) < other.raw.charAt(i))
                return -1;
            if (this.raw.charAt(i) > other.raw.charAt(i))
                return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object other) {
        Bignum otherNum = (Bignum)other;
        return this.compareTo(otherNum) == 0;
    }

    public final Comparator<Bignum> comparator = new Comparator<Bignum>() {
        public int compare(Bignum o1, Bignum o2) {
            return o1.compareTo(o2);
        }
    };

    public Bignum copy() {
        return new Bignum(this);
    }
}
