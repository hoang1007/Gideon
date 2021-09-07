package com.gnaoh.command.music;

import com.gnaoh.Config;
import com.gnaoh.command.CommandContext;
import com.gnaoh.command.ICommand;
import com.gnaoh.util.lavaplayer.PlayerManager;
import com.gnaoh.util.web.UrlUtils;

import net.dv8tion.jda.api.entities.TextChannel;

public class PlayCommand implements ICommand {
    @Override
    public void handle(CommandContext context) {
        try {
            checkVoiceChannel(context);
            play(context.getChannel(), String.join(" ", context.getArgs()));
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }

    void play(TextChannel channel, String link) {
        if (!UrlUtils.isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.INSTANCE.loadAndPlay(channel, link);
    }

    @Override
    public
    void checkParameters(CommandContext context) throws Exception {
        if (context.getArgs().isEmpty())
            throw new Exception(String.format("Correct usage is `%splay <youtube link> or <name of song>`", Config.prefix));
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return String.format("Plays a song\nUsage: `%splay <youtube link>`", Config.token);
    }
    
}
