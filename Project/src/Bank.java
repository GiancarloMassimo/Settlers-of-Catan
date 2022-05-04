import java.util.HashMap;

public class Bank {
    private HashMap<ItemType, ItemCost> costMap;
    private HashMap<ResourceType, Integer> bankStock;
    private int developmentCardCount;
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
        developmentCardStock.put(DevelopmentCardType.Knight, 0);
        developmentCardStock.put(DevelopmentCardType.VictoryPoint, 10);
        developmentCardStock.put(DevelopmentCardType.Monopoly, 0);
        developmentCardStock.put(DevelopmentCardType.RoadBuilding, 0);
        developmentCardStock.put(DevelopmentCardType.YearOfPlenty, 0);

        developmentCardCount = 10;
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

        if (hasEnoughResources(inventory, cost)) {
            if (itemType == ItemType.DevelopmentCard && developmentCardCount > 0){
                giveRandomDevelopmentCard(inventory);
        } else if(inventory.itemAvailable(itemType)) {
            try {
                if (itemType == ItemType.Road) {
                    ItemPlacementController.placeRoad();
                } else if (itemType == ItemType.Settlement) {
                    ItemPlacementController.placeSettlement();
                } else if (itemType == ItemType.City) {
                    ItemPlacementController.placeCity();
                }
            } catch (NoValidPositionForItemException e) {
                return;
            }
        }
            else return;

            GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " bought a " + itemType.toString());

            GameManager.instance.startBuildPhase();

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

    public void changeDevelopmentCardCount(int n) { developmentCardCount += n; }

    public void giveRandomDevelopmentCard(Inventory inventory) {
        if(developmentCardCount == 0) return;
        int n = Helpers.randInt(1, developmentCardCount+1);
        for(DevelopmentCardType t: DevelopmentCardType.values()) {
            if(n <= developmentCardStock.get(t)) {
                changeDevelopmentCardCount(-1);
                developmentCardStock.put(t, developmentCardStock.get(t) - 1);
                switch(t){
                    case Knight:
                        inventory.receiveDevelopmentCard(DevelopmentCardType.Knight);
                        return;
                    case Monopoly:
                        inventory.receiveDevelopmentCard(DevelopmentCardType.Monopoly);
                        return;
                    case RoadBuilding:
                        inventory.receiveDevelopmentCard(DevelopmentCardType.RoadBuilding);
                        return;
                    case VictoryPoint:
                        inventory.receiveDevelopmentCard(DevelopmentCardType.VictoryPoint);
                        return;
                    case YearOfPlenty:
                        inventory.receiveDevelopmentCard(DevelopmentCardType.YearOfPlenty);
                        return;
                }

            }
            n -= developmentCardStock.get(t);
        }
    }

    public void giveBackDevelopmentCard(DevelopmentCardType developmentCardType) {
        developmentCardStock.put(developmentCardType, developmentCardStock.get(developmentCardType) + 1);
        changeDevelopmentCardCount(1);
    }
}
