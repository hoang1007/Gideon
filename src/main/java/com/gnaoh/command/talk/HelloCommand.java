package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;

public class HelloCommand implements ICommand {

    @Override
    public void handle(Bot bot, List<String> args) {
        String target = args.isEmpty() ? 
            bot.getEvent().getAuthor().getAsMention() : String.join(" ", args);
           
        bot.reply("hi " + target + " ❤️");
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
