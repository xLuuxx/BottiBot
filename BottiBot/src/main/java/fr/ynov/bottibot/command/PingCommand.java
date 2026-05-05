package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * PingCommand allow the user to use !Ping and to be answered by Pong ! by the bot
 */
public class PingCommand implements ICommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong !").queue();
    }
}