package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.music.NoMemberInVoiceChannel;
import com.gnaoh.ienum.MemberType;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext context) throws Exception {
        final AudioManager audioManager = context.getGuild().getAudioManager();
        final GuildVoiceState memberVoiceState = context.getVoiceState(MemberType.NORMAL);
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (memberVoiceState.inVoiceChannel()) {
            audioManager.openAudioConnection(memberChannel);

            context.reply("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName());
            
        } else
            throw new NoMemberInVoiceChannel();
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "help me join your voice channel";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (!args.isEmpty())
            throw new Exception(String.format("`Correct usage is [%sjoin]`", Config.get("PREFIX")));
    }
}
