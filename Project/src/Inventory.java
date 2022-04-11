import java.util.HashMap;

public class Inventory {
    private HashMap<ResourceType, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
        for (ResourceType type : ResourceType.values()) {
            inventory.put(type, 0);
        }
    }

    public void receiveItem(ResourceType type, int count) {
        inventory.put(type, inventory.get(type) + count);
    }

    public void payItem(ResourceType type, int count) {
        inventory.put(type, inventory.get(type) - count);
    }

    public int getResourceCount(ResourceType type) {
        return inventory.get(type);
    }
}