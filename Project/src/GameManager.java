import java.awt.event.KeyEvent;

public class GameManager implements KeyEventHandler {
    public static GameManager instance;

    private int initialTurns;
    private int turnCount;

    private Player[] players;
    private Player currentPlayer;
    private int turnIndex;
    private Dice dice;
    private Bank bank;
    private Map map;

    public GameManager() {
        if (instance == null) {
            instance = this;
        }

        InstantiatePlayers();
        InputHandler.addKeyEvent(this);
        dice = new Dice();
        bank = new Bank();
        map = new Map();

        turnCount = 0;
        initialTurns = players.length * 2;
    }

    public void onWindowLoad() {
        ItemPlacementController.placeInitialSettlement();
        GameActionHandler.queueAction(
                GameActionTypes.Instant,
                () -> ItemPlacementController.placeInitialRoad()
        );
        GameStateChangeListener.invoke();
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

    private void nextTurn() {
        GameLog.instance.logEvent(getCurrentPlayer() + " ended their turn\n");
        turnCount++;

        if (turnCount < initialTurns) {
            initialTurn();
            return;
        }

        turnIndex = cycleTurnIndex();
        currentPlayer = players[turnIndex];

        dice.rollDice();
        GameStateChangeListener.invoke();
    }

    private void initialTurn() {
        turnIndex = pingPongTurnIndex();
        currentPlayer = players[turnIndex];

        ItemPlacementController.placeInitialSettlement();
        GameActionHandler.queueAction(
                GameActionTypes.Instant,
                () -> ItemPlacementController.placeInitialRoad()
        );

        GameStateChangeListener.invoke();
    }

    private int cycleTurnIndex() {
        turnIndex++;
        turnIndex %= players.length;
        return turnIndex;
    }

    private int pingPongTurnIndex() {
        if (turnCount < initialTurns / 2) {
            turnIndex++;
        } else if (turnCount == initialTurns / 2) {
            return turnIndex;
        } else {
            turnIndex--;
        }
        return turnIndex;
    }

    public void distributeResources() {
        for (Player player : players) {
            for (Building building : player.getBuildings()) {
                building.collectResources();
            }
        }
        GameStateChangeListener.invoke();
    }

    public Dice getDice() {
        return dice;
    }

    public Bank getBank() {
        return bank;
    }

    public Map getMap() {
        return map;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getInitialTurns() {
        return initialTurns;
    }

    public PlayerGraphicsInfo getCurrentPlayerGraphicsInfo() {
        return currentPlayer.getGraphicsInfo();
    }

    @Override
    public void OnKeyDown(KeyEvent e) {
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
           GameActionHandler.signalAction(
                   GameActionTypes.Instant,
                   () -> nextTurn()
                   );
        }
    }
}
