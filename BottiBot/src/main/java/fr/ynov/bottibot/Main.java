package fr.ynov.bottibot;

import fr.ynov.bottibot.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        try {
            String token = BotConfig.getToken();

            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new CommandListener())
                    .build();

            jda.awaitReady();
            System.out.println("Bot en ligne !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}