package com.gnaoh.command;

import com.gnaoh.base.Command;
import com.gnaoh.utilities.AnimeGif;

public class TalkCommand extends Command {
    public void info() {
        event.getChannel().sendMessage(AnimeGif.smile).queue();
    }

    public void hello() {
        event.getChannel().sendMessage("hi " + event.getAuthor().getAsMention() + " ❤️").queue();
    }
}
