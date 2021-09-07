package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class ResumeCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            resumeTrack(PlayerManager.INSTANCE.getMusicManager(context.getGuild()).audioPlayer);
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    void resumeTrack(AudioPlayer audioPlayer) throws Exception {
        if (audioPlayer.isPaused()) {
            audioPlayer.setPaused(false);
        }
        else 
            throw new Exception("Track is playing");
    }

    @Override
    public String getName() {
        return "resume";
    }

    @Override
    public String getHelp() {
        return "resume the current track";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
