package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.ienum.UserType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

public class SkipCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) {
        try {
            checkVoiceChannel(bot);
            GuildMusicManager musicManager = bot.getPlayerManager().getMusicManager(bot.getGuild());
            
            skipTrack(musicManager);
            bot.reply("Track is skipped by " + bot.getUser(UserType.AUTHOR).getAsMention());
        } catch (Exception e) {
            bot.reply(e.getMessage());
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
            throw new Exception("This command should not contain parameters");
    }
}
