package com.gnaoh.command.channel;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.ienum.UserType;

import net.dv8tion.jda.api.entities.Message;

public class DeleteCommand implements ICommand {
    public int limitMessageCount = 50;

    @Override
    public void handle(CommandContext context) throws Exception {
        List<Message> messages = context.getChannel().getHistory().retrievePast(limitMessageCount).complete();

        messages.removeIf(m -> 
                !(m.getAuthor() == context.getJDA().getSelfUser()
                || (m.getAuthor() == context.getUser(UserType.AUTHOR) && m.getContentRaw().contains(Config.INSTANCE.get("PREFIX"))))
        );
        
        context.getChannel().deleteMessages(messages).queue();
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
            throw new Exception(String.format("`Correct usage is %sdelete`", Config.INSTANCE.get("PREFIX")));
    }
}
