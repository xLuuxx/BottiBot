package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * PollCommand allows the user to use !poll to create a poll with an question and two answers. The other user can answer using reaction.
 */
public class PollCommand implements ICommand {

    @Override
    public String getName() {
        return "poll";
    }

    @Override
    public String getDescription() {
        return "Crée un sondage";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String content = String.join(" ", args).trim();
        String[] parts = content.split("\\|");

        if (parts.length < 3) {
            event.getChannel().sendMessage("Utilisation : !poll <question> | <option1> | <option2>").queue();
            return;
        }

        String question = parts[0].trim();
        String option1 = parts[1].trim();
        String option2 = parts[2].trim();

        String pollMessage = "**Sondage**\n"
                + "**Question :** " + question + "\n\n"
                + "1. " + option1 + "\n"
                + "2. " + option2;

        event.getChannel().sendMessage(pollMessage).queue(message -> {
            message.addReaction(Emoji.fromUnicode("1️⃣")).queue();
            message.addReaction(Emoji.fromUnicode("2️⃣")).queue();
        });
    }
}