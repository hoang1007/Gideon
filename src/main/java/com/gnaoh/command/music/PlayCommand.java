package com.gnaoh.command.music;

import java.util.List;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.cmdinterface.IMusicCommand;
import com.gnaoh.exception.music.NoMemberInVoiceChannel;
import com.gnaoh.exception.music.NotSameVoiceChannel;
import com.gnaoh.ienum.MemberType;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.gnaoh.util.web.UrlUtils;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.TextChannel;

public class PlayCommand implements IMusicCommand{
    @Override
    public void handle(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT),
                                memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if (!selfVoiceState.inVoiceChannel())
            context.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());

        play(context.getChannel(), String.join(" ", context.getArgs()));
    }

    void play(TextChannel channel, String link) {
        if (!UrlUtils.isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.INSTANCE.loadAndPlay(channel, link, false);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "play a song";
    }

    @Override
    public void checkParameters(List<String> args) throws Exception {
        if (args.isEmpty())
            throw new Exception(String.format("Correct usage is `%splay <youtube link> or <name of song>`", Config.prefix));  
    }
    
    @Override
    public void checkVoiceChannel(CommandContext context) throws Exception {
        final GuildVoiceState selfVoiceState = context.getVoiceState(MemberType.BOT);
        final GuildVoiceState memberVoiceState = context.getVoiceState(MemberType.NORMAL);

        if(!memberVoiceState.inVoiceChannel())
            throw new NoMemberInVoiceChannel();
        
        if (selfVoiceState.inVoiceChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            throw new NotSameVoiceChannel();
        }
    }
}
