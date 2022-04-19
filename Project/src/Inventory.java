import java.util.HashMap;

public class Inventory {
    private int itemsInInventory = 0;
    private HashMap<ResourceType, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
        for (ResourceType type : ResourceType.values()) {
            inventory.put(type, 0);
        }
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
}