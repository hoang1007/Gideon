package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.InvalidArgumentException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class PauseCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        pauseCurrentTrack(bot.getPlayerManager().getMusicManager(bot.getGuild()).audioPlayer);
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
            throw new InvalidArgumentException("`This command should not contain parameters`");
    }

}
