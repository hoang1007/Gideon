package com.gnaoh;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot 
{
    public static void main( String[] args ) throws LoginException, InterruptedException
    {
        // Initialize gideon
        JDA jda = JDABuilder.createDefault(
            Config.INSTANCE.get("TOKEN"),
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES
        )
        .disableCache(EnumSet.of(
            CacheFlag.CLIENT_STATUS,
            CacheFlag.ACTIVITY,
            CacheFlag.EMOTE
        ))
        .enableCache(CacheFlag.VOICE_STATE)
        .addEventListeners(new EventListener())
        .setActivity(Activity.listening("Commands from you"))
        .build();
        
        jda.awaitReady();
    }
}
