package com.gnaoh.command.music;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.GuildMusicManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class SkipCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            GuildMusicManager musicManager = PlayerManager.INSTANCE.getMusicManager(context.getGuild());
            
            skipTrack(musicManager);
            context.reply("Track is skipped by " + context.getAuthor().getAsMention());
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
    public String getHelp() {
        return "Skips the current track";
    }

    @Override
    public void checkParameters(CommandContext context) {
    }
}
