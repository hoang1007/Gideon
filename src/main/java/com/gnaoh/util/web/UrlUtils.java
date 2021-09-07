package com.gnaoh.util.web;

import java.net.URI;

public class UrlUtils {
    public static boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getThumbnailUrl(String indentifer) {
        return String.format("http://i3.ytimg.com/vi/%s/maxresdefault.jpg", indentifer);
    }
}