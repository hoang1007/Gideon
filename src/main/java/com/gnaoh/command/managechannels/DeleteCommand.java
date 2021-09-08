package com.gnaoh.command.managechannels;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DeleteCommand implements ICommand {
    public int limitMessageCount = 50;

    @Override
    public void handle(CommandContext context) {
        GuildMessageReceivedEvent event = context.getEvent();

        List<Message> messages = event.getChannel().getHistory().retrievePast(limitMessageCount).complete();

        messages.removeIf(m -> 
                !(m.getAuthor() == event.getJDA().getSelfUser()
                || (m.getAuthor() == event.getAuthor() && m.getContentRaw().contains(Config.prefix)))
        );
        
        event.getChannel().deleteMessages(messages).queue();
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "clear all command that you were called";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception(String.format("`Correct usage is %sdelete`", Config.prefix));
    }
}
