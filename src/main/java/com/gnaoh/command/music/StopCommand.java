package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;

public class StopCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            
            PlayerManager.INSTANCE.getMusicManager(context.getGuild())
                .scheduler.player.stopTrack();
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getHelp() {
        return "Stop the current song";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub
        
    }
}
