package com.gnaoh;

import javax.security.auth.login.LoginException;
import com.gnaoh.utilities.Config;
import net.dv8tion.jda.api.*;

public class App 
{
    public static void main( String[] args ) throws LoginException, InterruptedException
    {
        // Initialize gideon
        JDA jda = JDABuilder.createDefault(Config.getProperty("token"))
                    .setStatus(OnlineStatus.ONLINE)
                    .addEventListeners(new CommandHandler())
                    .build();
        jda.awaitReady();
    }
}
