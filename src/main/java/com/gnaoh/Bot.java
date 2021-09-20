package com.gnaoh;

import javax.annotation.Nonnull;

import com.gnaoh.ienum.MemberType;
import com.gnaoh.ienum.UserType;
import com.gnaoh.util.commands.CommandManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/** Get every things of bot */
public class Bot extends ListenerAdapter {
    private Config config;
    private EventWaiter eventWaiter;
    private PlayerManager playerManager;
    private CommandManager commandManager;
    private JDA jda;
    private GuildMessageReceivedEvent guildEvent;

    Bot(Builder builder) {
        this.config = builder.config;
        this.eventWaiter = builder.eventWaiter;
        this.playerManager = builder.playerManager;
        this.jda = builder.jda;
        this.commandManager = builder.commandManager;
    }

    public Config getConfig() {
        return config;
    }

    public EventWaiter getEventWaiter() {
        return eventWaiter;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public JDA getJDA() { return jda; }

    public GuildMessageReceivedEvent getEvent() {
        return guildEvent;
    }

    public Guild getGuild() {
        return getEvent().getGuild();
    }

    public TextChannel getChannel() {
        return getEvent().getChannel();
    }

    public User getUser(UserType userType) {
        switch (userType) {
            case AUTHOR:
                return getEvent().getAuthor();
            case BOT:
                return jda.getSelfUser();
            default:
                return null;
        }
    }

    public Member getMember(MemberType memberType) {
        switch (memberType) {
            case BOT:
                return getGuild().getSelfMember();
            case NORMAL:
                return getEvent().getMember();
            default:
                return null;
        }
    }

    public GuildVoiceState getVoiceState(MemberType memberType) {
        return getMember(memberType).getVoiceState();
    }

    public void reply(@Nonnull String message) {
        getChannel().sendMessage(message).queue();
    }

    public void reply(@Nonnull String message, @Nonnull MessageEmbed msgEmbed) {
        getChannel().sendMessage(message).embed(msgEmbed).queue();
    }

    public void reply(@Nonnull String format, @Nonnull Object...args) {
        getChannel().sendMessageFormat(format, args).queue();
    }

    public void reply(@Nonnull MessageEmbed msgEmbed) {
        getChannel().sendMessage(msgEmbed).queue();
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage())
            return ;
        
        if (event.getMessage().getContentRaw().startsWith(config.getPrefix())) {
            guildEvent = event;
            commandManager.handle(this);
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            commandManager.retrieveCommands();
            commandManager.upsertCommands(jda);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private Config config;
        private EventWaiter eventWaiter;
        private PlayerManager playerManager;
        private CommandManager commandManager;
        private JDA jda;

        public Builder setEventWaiter(EventWaiter eventWaiter) {
            this.eventWaiter = eventWaiter;
            return this;
        }

        public Builder setConfig(Config config) {
            this.config = config;
            return this;
        }

        public Builder setPlayerManager(PlayerManager playerManager) {
            this.playerManager = playerManager;
            return this;
        }

        public Builder setJDA(JDA jda) {
            this.jda = jda;
            return this;
        }

        public Builder setCommandManager(CommandManager commandManager) {
            this.commandManager = commandManager;
            return this;
        }

        public Bot build() {
            return new Bot(this);
        }
    }
}
