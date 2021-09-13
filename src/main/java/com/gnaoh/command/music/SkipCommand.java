package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.ienum.UserType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class SkipCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            GuildMusicManager musicManager = PlayerManager.INSTANCE.getMusicManager(context.getGuild());
            
            skipTrack(musicManager);
            context.reply("Track is skipped by " + context.getUser(UserType.AUTHOR).getAsMention());
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    void skipTrack(GuildMusicManager musicManager) throws Exception {
        AudioPlayer audioPlayer = musicManager.audioPlayer;

        if (audioPlayer.getPlayingTrack() == null)
            throw new Exception("There is no track playing currently");
            
        musicManager.scheduler.nextTrack();
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getDescription() {
        return "skips the current track";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception(String.format("Correct usage is [%sskip]", Config.get("PREFIX")));
    }
}
