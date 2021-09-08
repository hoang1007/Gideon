package com.gnaoh.command.cmdinterface;

import java.util.Arrays;
import java.util.List;

import com.gnaoh.command.CommandContext;

public interface ICommand {
    void handle(CommandContext context) throws Exception;

    String getName();
    String getDescription();
    
    /**
     * Check everything required to execute the command
     * @throws Exception
     */
    void checkParameters(List<String> args) throws Exception;

    default List<String> getAliases() {
        return Arrays.asList();
    }

    default void invoke(CommandContext context) {
        try {
            checkParameters(context.getArgs());
            
            handle(context);
        } catch (Exception e) {
            context.reply(e.getMessage());
        }
    }
}
