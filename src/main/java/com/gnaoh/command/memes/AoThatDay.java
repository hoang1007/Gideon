package com.gnaoh.command.memes;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.assets.MemeSounds;
import com.gnaoh.command.cmdinterface.IMemeCommand;
import com.gnaoh.exception.InvalidArgumentException;

public class AoThatDay implements IMemeCommand {

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        play(bot, MemeSounds.aothatday);
    }

    @Override
    public String getName() {
        return "aothatday";
    }

    @Override
    public String getDescription() {
        return "summon kha bank";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new InvalidArgumentException("`This command should not contain parameters`");
    } 
}
