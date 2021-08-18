package com.gnaoh.command.real;

import com.gnaoh.command.Command;
import com.gnaoh.utilities.numerics.Bignum;
import com.gnaoh.utilities.numerics.Calculator;

public class NumericCommand extends Command {
    public void add(String param1, String param2) {
        Bignum bn1, bn2;
        bn1 = bn2 = Bignum.zero;
        try {
            bn1 = Bignum.Parse(param1);
            bn2 = Bignum.Parse(param2);
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage()).queue();
            return ;
        }

        event.getChannel().sendMessage(Calculator.add(bn1, bn2).toString()).queue();
    }

    public void multi(String param1, String param2) {
        Bignum bn1, bn2;
        bn1 = bn2 = Bignum.zero;

        try {
            bn1 = Bignum.Parse(param1);
            bn2 = Bignum.Parse(param2);
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage()).queue();
            return ;
        }

        event.getChannel().sendMessage(Calculator.multiply(bn1, bn2).toString()).queue();
    }

    public void pow(String param1, String param2) {
        Bignum bn1;
        long k;

        try {
            bn1 = Bignum.Parse(param1);
            k = Long.parseLong(param2);
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage()).queue();
            return ;
        }

        event.getChannel().sendMessage(Calculator.power(bn1, k).toString()).queue();
    }
}
