public class GameManager {
    public static GameManager instance;

    private Player[] players;
    private Player currentPlayer;
    private int turnIndex;


    public GameManager() {
        if (instance == null) {
            instance = this;
        }

        InstantiatePlayers();
    }

    void InstantiatePlayers() {
        players = new Player[4];
        int i = 0;
        for (PlayerColor color : PlayerColor.values()) {
            players[i] = new Player(i + 1, color);
        }
        currentPlayer = players[0];
    }

    void NextTurn() {
        turnIndex++;
        turnIndex %= players.length;

        currentPlayer = players[turnIndex];
    }
}
