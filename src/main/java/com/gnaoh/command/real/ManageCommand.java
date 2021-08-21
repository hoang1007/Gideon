package com.gnaoh.command.real;

import java.util.List;
import com.gnaoh.command.Command;
import com.gnaoh.utilities.Secret;

import net.dv8tion.jda.api.entities.Message;

public class ManageCommand extends Command {
    public int limitMessageCount = 50;

    public void clear() {
            List<Message> messages = event.getChannel()
                                        .getHistory()
                                        .retrievePast(limitMessageCount).complete();

            messages.removeIf(m -> !(
                    m.getAuthor() == event.getJDA().getSelfUser() || 
                    (m.getAuthor() == event.getAuthor() && m.getContentRaw().contains(Secret.prefix))
                )
            );
            event.getChannel().deleteMessages(messages).queue()
    }
}
