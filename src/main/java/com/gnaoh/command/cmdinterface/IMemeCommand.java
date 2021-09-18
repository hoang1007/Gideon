package com.gnaoh.command.cmdinterface;

import com.gnaoh.command.CommandContext;
import com.gnaoh.exception.NoMemberInVoiceChannel;
import com.gnaoh.exception.NotSameVoiceChannel;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface IMemeCommand extends IMusicCommand {
    default void play(CommandContext context, String url) {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT),
                                memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if (!selfVoiceState.inVoiceChannel())
            context.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        
        final GuildMusicManager musicManager = PlayerManager.INSTANCE.getMusicManager(context.getGuild());

        PlayerManager.INSTANCE.getAudioPlayerManager().loadItem(url, new AudioLoadResultHandler() {

            @Override
            public void loadFailed(FriendlyException arg0) {
                context.reply("Error while loading");
            }

            @Override
            public void noMatches() {
                context.reply("No match found");
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
    default void checkVoiceChannel(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if(!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();
        
        if (selfVoiceState.inVoiceChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }
}