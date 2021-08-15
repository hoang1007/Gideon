package com.gnaoh.utilities;

import java.io.FileReader;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();
    private static boolean initialized = false;

    static void Initialize() {
        try {
            properties.load(new FileReader("config.properties"));
            initialized = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (!initialized)
            Initialize();
        return properties.getProperty(key);
    }
}