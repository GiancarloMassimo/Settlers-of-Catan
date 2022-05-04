import java.util.HashMap;

public class YearOfPlentyItemSelection implements ItemSelection{
    Player player;
    int currAmount = 0;
    HashMap<ResourceType, Integer> selectionMap;

    /*public YearOfPlenty(Player o){
        super(o,ProgressCardType.YearOfPlenty);
    }*/

    @Override
    public void setUp(Player player) {
        this.player = player;
        clearSelection();
    }

    @Override
    public String getSelectionMessage() {
        return player + ": Select 2 cards";
    }

    @Override
    public void tryItemSelection(ResourceType resourceType) {
        if(currAmount < 2) {
            selectionMap.put(resourceType, selectionMap.get(resourceType) + 1);
            currAmount++;
        }
    }

    @Override
    public boolean isCompleteSelection() {
        return currAmount == 2;
    }

    @Override
    public void clearSelection() {
        for(ResourceType resourceType: ResourceType.values()) {
            selectionMap.put(resourceType, 0);
        }
        currAmount = 0;
        GameStateChangeListener.invoke();
    }

    @Override
    public void confirm() {
        for(ResourceType resourceType: ResourceType.values()) {
            GameManager.instance.getBank().removeStock(resourceType, selectionMap.get(resourceType));
            player.getInventory().receiveItem(resourceType, selectionMap.get(resourceType));
        }
        GameLog.instance.logEvent(player + " received 2 cards");
        GameStateChangeListener.invoke();
    }

    @Override
    public HashMap<ResourceType, Integer> getSelection() {
        return selectionMap;
    }
}
