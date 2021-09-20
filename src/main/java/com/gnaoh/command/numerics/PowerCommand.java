package com.gnaoh.command.numerics;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;

public class PowerCommand implements ICommand {
    @Override
    public void handle(Bot bot, List<String> args) throws Exception{
        Bignum a = Bignum.Parse(args.get(0));
        Long b = Long.parseLong(args.get(1));
        bot.reply(Calculator.power(a, b).toString());
    }

    @Override
    public String getName() {
        return "pow";
    }

    @Override
    public String getDescription() {
        return "Calculate power of two big numbers";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        final int paramCount = 2;
        if (args.size() != paramCount) {
            throw new InvalidArgumentException("Must provide two numbers`");
        }
    }
}
