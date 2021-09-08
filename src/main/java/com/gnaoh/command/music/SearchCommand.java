package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;

public class SearchCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) {
        PlayerManager.INSTANCE.loadAndPlay(
            context.getChannel(),
            "ytsearch:" + String.join(" ", context.getArgs()) ,
            true // get list set true
        );
    }

    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getDescription() {
        return "search youtube for a provided query";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty())
            throw new Exception(String.format("`Correct usage is %ssearch <title>`", Config.prefix));
    }
}
