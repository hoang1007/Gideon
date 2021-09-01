package com.gnaoh;

import static org.junit.Assert.assertNotNull;

import com.gnaoh.util.AnimeGifs;

import org.junit.Test;

public class GifTest {
    @Test
    public void testGif() {
        assertNotNull(AnimeGifs.angry.tohruAngry1);
    }

    @Test
    public void testGetRandomGif() {
        assertNotNull(AnimeGifs.angry.getRandom());
    }
}
