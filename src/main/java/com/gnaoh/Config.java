package com.gnaoh;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    public static Config INSTANCE = new Config();
    
    private Dotenv dotenv = Dotenv.load();
    
    public String get(String env) {
        return dotenv.get(env);
    }
}