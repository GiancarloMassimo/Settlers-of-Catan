import java.awt.event.KeyEvent;

public class GameManager implements KeyEventHandler {
    public static GameManager instance;

    private Player[] players;
    private Player currentPlayer;
    private int turnIndex;


    public GameManager() {
        if (instance == null) {
            instance = this;
        }

        InstantiatePlayers();
        InputHandler.addKeyEvent(this);
    }

    void InstantiatePlayers() {
        players = new Player[4];
        int i = 0;
        for (PlayerColor color : PlayerColor.values()) {
            players[i] = new Player(i + 1, color);
            i++;
        }
        currentPlayer = players[0];
    }

    void NextTurn() {
        turnIndex++;
        turnIndex %= players.length;

        currentPlayer = players[turnIndex];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public PlayerGraphicsInfo getCurrentPlayerGraphicsInfo() {
        return currentPlayer.getGraphicsInfo();
    }

    @Override
    public void OnKeyDown(KeyEvent e) {
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            NextTurn();
            GameStateChangeListener.invoke();
        }
    }
}
