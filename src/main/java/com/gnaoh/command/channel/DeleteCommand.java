package com.gnaoh.command.channel;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.ienum.UserType;

import net.dv8tion.jda.api.entities.Message;

public class DeleteCommand implements ICommand {
    public int limitMessageCount = 50;

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        List<Message> messages = bot.getChannel().getHistory().retrievePast(limitMessageCount).complete();

        messages.removeIf(m -> 
                !(m.getAuthor() == bot.getJDA().getSelfUser()
                || (m.getAuthor() == bot.getUser(UserType.AUTHOR) && m.getContentRaw().contains(bot.getConfig().getPrefix())))
        );
        
        bot.getChannel().deleteMessages(messages).queue();
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
            throw new InvalidArgumentException("`This command should not contain parameters`");
    }
}
