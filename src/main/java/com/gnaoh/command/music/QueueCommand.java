package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.gnaoh.util.other.Formatter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class QueueCommand implements IMusicCommand {

    @Override
    public void handle(CommandContext context) {
        String list = "";
        for (AudioTrack track : PlayerManager.INSTANCE.getMusicManager(context.getGuild()).scheduler.trackQueue) {
            list += String.format("`%s - %s`\n", track.getInfo().title, Formatter.formatTime(track.getDuration()));
        } 

        context.reply(list.isEmpty() ? "`No tracks available in queue!`" : list);
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
            throw new Exception(String.format("`Correct usage is [%squeue]`", Config.prefix));
    }
}
