package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class ResumeCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) throws Exception {
        resumeTrack(PlayerManager.INSTANCE.getMusicManager(context.getGuild()).audioPlayer);
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
            throw new Exception(String.format("`Correct usage is [%sresume]`", Config.get("PREFIX")));
    }
    
}
