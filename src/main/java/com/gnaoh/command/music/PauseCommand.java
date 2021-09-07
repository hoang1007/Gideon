package com.gnaoh.command.music;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class PauseCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            pauseCurrentTrack(PlayerManager.INSTANCE.getMusicManager(context.getGuild()).audioPlayer);
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    void pauseCurrentTrack(AudioPlayer audioPlayer) throws Exception {
        if (audioPlayer.isPaused())
            throw new Exception("Audio player is already paused");
        
        audioPlayer.setPaused(true);
    }

    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public String getHelp() {
        return "Pauses the current track";
    }

    @Override
    public void checkParameters(CommandContext context) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
