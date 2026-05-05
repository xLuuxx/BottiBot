package fr.ynov.bottibot;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String getToken() {
        String token = dotenv.get("DISCORD_TOKEN");
        if (token == null || token.isBlank()) {
            throw new RuntimeException("DISCORD_TOKEN manquant dans le fichier .env");
        }
        return token;
    }

    public static String getPrefix() {
        String prefix = dotenv.get("BOT_PREFIX");
        return (prefix == null || prefix.isBlank()) ? "!" : prefix;
    }
}