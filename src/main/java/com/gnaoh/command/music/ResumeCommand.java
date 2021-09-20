package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class ResumeCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        resumeTrack(bot.getPlayerManager().getMusicManager(bot.getGuild()).audioPlayer);
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
    public String getDescription() {
        return "resume the current track";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new InvalidArgumentException("`This command should not contain parameters`");
    }
    
}
