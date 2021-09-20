package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.assets.AnimeGifs;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;

import net.dv8tion.jda.api.EmbedBuilder;

public class ConfessCommand implements ICommand{

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        bot.reply(String.format("Yeu %s", String.join(" ", args)), 
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
            throw new InvalidArgumentException("`Seem you missing your crush`");
    }
    
}
