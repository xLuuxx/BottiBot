package fr.ynov.bottibot.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RpsGameManager is a  class that manages active Rock-Paper-Scissors games.
 * It allows adding, retrieving, and removing games based on their unique game IDs.
 */
public class RpsGameManager {

    private static final Map<String, RpsGame> games = new ConcurrentHashMap<>();

    public static void addGame(RpsGame game) {
        games.put(game.getGameId(), game);
    }

    public static RpsGame getGame(String gameId) {
        return games.get(gameId);
    }

    public static void removeGame(String gameId) {
        games.remove(gameId);
    }

    public static class RpsGame {
        private final String gameId;
        private final long channelId;
        private final long player1Id;
        private final long player2Id;
        private String player1Choice;
        private String player2Choice;
        private boolean finished;

        public RpsGame(String gameId, long channelId, long player1Id, long player2Id) {
            this.gameId = gameId;
            this.channelId = channelId;
            this.player1Id = player1Id;
            this.player2Id = player2Id;
        }

        public String getGameId() {
            return gameId;
        }

        public long getChannelId() {
            return channelId;
        }

        public long getPlayer1Id() {
            return player1Id;
        }

        public long getPlayer2Id() {
            return player2Id;
        }

        public String getPlayer1Choice() {
            return player1Choice;
        }

        public String getPlayer2Choice() {
            return player2Choice;
        }

        public void setPlayer1Choice(String player1Choice) {
            this.player1Choice = player1Choice;
        }

        public void setPlayer2Choice(String player2Choice) {
            this.player2Choice = player2Choice;
        }

        public boolean isComplete() {
            return player1Choice != null && player2Choice != null;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }
    }
}