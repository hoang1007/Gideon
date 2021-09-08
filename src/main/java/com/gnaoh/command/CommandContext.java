package com.gnaoh.command;

import java.util.List;

import javax.annotation.Nonnull;

import com.gnaoh.ienum.MemberType;
import com.gnaoh.ienum.UserType;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
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

    public JDA getJDA() {
        return event.getJDA();
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

    public User getUser(UserType userType) {
        switch (userType) {
            case AUTHOR:
                return event.getAuthor();
            case BOT:
                return event.getJDA().getSelfUser();
            default:
                return null;
        }
    }

    public Member getMember(MemberType memberType) {
        switch (memberType) {
            case BOT:
                return getGuild().getSelfMember();
            case NORMAL:
                return event.getMember();
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
}