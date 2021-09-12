package com.gnaoh.util.lavaplayer;

import java.util.HashMap;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import net.dv8tion.jda.api.entities.Guild;

public class PlayerManager {
    public static final PlayerManager INSTANCE = new PlayerManager();

    private final Map<Long, GuildMusicManager> musicManager;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManager = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return musicManager.computeIfAbsent(guild.getIdLong(), guildID -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return this.audioPlayerManager;
    }

    // public void loadAndPlay(TextChannel channel, String trackURL, boolean isGetList) {
    //     final GuildMusicManager musicManager = getMusicManager(channel.getGuild());

    //     audioPlayerManager.loadItemOrdered(channel.getGuild(), trackURL, new AudioLoadResultHandler() {
    //         @Override
    //         public void loadFailed(FriendlyException arg0) {
    //             // TODO Auto-generated method stub

    //         }

    //         @Override
    //         public void noMatches() {
    //             // TODO Auto-generated method stub

    //         }

    //         @Override
    //         public void playlistLoaded(AudioPlaylist playlist) {
    //             AudioTrack track = playlist.getTracks().get(0);

    //             musicManager.scheduler.queue(track);

    //             EmbedBuilder embedBuilder = new EmbedBuilder()
    //                     .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title, Formatter.formatTime(track.getDuration())))
    //                     .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

    //             channel.sendMessage(embedBuilder.build()).queue();
    //         }

    //         @Override
    //         public void trackLoaded(AudioTrack track) {
    //             musicManager.scheduler.queue(track);

    //             EmbedBuilder embedBuilder = new EmbedBuilder()
    //                     .setTitle(String.format("Adding to queue: `%s`", track.getInfo().title))
    //                     .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

    //             channel.sendMessage(embedBuilder.build()).queue();
    //         }
    //     });
    // }
}
