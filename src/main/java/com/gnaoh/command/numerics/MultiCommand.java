package com.gnaoh.command.numerics;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;

public class MultiCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            List<String> args = context.getArgs();
            checkParameters(args);
            Bignum a = Bignum.Parse(args.get(0));
            Bignum b = Bignum.Parse(args.get(1));

            context.reply(Calculator.multiply(a, b).toString());
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
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
            throw new Exception(String.format("Correct usage is `%smul <bignum1> <bignum2>`", Config.prefix));
    }
}
