package com.gnaoh.util.commands;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.gnaoh.Bot;
import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.util.classutils.ClassFinder;

import net.dv8tion.jda.api.JDA;

public class CommandManager {
    private final Hashtable<String, ICommand> commands = new Hashtable<String, ICommand>();

    // public List<ICommand> getCommands() {
    //     return commands;
    // }

    public ICommand getCommand(String name) throws NoSuchMethodException {
        name = name.toLowerCase(); // reformat to lower

        ICommand command = commands.get(name);

        if (command == null) {
            throw new NoSuchMethodException("Command not found");
        } else {
            return command;
        }
    }

    public void handle(Bot bot) {
        String[] split = bot.getEvent().getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(bot.getConfig().getPrefix()), "")
                .split("\\s+");

        try {
            String invoke = split[0];
            ICommand command = getCommand(invoke);

            List<String> args = Arrays.asList(split).subList(1, split.length);

            command.invoke(bot, args);
        } catch (Exception e) {
            e.printStackTrace();
            bot.reply(e.getMessage());
        }
    }

    
    public void retrieveCommands() throws Exception {
        Set<Class<? extends ICommand>> classes = new ClassFinder<>(ICommand.class).getSubClasses();

        System.out.println("Loading classes...");
        for (Class<? extends ICommand> clazz : classes) {
            System.out.println(clazz.getSimpleName());
            ICommand command = clazz.getConstructor().newInstance();
            commands.put(command.getName(), command);
        }
    }

    public void upsertCommands(JDA jda) {
        for (ICommand command : commands.values()) {
            jda.upsertCommand(command.getName(), command.getDescription()).queue();
        }
    }
}
