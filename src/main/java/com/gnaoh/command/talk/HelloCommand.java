package com.gnaoh.command.talk;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;

public class HelloCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        context.getChannel().sendMessage("hi " + context.getEvent().getAuthor().getAsMention() + " ❤️").queue();
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
    public void checkParameters(CommandContext context) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
