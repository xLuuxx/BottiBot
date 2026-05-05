package fr.ynov.bottibot.listener;

import fr.ynov.bottibot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * CommandListener is a listener that listens for message events and delegates
 * command handling to the CommandManager.
 */
public class CommandListener extends ListenerAdapter {

    private final CommandManager commandManager = new CommandManager();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        commandManager.handle(event);
    }
}