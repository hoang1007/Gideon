package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.music.NoMemberInVoiceChannel;
import com.gnaoh.exception.music.NotSameVoiceChannel;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.lavaplayer.GuildMusicManager;
import com.gnaoh.util.lavaplayer.PlayerManager;
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
    public void handle(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT),
                memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if (!selfVoiceState.inVoiceChannel())
            context.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());

        play(context, String.join(" ", context.getArgs()));
    }

    void play(CommandContext context, String url) {
        if (!UrlUtils.isUrl(url)) {
            url = "ytsearch:" + url;
        }

        final GuildMusicManager musicManager = PlayerManager.INSTANCE.getMusicManager(context.getGuild());

        PlayerManager.INSTANCE.getAudioPlayerManager().loadItemOrdered(context.getGuild(), url, new AudioLoadResultHandler() {
  
            @Override
            public void loadFailed(FriendlyException arg0) {
                context.reply("Error while handle the request");
            }

            @Override
            public void noMatches() {
                context.reply("I can't find your request");
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack track = playlist.getTracks().get(0);

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title, Formatter.formatTime(track.getDuration())))
                        .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                context.reply(embedBuilder.build());
                musicManager.scheduler.queue(track);
            }

            @Override
            public void trackLoaded(AudioTrack track) {
                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title, Formatter.formatTime(track.getDuration())))
                        .setAuthor(track.getInfo().author).setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                context.reply(embedBuilder.build());
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
            throw new Exception(String.format("Correct usage is `%splay <youtube link> or <name of song>`",
                    Config.prefix));
    }

    @Override
    public void checkVoiceChannel(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if (!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();

        if (selfVoiceState.inVoiceChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }
}
