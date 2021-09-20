package com.gnaoh.command.numerics;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;

public class MultiCommand implements ICommand {

    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        Bignum a = Bignum.Parse(args.get(0));
        Bignum b = Bignum.Parse(args.get(1));

        bot.reply(Calculator.multiply(a, b).toString());
    }

    @Override
    public String getName() {
        return "mul";
    }

    @Override
    public String getDescription() {
        return "multiply two big numbers";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        final int paramCount = 2;
        if (args.size() != paramCount)
            throw new InvalidArgumentException("Must provide two numbers`");
    }
}
