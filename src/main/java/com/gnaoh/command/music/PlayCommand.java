package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.exception.NoMemberInVoiceChannel;
import com.gnaoh.exception.NotSameVoiceChannel;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;
import com.gnaoh.util.other.Formatter;
import com.gnaoh.util.web.UrlUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;

public class PlayCommand implements IMusicCommand {
    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        final GuildVoiceState selfVoiceState = bot.getVoiceState(MemberType.BOT),
                memberVoiceState = bot.getVoiceState(MemberType.NORMAL);

        if (!selfVoiceState.inVoiceChannel())
            bot.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());

        play(bot, String.join(" ", args));
    }

    void play(Bot bot, String url) {
        if (!UrlUtils.isUrl(url)) {
            url = "ytsearch:" + url;
        }

        final GuildMusicManager musicManager = bot.getPlayerManager().getMusicManager(bot.getGuild());

        bot.getPlayerManager().getAudioPlayerManager().loadItemOrdered(bot.getGuild(), url, new AudioLoadResultHandler() {
  
            @Override
            public void loadFailed(FriendlyException arg0) {
                bot.reply("Error while handle the request");
            }

            @Override
            public void noMatches() {
                bot.reply("I can't find your request");
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack track = playlist.getTracks().get(0);

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title, Formatter.formatTime(track.getDuration())))
                        .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                bot.reply(embedBuilder.build());
                musicManager.scheduler.queue(track);
            }

            @Override
            public void trackLoaded(AudioTrack track) {
                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title, Formatter.formatTime(track.getDuration())))
                        .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                bot.reply(embedBuilder.build());
                musicManager.scheduler.queue(track);
            }
        });
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "play a song";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty())
            throw new InvalidArgumentException("Must provide a url or song's name`");
    }

    @Override
    public void checkVoiceChannel(Bot bot) throws Exception {
        final GuildVoiceState selfVoiceState = bot.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = bot.getVoiceState(MemberType.NORMAL);

        if (!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();

        if (selfVoiceState.inVoiceChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }
}
