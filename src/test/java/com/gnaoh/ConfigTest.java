package com.gnaoh;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ConfigTest {
    @Test
    public void testPrefix() {
        try {
            assertEquals("!", new Config().getPrefix());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
