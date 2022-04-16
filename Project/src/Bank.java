import java.util.HashMap;

public class Bank {
    HashMap<ItemType, ItemCost> costMap;

    public Bank() {
        InitializeCosts();
    }

    public void InitializeCosts() {
        costMap = new HashMap<>();

        for (ItemType item : ItemType.values()) {
            int brick = 0, sheep = 0, ore = 0, wheat = 0, wood = 0;

            switch (item) {
                case Settlement -> {
                    brick = 1;
                    sheep = 1;
                    wheat = 1;
                    wood = 1;
                }
                case City -> {
                    wheat = 2;
                    ore = 3;
                }
                case Road -> {
                    wood = 1;
                    brick = 1;
                }
                case DevelopmentCard -> {
                    wheat = 1;
                    sheep = 1;
                    ore = 1;
                }
            }

            costMap.put(item, new ItemCost(brick, sheep, ore, wheat, wood));
        }
    }

    public void purchase(Inventory inventory, ItemType itemType) {
        HashMap<ResourceType, Integer> cost = costMap.get(itemType).getCostMap();

        if (hasEnoughResources(inventory, cost)) {
            try {
                if (itemType == ItemType.Road) {
                    ItemPlacementController.placeRoad();
                } else if (itemType == ItemType.Settlement) {
                    ItemPlacementController.placeSettlement();
                } else if (itemType == ItemType.City) {
                    ItemPlacementController.placeCity();
                }
            }
            catch (NoValidPositionForItemException e) {
                return;
            }

            GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " bought a " + itemType.toString());

            for (ResourceType resourceType : cost.keySet()) {
                inventory.payItem(resourceType, cost.get(resourceType));
            }

        }

        GameStateChangeListener.invoke();
    }

    public boolean hasEnoughResources(Inventory inventory, HashMap<ResourceType, Integer> cost) {
        for (ResourceType resource : cost.keySet()) {
            if (cost.get(resource) > inventory.getResourceCount(resource)) {
                return false;
            }
        }

        return true;
    }
}
