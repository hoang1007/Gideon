package com.gnaoh.command.cmdinterface;

import com.gnaoh.command.CommandContext;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface IMusicCommand extends ICommand {
    default void checkVoiceChannel(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getSelfMember().getVoiceState();
        final GuildVoiceState memberVoiceState = context.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel())
            throw new Exception("Mày đã vào voice channel đâu đm. Vào đi!");
        
        if (!selfVoiceState.inVoiceChannel())
            throw new Exception("Tao chưa vào voice channel!");

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new Exception("Ôi bạn ơi, bạn phải join vào channel này mới chơi được " + selfVoiceState.getChannel().getName());
        }
    }

    @Override 
    default void invoke(CommandContext context) {
        try {
            checkParameters(context.getArgs());
            checkVoiceChannel(context);
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }
}
