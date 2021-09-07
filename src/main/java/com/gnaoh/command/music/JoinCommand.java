package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {
        final AudioManager audioManager = context.getGuild().getAudioManager();
        final GuildVoiceState memberVoiceState = context.getMember().getVoiceState();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (memberVoiceState.inVoiceChannel()) {
            audioManager.openAudioConnection(memberChannel);
            context.reply("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName());
        }
        else 
            context.reply("Bạn vào trước đi rồi mình vào");
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Help me join your voice channel";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        // TODO Auto-generated method stub

    }
}
