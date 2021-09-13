package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.assets.AnimeGifs;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;

public class ConfessCommand implements ICommand{

    @Override
    public void handle(CommandContext context) throws Exception {
        context.reply(String.format("Yeu %s", String.join(" ", context.getArgs())), 
                new EmbedBuilder().setImage(AnimeGifs.sove[0]).build());
    }

    @Override
    public String getName() {
        return "confess";
    }

    @Override
    public String getDescription() {
        return "confess to someone";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty())
            throw new Exception(String.format("`Correct usage is [%sconfess <someone>]`", Config.get("PREFIX")));
    }
    
}
