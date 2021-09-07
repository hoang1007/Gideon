package com.gnaoh.command.numerics;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;

public class AddCommand implements ICommand {
    public final int paramCount = 2;

    @Override
    public void handle(CommandContext context) {
        try {
            List<String> args = context.getArgs();
            Bignum a = Bignum.Parse(args.get(0));
            Bignum b = Bignum.Parse(args.get(1));

            context.reply(Calculator.add(a, b).toString());
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getHelp() {
        return "calculate sum of two big numbers";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.size() != paramCount)
            throw new Exception(String.format("Correct usage is `%sadd <bignum1> <bignum2>`", Config.prefix));
    }
}
