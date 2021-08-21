package com.gnaoh.command.real;

import com.gnaoh.command.Command;
import com.gnaoh.utilities.AnimeGifs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class TalkCommand extends Command {
    public void info() {
        MessageEmbed messageEmbed = new EmbedBuilder().setImage(AnimeGifs.shine.one).build();
        event.getChannel().sendMessage("Made by gnaoh").embed(messageEmbed).queue();
    }

    public void hello() {
        event.getChannel().sendMessage("hi " + event.getAuthor().getAsMention() + " ❤️").queue();
    }

    public void hi() {
        MessageEmbed messageEmbed = new EmbedBuilder().setImage(AnimeGifs.shy.one).build();
        event.getChannel().sendMessage("hello " + event.getAuthor().getAsMention() + " ❤️").embed(messageEmbed).queue();
    }

    public void say(String message) {
        event.getChannel().sendMessage(message).queue();
    }

    public void confess(String crush) {
        MessageEmbed messageEmbed = new EmbedBuilder().setImage(AnimeGifs.love.giveHeart).build();
        event.getChannel().sendMessage("love " + crush + " ❤️").embed(messageEmbed).queue();
    }
}
