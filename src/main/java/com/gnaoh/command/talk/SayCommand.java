package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;

public class SayCommand implements ICommand {

    @Override
    public void handle(CommandContext context) throws Exception {
        context.reply(String.join(" ", context.getArgs()));
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
            throw new InvalidArgumentException(String.format("`Correct usage is [%say <something>]`", Config.prefix));
    }
}
