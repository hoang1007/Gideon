package com.gnaoh.command.real;

import com.gnaoh.command.Command;
import com.gnaoh.utilities.AnimeGif;

public class TalkCommand extends Command {
    public void info() {
        event.getChannel().sendMessage(AnimeGif.smile).queue();
    }

    public void hello() {
        event.getChannel().sendMessage("hi " + event.getAuthor().getAsMention() + " ❤️").queue();
    }

    public void say(String message) {
        event.getChannel().sendMessage(message).queue();
    }
}
