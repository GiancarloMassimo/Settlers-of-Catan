import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class TradingGraphics implements GraphicsItem, MouseEventHandler, KeyEventHandler
{
    private BufferedImage TradingBox;

    private Player currentPlayer = null;
    private HashMap<ResourceType, Integer> currentOffer, currentRequest;
    private Player[] players;
    private ArrayList<Player> availableTrades;

    private final ResourceType[] ICON_ORDER = {
            ResourceType.Sheep, ResourceType.Wheat,
            ResourceType.Ore, ResourceType.Brick, ResourceType.Wood};

    public TradingGraphics()
    {
        TradingBox = ImageLoader.getImage("Trading");
        GameManager.instance.setTradingGraphics(this);
        players = GameManager.instance.getPlayers();
        InputHandler.addKeyEvent(this);
        InputHandler.addMouseEvent(this);
    }

    public void onNextTurn() {
        currentPlayer = GameManager.instance.getCurrentPlayer();
        resetSelection();
    }

    private void resetSelection() {
        currentOffer = new HashMap<>();
        currentRequest = new HashMap<>();
        availableTrades = new ArrayList<>();

        for (ResourceType resourceType : ResourceType.values()) {
            currentOffer.put(resourceType, 0);
            currentRequest.put(resourceType, 0);
        }
        GameStateChangeListener.invoke();
    }

    @Override
    public void draw(Graphics g)
    {
        if (GameManager.instance.isInBuildPhase()) {
            resetSelection();
            return;
        }

        g.drawImage(TradingBox, 1050, 720, 537, 147, null);

        g.setColor(Color.black);
        g.setFont(new Font("default", Font.BOLD, 16));

        int spacing = 54;
        int startX = 1170;
        for (int i = 0; i < ICON_ORDER.length; i++) {
            g.drawString(currentOffer.get(ICON_ORDER[i]) + "", startX + spacing * i, 790);
            g.drawString(currentRequest.get(ICON_ORDER[i]) + "", startX + spacing * i, 823);
        }

        int playerTradeIconSpacing = 40;
        for (int i = 0; i < availableTrades.size(); i++) {
            g.setColor(availableTrades.get(i).getGraphicsInfo().getPlayerColor());
            g.fillOval(1420 + i * playerTradeIconSpacing, 780, 25, 25);
        }

        if (currentPlayer != null && canTradeWithBank()) {
            g.drawImage(ImageLoader.getImage("BankIcon"), 1530, 770, null);
        }
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
            GameActionHandler.signalAction(
                    GameActionTypes.Instant,
                    () -> {
                        resetSelection();
                        GameStateChangeListener.invoke();
                    }
            );
        }
    }

    private boolean validOffer(ResourceType resourceType) {
        return currentOffer.get(resourceType) < currentPlayer.getInventory().getResourceCount(resourceType);
    }

    private boolean validRequest(ResourceType resourceType) {
        return currentRequest.get(resourceType) < 19;
    }

    private boolean canTradeWithBank() {
        //check that the player is only trading one resource

        int resourcesBeingOffered = 0;
        ResourceType resourceBeingOffered = null;
        int amountBeingOffered = 0;
        for (ResourceType resourceType : ResourceType.values()) {
            if (currentOffer.get(resourceType) > 0) {
                resourcesBeingOffered++;
                if (resourcesBeingOffered > 1) {
                    return false;
                }
                resourceBeingOffered = resourceType;
                amountBeingOffered = currentOffer.get(resourceType);
            }
        }

        if (resourceBeingOffered == null) return false;

        // only asking for one resource

        int resourcesBeingRequested = 0;
        ResourceType resourceBeingRequested = null;
        int amountBeingRequested = 0;
        for (ResourceType resourceType : ResourceType.values()) {
            if (currentRequest.get(resourceType) > 0) {
                resourcesBeingRequested++;
                if (resourcesBeingRequested > 1) {
                    return false;
                }
                resourceBeingRequested = resourceType;
                amountBeingRequested = currentRequest.get(resourceType);
            }
        }

        if (resourceBeingRequested == null) return false;

        //check if the player is trading the correct ratio

        if (amountBeingOffered % currentPlayer.getTradingRatio(resourceBeingOffered) == 0
            && amountBeingOffered / amountBeingRequested == currentPlayer.getTradingRatio(resourceBeingOffered)) {
            return true;
        }
        return false;
    }

    private void updateAvailableTrades() {
        availableTrades = new ArrayList<>();

        boolean isEmptyOffer = true;
        for (ResourceType resourceType : currentOffer.keySet()) {
            if (currentOffer.get(resourceType) > 0) {
                isEmptyOffer = false;
                break;
            }
        }
        if (isEmptyOffer) return;

        for (Player player : players) {
            if (player == currentPlayer) continue;
            boolean addPlayer = true;
            for (ResourceType resourceType : currentRequest.keySet()) {
                if (player.getInventory().getResourceCount(resourceType) < currentRequest.get(resourceType)) {
                    addPlayer = false;
                    break;
                }
            }
            if (addPlayer) availableTrades.add(player);
        }
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        if (GameManager.instance.isInBuildPhase()) {
            return;
        }

        ResourceType clicked = getResourceClicked(e.getX());
        if (clicked != null) {
            if (e.getY() >= 790 && validRequest(clicked)) {
                GameActionHandler.signalAction(
                        GameActionTypes.Instant,
                        () -> {
                            currentRequest.put(clicked, currentRequest.get(clicked) + 1);
                            updateAvailableTrades();
                            GameStateChangeListener.invoke();
                        }
                );
            } else if (e.getY() >= 740 && validOffer(clicked)) {
                GameActionHandler.signalAction(
                        GameActionTypes.Instant,
                        () -> {
                            currentOffer.put(clicked, currentOffer.get(clicked) + 1);
                            GameStateChangeListener.invoke();
                        }
                );
            }
        }

        Player playerClicked = getPlayerClicked(e.getX());
        if (playerClicked != null) {
            GameActionHandler.signalAction(
                    GameActionTypes.Instant,
                    () -> {
                        for (ResourceType resourceType : currentOffer.keySet()) {
                            currentPlayer.getInventory().payItem(resourceType, currentOffer.get(resourceType));
                            currentPlayer.getInventory().receiveItem(resourceType, currentRequest.get(resourceType));
                            playerClicked.getInventory().payItem(resourceType, currentRequest.get(resourceType));
                            playerClicked.getInventory().receiveItem(resourceType, currentOffer.get(resourceType));
                        }
                        GameLog.instance.logEvent(currentPlayer + " traded " + currentOffer.toString() + " for "
                        + currentRequest + " with " + playerClicked);
                        resetSelection();
                        GameStateChangeListener.invoke();
                    }
            );
        } else if (bankClicked(e.getX(), e.getY()) && canTradeWithBank()) {
            GameActionHandler.signalAction(
                    GameActionTypes.Instant,
                    () -> {
                        for (ResourceType resourceType : currentOffer.keySet()) {
                            currentPlayer.getInventory().payItem(resourceType, currentOffer.get(resourceType));
                            currentPlayer.getInventory().receiveItem(resourceType, currentRequest.get(resourceType));
                            GameManager.instance.getBank().addStock(resourceType, currentOffer.get(resourceType));
                            GameManager.instance.getBank().removeStock(resourceType, currentRequest.get(resourceType));
                        }
                        GameLog.instance.logEvent(currentPlayer + " traded " + currentOffer.toString() + " for "
                                + currentRequest + " with the Bank");
                        resetSelection();
                        GameStateChangeListener.invoke();
                    }
            );

        }
    }

    private Player getPlayerClicked(int x) {
        int playerTradeIconSpacing = 40;
        int startX = 1420;
        try {
            return availableTrades.get(((x - startX) / playerTradeIconSpacing));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private ResourceType getResourceClicked(int x) {
        int spacing = 54;
        int startX = 1170;

        try {
            return ICON_ORDER[((x - startX + 30) / spacing)];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private boolean bankClicked(int x, int y) {
        final int radius = 15;
        int bankX = 1530, bankY = 770;
        return Helpers.getDistance(x, y, bankX + 10, bankY + 10) < radius;
    }
}
