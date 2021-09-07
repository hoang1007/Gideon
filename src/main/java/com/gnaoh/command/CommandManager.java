package com.gnaoh.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.gnaoh.command.music.*;
import com.gnaoh.command.talk.*;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        // Music commands
        addCommand(new PlayCommand());
        addCommand(new JoinCommand());
        addCommand(new StopCommand());
        addCommand(new ClearQueueCommand());
        addCommand(new SkipCommand());
        addCommand(new PauseCommand());
        addCommand(new ResumeCommand());
        // Numeric commands
        
        // Talk commands
        addCommand(new HelloCommand());
    }

    private void addCommand(ICommand command) {
        commands.add(command);
    }

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
        String[] split = event.getMessage().getContentRaw()
                        .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                        .split("\\s+");
    
        try {
            String invoke = split[0];
            ICommand command = getCommand(invoke);

            List<String> args = Arrays.asList(split).subList(1, split.length);

            command.handle(new CommandContext(event, args));
        } catch (Exception e) {
            event.getChannel().sendMessage(e.getMessage()).queue();
        }
    }
}
