import java.awt.event.KeyEvent;

public class GameManager implements KeyEventHandler {
    public static GameManager instance;

    private Player[] players;
    private Player currentPlayer;
    private int turnIndex;
    private Dice dice;

    public GameManager() {
        if (instance == null) {
            instance = this;
        }

        InstantiatePlayers();
        InputHandler.addKeyEvent(this);
        dice = new Dice();
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

    private void NextTurn() {
        GameLog.instance.logEvent(getCurrentPlayer() + " ended their turn\n");
        turnIndex++;
        turnIndex %= players.length;
        currentPlayer = players[turnIndex];

        dice.rollDice();
        GameStateChangeListener.invoke();
    }

    public Dice getDice() {
        return dice;
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
        }
    }
}
