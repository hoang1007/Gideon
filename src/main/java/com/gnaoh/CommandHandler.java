package com.gnaoh;

import java.lang.reflect.Method;
import com.gnaoh.command.Commands;
import com.gnaoh.utilities.Secret;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHandler extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return ;

        String[] args = event.getMessage().getContentRaw().split(" ");

        Method method = null;
        Commands commands = new Commands(event);
        
        if (args[0].substring(0, 1).equalsIgnoreCase(Secret.prefix)) {
            try {
                String methodName = args[0].substring(1).toLowerCase();
                method = commands.getClass().getMethod(methodName, String.class);
            }
            catch (Exception e) {
                event.getChannel().sendMessage("Can't invoke command!").queue();
            }

            try {
                String param = args.length == 2 ? args[1] : null;
                method.invoke(commands, param);
            }
            catch (Exception e) {
                System.out.println("Error invoke command. " + e.getMessage());
            }
        }
        else System.out.println(args[0] + " isn't a command");
    }
}
