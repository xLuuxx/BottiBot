package fr.ynov.bottibot.command;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * RpsButtonListener is a listener for button interactions related to the rock-paper-scissors game. It handles players' choices,
 * updates the game state, and determines the winner once both players have made their selections.
 */
public class RpsButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String componentId = event.getComponentId();

        if (!componentId.startsWith("rps:")) return;

        String[] parts = componentId.split(":");
        if (parts.length != 3) {
            event.reply("Interaction invalide.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        String gameId = parts[1];
        String choice = parts[2];

        RpsGameManager.RpsGame game = RpsGameManager.getGame(gameId);

        if (game == null || game.isFinished()) {
            event.reply("Cette partie n'est plus disponible.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        long userId = event.getUser().getIdLong();

        if (userId != game.getPlayer1Id() && userId != game.getPlayer2Id()) {
            event.reply("Tu ne fais pas partie de cette partie.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        if (userId == game.getPlayer1Id()) {
            if (game.getPlayer1Choice() != null) {
                event.reply("Tu as déjà choisi.")
                        .setEphemeral(true)
                        .queue();
                return;
            }
            game.setPlayer1Choice(choice);
        } else if (userId == game.getPlayer2Id()) {
            if (game.getPlayer2Choice() != null) {
                event.reply("Tu as déjà choisi.")
                        .setEphemeral(true)
                        .queue();
                return;
            }
            game.setPlayer2Choice(choice);
        }

        event.reply("Ton choix a bien été enregistré : **" + choice + "**.")
                .setEphemeral(true)
                .queue();

        if (!game.isComplete()) return;

        game.setFinished(true);

        String resultMessage = buildResultMessage(game);

        MessageChannel channel = event.getJDA().getChannelById(MessageChannel.class, game.getChannelId());
        if (channel != null) {
            channel.sendMessage(resultMessage).queue();
        }

        event.getMessage().editMessage("Partie terminée.")
                .setComponents()
                .queue();

        RpsGameManager.removeGame(gameId);
    }

    private String buildResultMessage(RpsGameManager.RpsGame game) {
        String p1 = game.getPlayer1Choice();
        String p2 = game.getPlayer2Choice();

        if (p1.equals(p2)) {
            return "Égalité ! Les deux joueurs ont choisi **" + p1 + "**.";
        }

        boolean player1Wins =
                (p1.equals("pierre") && p2.equals("ciseaux")) ||
                        (p1.equals("feuille") && p2.equals("pierre")) ||
                        (p1.equals("ciseaux") && p2.equals("feuille"));

        if (player1Wins) {
            return "<@" + game.getPlayer1Id() + "> gagne ! ("
                    + p1 + " bat " + p2 + ")";
        } else {
            return "<@" + game.getPlayer2Id() + "> gagne ! ("
                    + p2 + " bat " + p1 + ")";
        }
    }
}