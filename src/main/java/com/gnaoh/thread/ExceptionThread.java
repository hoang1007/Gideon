package com.gnaoh.thread;

import com.gnaoh.command.CommandManager;
import com.gnaoh.command.CommandState;

public final class ExceptionThread {
    static int exceptionCount = 0;
    static CommandState lastState = CommandState.UNKNOWN;

    private static void Initialize() {
        lastState = CommandState.UNKNOWN;
        exceptionCount = 0;
    }

    public static void addState(CommandState state) {
        exceptionCount++;

        if (lastState != CommandState.FOUND)
            lastState = state;
    }

    public static boolean canThrowException() {
        boolean canThrow = false;
        if (exceptionCount == CommandManager.commandCount) {
            if (lastState == CommandState.NOTFOUND)
                canThrow = true;
            Initialize();
        }
        return canThrow;
    }
}
