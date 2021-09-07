package com.gnaoh.command;

import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandContext {
    private final GuildMessageReceivedEvent event;
    private final List<String> args;
    
    public CommandContext(GuildMessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
    }

    public Guild getGuild() {
        return event.getGuild();
    }

    public GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public TextChannel getChannel() {
        return event.getChannel();
    }

    public List<String> getArgs() {
        return args;
    }

    public Message getMessage() {
        return event.getMessage();
    }

    public User getAuthor() {
        return event.getAuthor();
    }

    public Member getMember() {
        return event.getMember();
    }

    public JDA getJDA() {
        return event.getJDA();
    }

    public User getSelfUser() {
        return getJDA().getSelfUser();
    }

    public Member getSelfMember() {
        return getGuild().getSelfMember();
    }

    public void reply(String message) {
        getChannel().sendMessage(message).queue();
    }
}
