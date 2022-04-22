import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private Robber robber;
    private LongestRoad longestRoad;

    public GameManager() {
        if (instance == null) {
            instance = this;
        }

        InstantiatePlayers();
        InputHandler.addKeyEvent(this);
        dice = new Dice();
        bank = new Bank();
        map = new Map();
        robber = new Robber(map.getDesert());
        longestRoad = new LongestRoad();

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
        int playerCount = 4;
        boolean validInput = false;
        while(!validInput) {
            try {
                playerCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players (2 - 4)"));
                validInput = playerCount >= 2 && playerCount <= 4;
            }
            catch (Exception e) {

            }
        }

        players = new Player[playerCount];
        int i = 0;
        var colors = Arrays.asList(PlayerColor.values());
        Collections.shuffle(colors);
        for (PlayerColor color : colors) {
            players[i] = new Player(i + 1, color);
            i++;
            if (i == playerCount) {
                break;
            }
        }
        turnIndex = 0;
        currentPlayer = players[turnIndex];
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

    public Robber getRobber() {
        return robber;
    }

    public LongestRoad getLongestRoad() {
        return longestRoad;
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
    public void onKeyDown(KeyEvent e) {
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
           GameActionHandler.signalAction(
                   GameActionTypes.Instant,
                   () -> nextTurn()
                   );
        }
    }
}
