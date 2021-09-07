package com.gnaoh.command.numerics;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.numerics.Bignum;
import com.gnaoh.util.numerics.Calculator;

public class PowerCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        try {
            List<String> args = context.getArgs();
            checkParameters(args);
            
            Bignum a = Bignum.Parse(args.get(0));
            Long b = Long.parseLong(args.get(1));
            context.reply(Calculator.power(a, b).toString());
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "pow";
    }

    @Override
    public String getHelp() {
        return "Calculate power of two big numbers";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        final int paramCount = 2;
        if (args.size() != paramCount) {
            throw new Exception(String.format("Correct usage is `%spow <bignum1> <long integer>`", Config.prefix));
        }
    }
    
}
