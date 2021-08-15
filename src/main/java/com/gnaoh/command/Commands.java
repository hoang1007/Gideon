package com.gnaoh.command;

import com.gnaoh.utilities.AnimeGif;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Commands {
    GuildMessageReceivedEvent event;
    
    public Commands(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    public void info(String param) {
        event.getChannel().sendMessage(AnimeGif.smile).queue();
    }

    public void hello(String param) {
        event.getChannel().sendMessage("hi " + event.getAuthor().getAsMention() + " ❤️").queue();
    }
}
