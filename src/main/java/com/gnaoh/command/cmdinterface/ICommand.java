package com.gnaoh.command.cmdinterface;

import java.util.Arrays;
import java.util.List;

import com.gnaoh.Bot;

public interface ICommand {
    void handle(Bot bot, List<String> args) throws Exception;

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

    default void invoke(Bot bot, List<String> args) {
        try {
            checkParameters(args);
            
            handle(bot, args);
        } catch (Exception e) {
            bot.reply(e.getMessage());
        }
    }
}
