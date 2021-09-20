package com.gnaoh.command.cmdinterface;

import com.gnaoh.Bot;
import com.gnaoh.exception.NoMemberInVoiceChannel;
import com.gnaoh.exception.NotSameVoiceChannel;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface IMemeCommand extends IMusicCommand {
    default void play(Bot bot, String url) {
        final GuildVoiceState selfVoiceState = bot.getVoiceState(MemberType.BOT),
                                memberVoiceState = bot.getVoiceState(MemberType.NORMAL);

        if (!selfVoiceState.inVoiceChannel())
            bot.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        
        final GuildMusicManager musicManager = bot.getPlayerManager().getMusicManager(bot.getGuild());

        bot.getPlayerManager().getAudioPlayerManager().loadItem(url, new AudioLoadResultHandler() {

            @Override
            public void loadFailed(FriendlyException arg0) {
                bot.reply("Error while loading");
            }

            @Override
            public void noMatches() {
                bot.reply("No match found");
            }

            @Override
            public void playlistLoaded(AudioPlaylist arg0) {
                
            }

            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);
            }
        });      
    }

    @Override
    default void checkVoiceChannel(Bot bot) throws Exception {
        final GuildVoiceState selfVoiceState = bot.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = bot.getVoiceState(MemberType.NORMAL);

        if(!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();
        
        if (selfVoiceState.inVoiceChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }
}