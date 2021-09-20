package com.gnaoh.command.music;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.other.Formatter;
import com.gnaoh.util.web.UrlUtils;
import com.jagrosh.jdautilities.menu.OrderedMenu;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.EmbedBuilder;

public class SearchCommand implements IMusicCommand {
    private final OrderedMenu.Builder menuBuilder;

    public SearchCommand() {
        menuBuilder = new OrderedMenu.Builder().allowTextInput(true).useNumbers().useCancelButton(true).setTimeout(1,
                TimeUnit.MINUTES);
    }

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        menuBuilder.setEventWaiter(bot.getEventWaiter());

        String query = String.join(" ", args);

        bot.getPlayerManager().getAudioPlayerManager().loadItemOrdered(bot.getGuild(), "ytsearch:" + query,
                new AudioLoadResultHandler() {

                    @Override
                    public void loadFailed(FriendlyException arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void noMatches() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist playlist) {
                        menuBuilder.setColor(bot.getMember(MemberType.BOT).getColor())
                                .setText(String.format("`Search results for %s:`", query)).setChoices(new String[0])
                                .setSelection((msg, i) -> {
                                    AudioTrack track = playlist.getTracks().get(i - 1);

                                    bot.getPlayerManager().getMusicManager(bot.getGuild()).scheduler.queue(track);
                                    EmbedBuilder embedBuilder = new EmbedBuilder()
                                            .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title,
                                                    Formatter.formatTime(track.getDuration())))
                                            .setAuthor(track.getInfo().author)
                                            .setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                                    bot.reply(embedBuilder.build());
                                }).setCancel(msg -> {
                                }).setUsers(bot.getEvent().getAuthor());

                        for (int i = 0; i < 4 && i < playlist.getTracks().size(); i++) {
                            AudioTrack track = playlist.getTracks().get(i);
                        
                            menuBuilder.addChoices("[**"+String.format("%s - %s", track.getInfo().title, Formatter.formatTime(track.getDuration()))+"**]("+track.getInfo().uri+")");
                        }

                        menuBuilder.build().display(bot.getChannel());
                    }

                    @Override
                    public void trackLoaded(AudioTrack track) {
                        bot.getPlayerManager().getMusicManager(bot.getGuild()).scheduler.queue(track);
                        EmbedBuilder embedBuilder = new EmbedBuilder()
                                .setTitle(String.format("Adding to queue: `%s - %s`", track.getInfo().title,
                                        Formatter.formatTime(track.getDuration())))
                                .setAuthor(track.getInfo().author)
                                .setImage(UrlUtils.getThumbnailUrl(track.getIdentifier()));

                        bot.reply(embedBuilder.build());
                    }

                });
    }

    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getDescription() {
        return "search Youtube for a provided query";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty()) {
            throw new InvalidArgumentException("Must provide a query");
        }
    }
}
