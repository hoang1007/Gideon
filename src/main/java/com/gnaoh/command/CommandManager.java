package com.gnaoh.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import com.gnaoh.command.cmdinterface.ICommand;
import com.gnaoh.util.classutils.ClassGetter;

import org.clapper.util.classutil.ClassInfo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandManager {
    public static final CommandManager INSTANCE = new CommandManager();
    private final List<ICommand> commands = new ArrayList<>();

    public List<ICommand> getCommands() {
        return commands;
    }

    public ICommand getCommand(String name) throws NoSuchMethodException {
        name = name.toLowerCase(); // reformat to lower

        for (ICommand command : commands) {
            if (command.getName().equals(name) || command.getAliases().contains(name))
                return command;
        }

        throw new NoSuchMethodException("Command not found");
    }

    public void handle(GuildMessageReceivedEvent event, String prefix) {
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");

        try {
            String invoke = split[0];
            ICommand command = getCommand(invoke);

            List<String> args = Arrays.asList(split).subList(1, split.length);

            command.invoke(new CommandContext(event, args));
        } catch (Exception e) {
            e.printStackTrace();
            event.getChannel().sendMessage(e.getMessage()).queue();
        }
    }

    
    public void retrieveCommands() throws Exception {
        Collection<ClassInfo> classes = new ClassGetter().getSubClasses(ICommand.class);

        System.out.println("Loaded classes");
        for (ClassInfo classInfo : classes) {
            System.out.println(classInfo.getClassName());

            @SuppressWarnings("unchecked")
            final Class<? extends ICommand> iclass = (Class<? extends ICommand>) Class.forName(classInfo.getClassName());

            commands.add(iclass.getConstructor().newInstance());
        }
    }

    public void upsertCommands(JDA jda) {
        for (ICommand command : commands) {
            jda.upsertCommand(command.getName(), command.getDescription()).queue();
        }
    }
}
