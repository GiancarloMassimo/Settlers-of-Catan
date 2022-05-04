import java.util.HashMap;

public class Inventory {
    private int itemsInInventory = 0;
    private HashMap<ResourceType, Integer> inventory;
    private HashMap<ItemType, Integer> remainingItems;
    private HashMap<DevelopmentCardType, Integer> developmentCards;

    public Inventory() {
        inventory = new HashMap<>();
        remainingItems = new HashMap<>();
        developmentCards = new HashMap<>();
        for (ResourceType type : ResourceType.values()) {
            inventory.put(type, 0);
        }
        for(DevelopmentCardType type: DevelopmentCardType.values()) {
            developmentCards.put(type, 0);
        }
        remainingItems.put(ItemType.Settlement, 5);
        remainingItems.put(ItemType.City, 4);
        remainingItems.put(ItemType.Road, 15);
        remainingItems.put(ItemType.DevelopmentCard, 0);
    }

    public void receiveItem(ResourceType type, int count) {
        inventory.put(type, inventory.get(type) + count);
        itemsInInventory += count;
    }

    public void payItem(ResourceType type, int count) {
        inventory.put(type, inventory.get(type) - count);
        itemsInInventory -= count;
    }

    public int getResourceCount(ResourceType type) {
        return inventory.get(type);
    }

    public ResourceType getRandomResource() {
        if (itemsInInventory == 0) return null;

        int n = Helpers.randInt(1, itemsInInventory + 1);
        for (ResourceType resource : inventory.keySet()) {
            if (inventory.get(resource) >= n) return resource;
            n -= inventory.get(resource);
        }

        return null;
    }

    public int getItemCount(ItemType type) {
        return remainingItems.get(type);
    }

    public int getDevelopmentCardCount(DevelopmentCardType type) {
        return developmentCards.get(type);
    }

    public void receiveDevelopmentCard(DevelopmentCardType type) {
        developmentCards.put(type, developmentCards.get(type) + 1);
        if (type == DevelopmentCardType.VictoryPoint) {
            GameManager.instance.getCurrentPlayer().addSecretVictoryPoints(1);
        }
        GameManager.instance.devCardTurnPurchase.put(type, GameManager.instance.devCardTurnPurchase.get(type) + 1);
    }

    public boolean canPlayCard(DevelopmentCardType type) {
        if (GameManager.instance.devCardPlayed) {
            return false;
        }
        if (type == DevelopmentCardType.VictoryPoint) {
            return false;
        }
        if (getDevelopmentCardCount(type) <= GameManager.instance.devCardTurnPurchase.get(type)) {
            return false;
        }
        return true;
    }

    public void playCard(DevelopmentCardType type) {
        GameManager.instance.devCardPlayed = true;
        developmentCards.put(type, developmentCards.get(type) - 1);
        if (type != DevelopmentCardType.Knight) {
            GameManager.instance.getBank().giveBackDevelopmentCard(type);
        }
    }

    public int getTotalResources() {
        int count = 0;

        for (ResourceType resourceType : inventory.keySet()) {
            count += inventory.get(resourceType);
        }

        return count;
    }

    public void decrementItem(ItemType type) {
        remainingItems.put(type, remainingItems.get(type) - 1);
    }

    public void incrementItem(ItemType type) {
        remainingItems.put(type, remainingItems.get(type) + 1);
    }

    public boolean itemAvailable(ItemType type) {
        return remainingItems.get(type) > 0;
    }

    public void addDevelopmentCard(DevelopmentCardType developmentCardType, int n){
        developmentCards.put(developmentCardType,developmentCards.get(developmentCardType)+n);
    }

    public HashMap<DevelopmentCardType, Integer> getDevelopmentCards(){
        return developmentCards;
    }
}