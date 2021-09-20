package com.gnaoh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.dv8tion.jda.api.OnlineStatus;

public class Config {
    private String token;
    private String prefix;

    private String successEmoji;
    private String warningEmoji;
    private String errorEmoji;
    private String loadingEmoji;
    private String searchingEmoji;

    private boolean stayInChannel;

    private long aloneTimeUntilStop;

    private OnlineStatus status;

    public Config() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("src/resources/config.properties")) {
            properties.load(is);

            init(properties);
        }
    }

    void init(Properties properties) {
        token = System.getenv("TOKEN"); // more security

        prefix = properties.getProperty("prefix");
        
        successEmoji = properties.getProperty("success");
        warningEmoji = properties.getProperty("warning");
        errorEmoji = properties.getProperty("error");
        loadingEmoji = properties.getProperty("loading");
        searchingEmoji = properties.getProperty("searching");

        aloneTimeUntilStop = Long.parseLong(properties.getProperty("alonetimeuntilstop"));
        status = OnlineStatus.valueOf(properties.getProperty("status"));
    }

    public String getToken() { return token; }
    public String getPrefix() { return prefix; }

    public String getSuccess() { return successEmoji; }
    public String getWarning() { return warningEmoji; }
    public String getError() { return errorEmoji; }
    public String getLoading() { return loadingEmoji; }
    public String getSearching() { return searchingEmoji; }

    public boolean isStayInChannel() { return stayInChannel; }

    public long getAloneTimeUntilStop() {return aloneTimeUntilStop; }

    public OnlineStatus getStatus() { return status; }
}
