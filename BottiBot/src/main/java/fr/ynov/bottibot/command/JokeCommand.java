package fr.ynov.bottibot.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.ynov.bottibot.BotConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * JokeCommand allow the user to use !joke in order to get a joke from a public API
 */
public class JokeCommand implements ICommand {

    @Override
    public String getName() {
        return "joke";
    }

    @Override
    public String getDescription() {
        return "Affiche une blague aléatoire depuis une API. ";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String jokeApiUrl = BotConfig.getJokeApiUrl();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(jokeApiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                event.getChannel().sendMessage("Erreur : impossible de récupérer une blague.").queue();
                return;
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String setup = json.get("setup").getAsString();
            String punchline = json.get("punchline").getAsString();

            event.getChannel().sendMessage(setup + "\n" + punchline).queue();

        } catch (IOException | InterruptedException e) {
            event.getChannel().sendMessage("Erreur lors de l'appel à l'API des blagues.").queue();
            e.printStackTrace();
        }
    }
}