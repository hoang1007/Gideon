package com.gnaoh.util.gifs;

import java.util.Random;

public class AnimeGifs {
    public static String getRandom(String[] emotion) {
        int index = new Random().nextInt(emotion.length);
        return emotion[index];
    }

    public static String[] Angry = new String[] {
        "https://c.tenor.com/5lyQO3OshFoAAAAC/tohru-angry.gif",
        "https://c.tenor.com/X3x3Y2mp2W8AAAAC/anime-angry.gif"
    };

    public static String[] shy = new String[] {
        "https://i.pinimg.com/originals/71/de/78/71de7826ad02a908a1c3e572f50e6901.gif"
    };

    public static String[] sove = new String[] {
        "https://c.tenor.com/_jrTQpXbWP4AAAAC/anime-love.gif"
    };

    public static String[] cute = new String[] {
        "https://c.tenor.com/SO03rrfGFv8AAAAM/tohru-kobayashi-kobayashisan-chi-no-maid-dragon.gif"
    };

    public static String[] shine = new String[] {
        "https://c.tenor.com/3cnefMD2rTUAAAAM/shine-hanamaru-kindergarten.gif"
    };

    public static String[] stunned = new String[] {
        "https://media.giphy.com/media/AHj0lQstZ9I9W/giphy.gif"
    };

    public static String[] smile = new String[] {
        "https://media.giphy.com/media/wkW0maGDN1eSc/giphy.gif"
    };

    public static String[] hard = new String[] {
        "https://64.media.tumblr.com/87bb0a3ec7ddf3b7b5d0d76645128c99/tumblr_pf876e7N3u1tvac0g_500.gifv"
    };
}