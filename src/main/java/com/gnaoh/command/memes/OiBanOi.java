package com.gnaoh.command.memes;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.assets.MemeSounds;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMemeCommand;
import com.gnaoh.exception.arguments.InvalidArgumentException;

public class OiBanOi implements IMemeCommand {

    @Override
    public void handle(CommandContext context) throws Exception {
        play(context, MemeSounds.oibanoi);
    }

    @Override
    public String getName() {
        return "oibanoi";
    }

    @Override
    public String getDescription() {
        return "summon kha bank";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty()) {
            throw new InvalidArgumentException(String.format("`Correct usage is [%soibanoi]`", Config.prefix));
        }
    }
    
}
