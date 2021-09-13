package com.gnaoh;

import javax.annotation.Nonnull;

import com.gnaoh.command.CommandManager;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage())
            return ;
        
        if (event.getMessage().getContentRaw().startsWith(Config.get("PREFIX"))) {
            CommandManager.INSTANCE.handle(event, Config.get("PREFIX"));
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            CommandManager.INSTANCE.retrieveCommands();

            CommandManager.INSTANCE.upsertCommands(event.getJDA());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
