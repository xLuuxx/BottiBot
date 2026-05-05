package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * ICommand is a interface where all commands must implement to be registered in the CommandManager.
 * It contains two methods:
 * - getName(): returns the name of the command, which is used to trigger it.
 * - execute(MessageReceivedEvent event): contains the logic to execute when the command is triggered.
 */
public interface ICommand {
    String getName();
    String getDescription();
    void execute(MessageReceivedEvent event, String[] args);
}