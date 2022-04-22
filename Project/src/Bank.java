import java.util.HashMap;

public class Bank {
    private HashMap<ItemType, ItemCost> costMap;
    private HashMap<ResourceType, Integer> bankStock;
    private int developmentCardCount = 25;
    private HashMap<DevelopmentCardType, Integer> developmentCardStock;

    public Bank() {
        initializeCosts();
        fillStock();
    }

    public void fillStock() {
        bankStock = new HashMap<>();
        developmentCardStock = new HashMap<>();
        for (ResourceType resourceType : ResourceType.values()) {
            bankStock.put(resourceType, 19);
        }
        developmentCardStock.put(DevelopmentCardType.Knight, 14);
        developmentCardStock.put(DevelopmentCardType.VictoryPoint, 5);
        developmentCardStock.put(DevelopmentCardType.Progress, 6);//could treat different types of progress cards as separate
    }

    public void initializeCosts() {
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

        if (hasEnoughResources(inventory, cost) && inventory.itemAvailable(itemType)) {
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

            //inventory.decrementItem(itemType);
            //replace with addRoad/addBuilding/upgradeBuilding

            for (ResourceType resourceType : cost.keySet()) {
                inventory.payItem(resourceType, cost.get(resourceType));
                addStock(resourceType, cost.get(resourceType));
            }

        }

        GameStateChangeListener.invoke();
    }

    public void addStock(ResourceType resourceType, int amount) {
        bankStock.put(resourceType, bankStock.get(resourceType) + amount);
    }

    public void removeStock(ResourceType resourceType, int amount) {
        bankStock.put(resourceType, bankStock.get(resourceType) - amount);
    }

    public boolean hasEnoughResources(Inventory inventory, HashMap<ResourceType, Integer> cost) {
        for (ResourceType resource : cost.keySet()) {
            if (cost.get(resource) > inventory.getResourceCount(resource)) {
                return false;
            }
        }

        return true;
    }

    public int getStockOfResource(ResourceType resourceType) {
        return bankStock.get(resourceType);
    }

    public int getDevelopmentCardCount() {
        return developmentCardCount;
    }

    public boolean hasResource(ResourceType resourceType, int amount) {
        return getStockOfResource(resourceType) >= amount;
    }
}
