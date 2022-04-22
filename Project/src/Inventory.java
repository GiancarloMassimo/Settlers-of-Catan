import java.util.HashMap;

public class Inventory {
    private int itemsInInventory = 0;
    private HashMap<ResourceType, Integer> inventory;
    private HashMap<ItemType, Integer> remainingItems;

    public Inventory() {
        inventory = new HashMap<>();
        remainingItems = new HashMap<>();
        for (ResourceType type : ResourceType.values()) {
            inventory.put(type, 0);
        }

        remainingItems.put(ItemType.Settlement, 5);
        remainingItems.put(ItemType.City, 4);
        remainingItems.put(ItemType.Road, 15);
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

}