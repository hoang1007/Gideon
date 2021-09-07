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
}
