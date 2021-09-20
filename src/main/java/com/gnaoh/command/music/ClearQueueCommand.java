package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;

public class ClearQueueCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) {
        bot.getPlayerManager().getMusicManager(bot.getGuild())
        .scheduler.trackQueue.clear();
    
        bot.reply("`The queue has been cleared`");
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear all songs from the queue";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception("`This command should not contain parameters`");
    }
    
}
