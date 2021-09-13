package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;

public class StopCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) {
        PlayerManager.INSTANCE.getMusicManager(context.getGuild())
            .scheduler.player.stopTrack();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "stop the current song";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception(String.format("`Correct usage is [%sstop]`", Config.get("PREFIX")));
    }
}
