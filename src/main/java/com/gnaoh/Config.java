package com.gnaoh;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static Dotenv dotenv = Dotenv.load();
    
    public static String get(String env) {
        try {
            return System.getenv(env);
        } catch (Exception e) {
            return dotenv.get(env);
        }
    }
}