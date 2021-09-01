package com.gnaoh.util;

import java.lang.reflect.Field;
import java.util.Random;

public class AnimeGifs {
    private static AnimeGifs instance = new AnimeGifs();

    public static Angry angry = instance.new Angry();
    public static Shy shy = instance.new Shy();
    public static Love love = instance.new Love();
    public static Cute cute = instance.new Cute();
    public static Shine shine = instance.new Shine();
    public static Stunned stunned = instance.new Stunned();
    public static Smile smile = instance.new Smile();
    public static Hard hard = instance.new Hard();


    private abstract class emotion {
        public String getRandom() {
            Field[] fields = this.getClass().getDeclaredFields();

            try {
                return fields[new Random().nextInt(fields.length)].get(this).toString();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class Angry extends emotion {
        public final String tohruAngry1 = "https://c.tenor.com/5lyQO3OshFoAAAAC/tohru-angry.gif",
                            tohruAngry2 = "https://c.tenor.com/X3x3Y2mp2W8AAAAC/anime-angry.gif";
    }

    public class Shy extends emotion {
        public final String one = "https://i.pinimg.com/originals/71/de/78/71de7826ad02a908a1c3e572f50e6901.gif";
    }

    public class Love extends emotion {
        public final String giveHeart = "https://c.tenor.com/_jrTQpXbWP4AAAAC/anime-love.gif";
    }

    private class Cute extends emotion {
        public final String tohruFond = "https://c.tenor.com/SO03rrfGFv8AAAAM/tohru-kobayashi-kobayashisan-chi-no-maid-dragon.gif";
    }

    public class Shine extends emotion {
        public final String one = "https://c.tenor.com/3cnefMD2rTUAAAAM/shine-hanamaru-kindergarten.gif";
    }

    private class Stunned extends emotion {
        public final String one = "https://media.giphy.com/media/AHj0lQstZ9I9W/giphy.gif";
    }

    private class Smile extends emotion {
        public final String one = "https://media.giphy.com/media/wkW0maGDN1eSc/giphy.gif";
    }

    public class Hard extends emotion {
        public final String one = "https://64.media.tumblr.com/87bb0a3ec7ddf3b7b5d0d76645128c99/tumblr_pf876e7N3u1tvac0g_500.gifv";
    }
}