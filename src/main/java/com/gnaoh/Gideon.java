package com.gnaoh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import com.gnaoh.util.commands.CommandManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Gideon {
        public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES};
    
    public static void main(String[] args) throws LoginException, InterruptedException, FileNotFoundException, IOException {
        Config config = new Config();

        // Initialize gideon
        JDA jda = JDABuilder
                .createDefault(config.getToken(), Arrays.asList(INTENTS))
                .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .disableCache(EnumSet.of(CacheFlag.CLIENT_STATUS, CacheFlag.ACTIVITY, CacheFlag.EMOTE))
                .setStatus(config.getStatus())
                .setBulkDeleteSplittingEnabled(true)
                .enableCache(CacheFlag.VOICE_STATE).build();

        Bot bot = new Bot.Builder().setConfig(config).setEventWaiter(new EventWaiter())
                .setPlayerManager(new PlayerManager()).setJDA(jda).setCommandManager(new CommandManager()).build();

        jda.addEventListener(bot, bot.getEventWaiter());
    }
}
