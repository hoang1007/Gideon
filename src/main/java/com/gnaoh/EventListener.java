package com.gnaoh;

import javax.annotation.Nonnull;

import com.gnaoh.command.CommandManager;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
    private final CommandManager commandManager = new CommandManager();
    
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage())
            return ;
        
        if (event.getMessage().getContentRaw().startsWith(Config.prefix)) {
            commandManager.handle(event, Config.prefix);
        }
    }
}
