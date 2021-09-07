package com.gnaoh.command;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.entities.GuildVoiceState;

public interface ICommand {
    void handle(CommandContext context);

    String getName();
    String getHelp();
    
    /**
     * Check parameters required by the command
     * @throws Exception Invalid parameters
     */
    void checkParameters(List<String> args) throws Exception;

    default List<String> getAliases() {
        return Arrays.asList();
    }

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
}
