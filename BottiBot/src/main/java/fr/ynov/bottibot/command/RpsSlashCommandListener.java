package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * RpsSlashCommandListener is a listener for the /rps slash command, allowing users to challenge each other to a game of rock-paper-scissors.
 * When a user invokes the command, it checks for a valid opponent and creates a new game instance, sending a message with buttons for the players to make their choices.
 */
public class RpsSlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("rps")) return;

        Member opponent = event.getOption("adversaire").getAsMember();

        if (opponent == null) {
            event.reply("Utilisateur invalide.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        if (opponent.getUser().isBot()) {
            event.reply("Tu ne peux pas défier un bot.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        if (opponent.getIdLong() == event.getUser().getIdLong()) {
            event.reply("Tu ne peux pas te défier toi-même.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        String gameId = UUID.randomUUID().toString();

        RpsGameManager.RpsGame game = new RpsGameManager.RpsGame(
                gameId,
                event.getChannel().getIdLong(),
                event.getUser().getIdLong(),
                opponent.getIdLong()
        );

        RpsGameManager.addGame(game);

        event.reply(event.getUser().getAsMention() + " défie " + opponent.getAsMention()
                        + " à pierre-feuille-ciseaux !\n"
                        + "Chaque joueur doit cliquer sur un bouton ci-dessous. Votre choix restera privé.")
                .addComponents(ActionRow.of(
                        Button.primary("rps:" + gameId + ":pierre", "Pierre"),
                        Button.success("rps:" + gameId + ":feuille", "Feuille"),
                        Button.danger("rps:" + gameId + ":ciseaux", "Ciseaux")
                ))
                .queue();
    }
}