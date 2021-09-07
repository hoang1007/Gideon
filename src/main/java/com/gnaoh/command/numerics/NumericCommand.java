// package com.gnaoh.command.numerics;

// import com.gnaoh.command.Command;
// import com.gnaoh.util.gifs.AnimeGifs;
// import com.gnaoh.util.numerics.Bignum;
// import com.gnaoh.util.numerics.Calculator;
// import net.dv8tion.jda.api.EmbedBuilder;

// public class NumericCommand extends Command {
//     public void add(String param1, String param2) {
//         try {
//             Bignum bn1 = Bignum.Parse(param1);
//             Bignum bn2 = Bignum.Parse(param2);

//             event.getChannel().sendMessage(Calculator.add(bn1, bn2).toString()).queue();
//         } catch (Exception e) {
//             event.getChannel().sendMessage(e.getMessage())
//             .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
//         }
//     }

//     public void multi(String param1, String param2) {
//         try {
//             Bignum bn1 = Bignum.Parse(param1);
//             Bignum bn2 = Bignum.Parse(param2);

//             event.getChannel().sendMessage(Calculator.multiply(bn1, bn2).toString()).queue();
//         } catch (Exception e) {
//             event.getChannel().sendMessage(e.getMessage())
//             .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
//         }
//     }

//     public void pow(String param1, String param2) {
//         try {
//             Bignum bn1 = Bignum.Parse(param1);
//             Bignum.Parse(param2);
//             long k = Long.parseLong(param2);
//             String resStr = Calculator.power(bn1, k).toString();

//             if (resStr.length() < 50) event.getChannel().sendMessage(resStr).queue();
//             else event.getChannel().sendMessage(resStr + "\nVai lon kho day!")
//                 .embed(new EmbedBuilder().setImage(AnimeGifs.hard.one).build()).queue();
//         } catch (IllegalArgumentException e) {
//             event.getChannel().sendMessage(e.getMessage())
//             .embed(new EmbedBuilder().setImage(AnimeGifs.angry.getRandom()).build()).queue();
//         }
//     }
// }
