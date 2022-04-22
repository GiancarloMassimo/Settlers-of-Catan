import java.util.HashMap;

public class DiscardItemSelection implements ItemSelection{
    Player currentPlayer;
    int discardAmount;
    int selectedAmount = 0;

    HashMap<ResourceType, Integer> selectionMap;

    @Override
    public void setUp(Player player) {
        currentPlayer = player;
        discardAmount = currentPlayer.getInventory().getTotalResources() / 2;
        selectionMap = new HashMap<>();
        for (ResourceType resourceType : ResourceType.values()) {
            selectionMap.put(resourceType, 0);
        }
        selectedAmount = 0;
    }

    @Override
    public String getSelectionMessage() {
        return currentPlayer + ": Discard " + discardAmount + " cards";
    }

    @Override
    public void tryItemSelection(ResourceType resourceType) {
        if (selectedAmount < discardAmount
                && selectionMap.get(resourceType) < currentPlayer.getInventory().getResourceCount(resourceType)) {
            selectionMap.put(resourceType, selectionMap.get(resourceType) + 1);
            selectedAmount++;
        }
    }

    @Override
    public boolean isCompleteSelection() {
        return selectedAmount == discardAmount;
    }

    @Override
    public void confirm() {
        for (ResourceType resourceType : selectionMap.keySet()) {
            currentPlayer.getInventory().payItem(resourceType, selectionMap.get(resourceType));
            GameManager.instance.getBank().addStock(resourceType, selectionMap.get(resourceType));
        }
        GameLog.instance.logEvent(currentPlayer + " discarded " + discardAmount + " cards");
        GameStateChangeListener.invoke();
    }

    @Override
    public HashMap<ResourceType, Integer> getSelection() {
        return selectionMap;
    }
}
