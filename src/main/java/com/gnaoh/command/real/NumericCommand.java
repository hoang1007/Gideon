package com.gnaoh.command.real;

import com.gnaoh.command.Command;
import com.gnaoh.utilities.AnimeGifs;
import com.gnaoh.utilities.numerics.Bignum;
import com.gnaoh.utilities.numerics.Calculator;
import net.dv8tion.jda.api.EmbedBuilder;

public class NumericCommand extends Command {
    public void add(String param1, String param2) {
        try {
            Bignum bn1 = Bignum.Parse(param1);
            Bignum bn2 = Bignum.Parse(param2);

            event.getChannel().sendMessage(Calculator.add(bn1, bn2).toString()).queue();
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage())
            .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
        }
    }

    public void multi(String param1, String param2) {
        try {
            Bignum bn1 = Bignum.Parse(param1);
            Bignum bn2 = Bignum.Parse(param2);

            event.getChannel().sendMessage(Calculator.multiply(bn1, bn2).toString()).queue();
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage())
            .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
        }
    }

    public void pow(String param1, String param2) {
        try {
            Bignum bn1 = Bignum.Parse(param1);
            long k = Long.parseLong(param2);

            event.getChannel().sendMessage(Calculator.power(bn1, k).toString()).queue();
        } catch (IllegalArgumentException e) {
            event.getChannel().sendMessage(e.getMessage())
            .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
        }
    }
}
