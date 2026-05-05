package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Map;


/**
 * Allow the user to use !help in order to see all the bot's command.
 */
public class HelpCommand implements ICommand {

    private final Map<String, ICommand> commands;

    public HelpCommand(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Liste des commands";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Help");
        embedBuilder.setColor(Color.GREEN);

        for (ICommand command : commands.values()) {
            embedBuilder.addField(
                    "!" + command.getName(),
                    command.getDescription(),
                    false
            );
        }

        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }
}
