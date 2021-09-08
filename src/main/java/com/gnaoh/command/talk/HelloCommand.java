package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;

public class HelloCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        String target = context.getArgs().isEmpty() ? 
            context.getEvent().getAuthor().getAsMention() : String.join(" ", context.getArgs());
           
        context.reply("hi " + target + " ❤️");
    }

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getDescription() {
        return "type hello and I'll hi back or you can say hello to anyone";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {

    }
}
