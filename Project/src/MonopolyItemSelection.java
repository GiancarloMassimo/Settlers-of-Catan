import java.util.HashMap;

public class MonopolyItemSelection implements ItemSelection{
    Player player;
    int currAmount = 0;
    ResourceType selectedType;

    @Override
    public void setUp(Player player) {
        this.player = player;
        clearSelection();
    }

    @Override
    public String getSelectionMessage() {
        return player + "Select one card";
    }

    @Override
    public void tryItemSelection(ResourceType resourceType) {
        if(currAmount==0) currAmount++;
        selectedType = resourceType;
    }

    @Override
    public boolean isCompleteSelection() {
        return currAmount == 1;
    }

    @Override
    public void clearSelection() {
        selectedType = null;
        currAmount = 0;
        GameStateChangeListener.invoke();
    }

    @Override
    public void confirm() {
        Player[] p = GameManager.instance.getPlayers();
        int received = 0;
        for(int i=0;i<4;i++) {
            if(p[i] != player) {
                Inventory inv = p[i].getInventory();
                int num = inv.getResourceCount(selectedType);
                received += num;
                player.getInventory().receiveItem(selectedType, num);
                inv.payItem(selectedType, num);
            }
        }
        GameLog.instance.logEvent(player + " received " + received + " cards");
        GameStateChangeListener.invoke();
    }

    @Override
    public HashMap<ResourceType, Integer> getSelection() {
        HashMap<ResourceType, Integer> selectionMap = new HashMap<>();
        selectionMap.put(selectedType, 1);
        return selectionMap;
    }
}
