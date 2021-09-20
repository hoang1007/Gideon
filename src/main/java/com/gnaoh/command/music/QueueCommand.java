package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.InvalidArgumentException;

import com.gnaoh.util.other.Formatter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class QueueCommand implements IMusicCommand {

    @Override
    public void handle(Bot bot, List<String> args) {
        String list = "";
        for (AudioTrack track : bot.getPlayerManager().getMusicManager(bot.getGuild()).scheduler.trackQueue) {
            list += String.format("`%s - %s`\n", track.getInfo().title, Formatter.formatTime(track.getDuration()));
        } 

        bot.reply(list.isEmpty() ? "`No tracks available in queue!`" : list);
    }

    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getDescription() {
        return "get a list of tracks";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new InvalidArgumentException("`This command should not contain parameters`");
    }
}
