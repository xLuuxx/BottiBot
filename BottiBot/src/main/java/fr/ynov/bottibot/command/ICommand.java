package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * ICommand is a interface where all commands must implement to be registered in the CommandManager.
 * It contains three methods:
 *  - getName(): returns the command name.
 *  - getDescription(): returns the command description.
 *  - execute(MessageReceivedEvent event, String[] args): executes the command logic.
 */
public interface ICommand {
    String getName();
    String getDescription();
    void execute(MessageReceivedEvent event, String[] args);
}