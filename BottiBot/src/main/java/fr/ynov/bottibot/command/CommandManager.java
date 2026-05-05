package fr.ynov.bottibot.command;

import fr.ynov.bottibot.BotConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandManager is a class where all commands are registered and executed.
 * It contains a map of command names to their corresponding ICommand implementations.
 * Have two main methods:
 * Register and handle
 */
public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandManager() {
        register(new PingCommand());
    }

    public void register(ICommand command) {
        commands.put(command.getName(), command);
    }

    public void handle(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();
        String prefix = BotConfig.getPrefix();

        if (!message.startsWith(prefix)) return;

        String commandName = message.substring(prefix.length()).split("\\s+")[0].toLowerCase();

        ICommand command = commands.get(commandName);
        if (command != null) {
            command.execute(event);
        }
    }
}