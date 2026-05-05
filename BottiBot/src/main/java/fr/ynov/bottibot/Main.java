package fr.ynov.bottibot;

import fr.ynov.bottibot.command.RpsButtonListener;
import fr.ynov.bottibot.command.RpsSlashCommandListener;
import fr.ynov.bottibot.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(BotConfig.getToken())
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new CommandListener())
                .addEventListeners(new RpsSlashCommandListener())
                .addEventListeners(new RpsButtonListener())
                .build()
                .awaitReady();

        jda.upsertCommand("rps", "Défie un membre à pierre-feuille-ciseaux")
                .addOption(OptionType.USER, "adversaire", "Le membre à défier", true)
                .queue();
    }
}