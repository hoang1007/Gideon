package com.gnaoh.command.cmdinterface;

import com.gnaoh.command.CommandContext;
import com.gnaoh.exception.music.*;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface IMusicCommand extends ICommand {
    default void checkVoiceChannel(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getSelfMember().getVoiceState();
        final GuildVoiceState memberVoiceState = context.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();
        
        if (!selfVoiceState.inVoiceChannel())
            throw new NotSelfInVoiceChannel();

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }

    @Override 
    default void invoke(CommandContext context) {
        try {
            checkParameters(context.getArgs());
            checkVoiceChannel(context);

            handle(context);
        }
        catch (Exception e) {
            context.reply(e.getMessage());
        }
    }
}
