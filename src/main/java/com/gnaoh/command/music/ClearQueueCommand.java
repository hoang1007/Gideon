package com.gnaoh.command.music;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;

public class ClearQueueCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        PlayerManager.INSTANCE.getMusicManager(context.getGuild())
            .scheduler.trackQueue.clear();
        
        context.reply("The queue has been cleared");
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "Clear all songs from the queue";
    }

    @Override
    public void checkParameters(CommandContext context) {
        // TODO Auto-generated method stub
        
    }
    
}
