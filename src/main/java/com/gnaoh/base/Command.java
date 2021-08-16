package com.gnaoh.base;

import java.lang.reflect.Method;
import java.util.Arrays;
import com.gnaoh.utilities.Secret;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class Command extends ListenerAdapter {
    protected GuildMessageReceivedEvent event;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        this.event = event; // update event

        String[] args = event.getMessage().getContentRaw().split(" ");

        Method method = null;
        Class<?>[] paramTypes = null;

        if (args[0].substring(0, 1).equalsIgnoreCase(Secret.prefix)) {
            try {
                String methodName = args[0].substring(1).toLowerCase();

                try {
                    if (args.length > 1) {
                        paramTypes = new Class[args.length - 1];
                        for (int i = 0; i < paramTypes.length; i++) {
                            paramTypes[i] = String.class;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                method = this.getClass().getMethod(methodName, paramTypes);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            try {
                String[] params = null;

                try {
                    params = Arrays.copyOfRange(args, 1, args.length - 1);
                } catch (Exception e) {
                }

                method.invoke(this, (Object[]) params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println(args[0] + " isn't a command");
    }
}
