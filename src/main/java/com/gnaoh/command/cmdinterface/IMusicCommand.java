package com.gnaoh.command.cmdinterface;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.exception.NoMemberInVoiceChannel;
import com.gnaoh.exception.NotSameVoiceChannel;
import com.gnaoh.exception.NotSelfInVoiceChannel;
import com.gnaoh.ienum.MemberType;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface IMusicCommand extends ICommand {
    default void checkVoiceChannel(Bot bot) throws Exception {
        final GuildVoiceState selfVoiceState = bot.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = bot.getVoiceState(MemberType.NORMAL);

        if(!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();
        
        if (!selfVoiceState.inVoiceChannel())
            throw new NotSelfInVoiceChannel();

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }

    @Override 
    default void invoke(Bot bot, List<String> args) {
        try {
            checkParameters(args);
            checkVoiceChannel(bot);

            handle(bot, args);
        }
        catch (Exception e) {
            bot.reply(e.getMessage());
        }
    }
}
