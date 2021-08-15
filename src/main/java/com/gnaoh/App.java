package com.gnaoh;

import javax.security.auth.login.LoginException;
import com.gnaoh.utilities.Secret;

import net.dv8tion.jda.api.*;

public class App 
{
    public static void main( String[] args ) throws LoginException, InterruptedException
    {
        // Initialize gideon
        JDA jda = JDABuilder.createDefault(Secret.token)
                    .setStatus(OnlineStatus.ONLINE)
                    .addEventListeners(new CommandHandler())
                    .build();
        jda.awaitReady();
    }
}
