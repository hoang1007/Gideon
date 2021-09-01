package com.gnaoh.command;

import java.lang.reflect.Method;
import java.util.Arrays;
import com.gnaoh.Config;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class Command extends ListenerAdapter {
    protected GuildMessageReceivedEvent event;

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Ready");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        this.event = event; // update event

        String message = event.getMessage().getContentRaw();
        String[] args = message.split(" ");

        if (args[0].substring(0, 1).equalsIgnoreCase(Config.prefix)) {
            try {
                String methodName = args[0].substring(1).toLowerCase();
                Method method = findMethod(methodName, args.length - 1);

                try {
                    args = message.split(" ", method.getParameterCount() + 1);
                    String[] params = Arrays.copyOfRange(args, 1, args.length);
    
                    method.invoke(this, (Object[]) params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private Method findMethod(String methodName, int paramCount) throws Exception {
        if (paramCount == 0) {
            try {
                return this.getClass().getMethod(methodName);
            } catch (Exception e) {
                throw e;
            }
        } else {
            Class<?>[] paramTypes = new Class[paramCount];

            for (int i = 0; i < paramCount; i++) {
                paramTypes[i] = String.class;
            }

            for (int i = paramCount; i >= 0; i--) {
                try {
                    return this.getClass().getMethod(methodName, Arrays.copyOfRange(paramTypes, 0, i));
                } catch (Exception e) {
                }
            }

            throw new Exception("Command not found");
        }
    }
}
