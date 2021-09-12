package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;

public class ClearQueueCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) {
        PlayerManager.INSTANCE.getMusicManager(context.getGuild())
        .scheduler.trackQueue.clear();
    
        context.reply("`The queue has been cleared`");
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
            throw new Exception(String.format("`Correct usage is [%sclear]`", Config.INSTANCE.get("PREFIX")));
    }
    
}
