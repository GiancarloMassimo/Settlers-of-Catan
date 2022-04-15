import java.util.HashMap;

public class Bank {
    HashMap<ItemType, ItemCost> costMap;
    HashMap<ResourceType, Integer> remainingResources;

    public Bank() {
        InitializeCosts();
        InitializeResources();
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

    public void InitializeResources() {
        remainingResources.put(ResourceType.Brick, 19);
        remainingResources.put(ResourceType.Ore, 19);
        remainingResources.put(ResourceType.Sheep, 19);
        remainingResources.put(ResourceType.Wheat, 19);
        remainingResources.put(ResourceType.Wood, 19);
    }

    public void purchase(Inventory inventory, ItemType itemType) {
        HashMap<ResourceType, Integer> cost = costMap.get(itemType).getCostMap();

        if (hasEnoughResources(inventory, cost)) {
            GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " bought a " + itemType.toString());

            for (ResourceType resourceType : cost.keySet()) {
                inventory.payItem(resourceType, cost.get(resourceType));
            }

            returnResources(cost);

            if (itemType == ItemType.Road) {
                ItemPlacementController.placeRoad();
            } else if (itemType == ItemType.Settlement) {
                ItemPlacementController.placeSettlement();
            } else if (itemType == ItemType.City) {
                ItemPlacementController.placeCity();
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

    public int getRemainingResourceCount(ResourceType type) {
        return remainingResources.get(type);
    }

    public void returnResources(HashMap<ResourceType, Integer> resources) {
        for(ResourceType resourceType:resources.keySet()) {
            remainingResources.put(resourceType, remainingResources.get(resourceType) + resources.get(resourceType));
        }
    }

    //function with hashmap as parameter instead of single resource might be needed for hasEnoughRemainingResources and giveResource
    public boolean hasEnoughRemainingResources(ResourceType type, int count) {
        return count <= remainingResources.get(type);
    }
    public void giveResource(ResourceType type, int count) {
        remainingResources.put(type, remainingResources.get(type) - count);
    }

//    public void giveResources(HashMap<ResourceType, Integer> resources) {
//        for(ResourceType resourceType:resources.keySet()) {
//            remainingResources.put(resourceType, remainingResources.get(resourceType) - resources.get(resourceType));
//        }
//    }
//    public boolean hasEnoughRemainingResources(HashMap<ResourceType, Integer> pay) {
//        for(ResourceType resource:pay.keySet()) {
//            if(pay.get(resource)>remainingResources.get(resource)) {
//                return false;
//            }
//        }
//        return true;
//    }

}
