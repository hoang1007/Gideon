package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;

public class SayCommand implements ICommand {

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        bot.reply(String.join(" ", args));
    }

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "let me say something";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty())
            throw new InvalidArgumentException("`Missing argument`");
    }
}
