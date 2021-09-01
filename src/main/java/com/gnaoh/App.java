package com.gnaoh;

import javax.security.auth.login.LoginException;
import com.gnaoh.command.real.*;
import com.gnaoh.util.Secret;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class App 
{
    public static void main( String[] args ) throws LoginException, InterruptedException
    {
        // Initialize gideon
        JDA jda = JDABuilder.createDefault(Secret.token)
                    .setStatus(OnlineStatus.ONLINE)
                    .addEventListeners(new TalkCommand())
                    .addEventListeners(new NumericCommand())
                    .addEventListeners(new ManageCommand())
                    .build();
        jda.awaitReady();
    }
}
