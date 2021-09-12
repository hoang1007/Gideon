package com.gnaoh.command.talk;

import java.util.List;

import com.gnaoh.assets.AnimeGifs;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;

public class HiCommand implements ICommand {
    @Override
    public void handle(CommandContext context) {
        String target = context.getArgs().isEmpty() ? 
            context.getEvent().getAuthor().getAsMention() : String.join(" ", context.getArgs());
            
        context.reply("hello " + target + " ❤️", 
            new EmbedBuilder().setImage(AnimeGifs.getRandom(AnimeGifs.shy)).build());
    }

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public String getDescription() {
        return "hi me and I'll hello back or you can say hi to anyone";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub
        
    }
}
