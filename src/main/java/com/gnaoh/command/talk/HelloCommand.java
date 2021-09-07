package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;

public class HelloCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        context.reply("hi " + context.getEvent().getAuthor().getAsMention() + " ❤️");
    }

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getHelp() {
        return "Type hello and I'll hi back";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
