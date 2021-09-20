package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;


public class StopCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) {
        bot.getPlayerManager().getMusicManager(bot.getGuild())
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
            throw new Exception("`This command should not contain parameters`");
    }
}
