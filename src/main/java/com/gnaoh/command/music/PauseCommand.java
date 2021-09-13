package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class PauseCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) throws Exception {
        pauseCurrentTrack(PlayerManager.INSTANCE.getMusicManager(context.getGuild()).audioPlayer);
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
    public String getDescription() {
        return "pauses the current track";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception(String.format("`Correct usage is [%spause]`", Config.get("PREFIX")));
    }

}
