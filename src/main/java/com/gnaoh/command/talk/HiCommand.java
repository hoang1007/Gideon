package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.gifs.AnimeGifs;

import net.dv8tion.jda.api.EmbedBuilder;

public class HiCommand implements ICommand {
    @Override
    public void handle(CommandContext context) {
        context.reply("hello " + context.getEvent().getAuthor().getAsMention() + " ❤️", 
            new EmbedBuilder().setImage(AnimeGifs.shy.getRandom()));
    }

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public String getHelp() {
        return "Hi me and I'll hello back";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub
    }
}
