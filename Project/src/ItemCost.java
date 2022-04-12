import java.util.HashMap;

public class ItemCost {
    private HashMap<ResourceType, Integer> costMap;

    public ItemCost(int brick, int sheep, int ore, int wheat, int wood) {
        costMap = new HashMap<>();
        costMap.put(ResourceType.Brick, brick);
        costMap.put(ResourceType.Sheep, sheep);
        costMap.put(ResourceType.Ore, ore);
        costMap.put(ResourceType.Wheat, wheat);
        costMap.put(ResourceType.Wood, wood);
    }

    public HashMap<ResourceType, Integer> getCostMap() {
        return costMap;
    }
}
