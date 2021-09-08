package com.gnaoh.command.music;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class QueueCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        BlockingQueue<AudioTrack> tracks = PlayerManager.INSTANCE.getMusicManager(context.getGuild()).scheduler.trackQueue;

        String list = "";
        for (AudioTrack track : tracks) {
            list += track.getInfo().title + "\n";
        } 

        context.reply(list);
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
        // TODO Auto-generated method stub
    }
}
