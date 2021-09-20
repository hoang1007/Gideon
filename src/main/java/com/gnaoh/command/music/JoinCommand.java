package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.exception.InvalidArgumentException;
import com.gnaoh.exception.NoMemberInVoiceChannel;
import com.gnaoh.ienum.MemberType;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {
    @Override
    public void handle(Bot bot, List<String> args) throws Exception {
        final AudioManager audioManager = bot.getGuild().getAudioManager();
        final GuildVoiceState memberVoiceState = bot.getVoiceState(MemberType.NORMAL);
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (memberVoiceState.inVoiceChannel()) {
            audioManager.openAudioConnection(memberChannel);

            bot.reply("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName());
            
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
            throw new InvalidArgumentException("This command should not contain parameters");
    }
}
