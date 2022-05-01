import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    private Inventory inventory;
    private int secretVictoryPoints;
    private int publicVictoryPoints;
    private int playerNumber;

    private PlayerGraphicsInfo graphicsInfo;
    private PlayerColor color;

    private HashMap<ResourceType, Integer> tradingRatioMap;

    public Player(int playerNumber, PlayerColor color) {
        secretVictoryPoints=0;
        publicVictoryPoints=0;
        this.playerNumber=playerNumber;
        inventory = new Inventory();
        this.color = color;
        graphicsInfo = new PlayerGraphicsInfo(color);
        buildings = new ArrayList<>();
        roads = new ArrayList<>();
        setTradingRatios();
    }

    private void setTradingRatios() {
        tradingRatioMap = new HashMap<>();
        for (ResourceType resourceType : ResourceType.values()) {
            tradingRatioMap.put(resourceType, 4);
        }
    }

    public int getTradingRatio(ResourceType resourceType) {
        return tradingRatioMap.get(resourceType);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        inventory.decrementItem(ItemType.Settlement);
        publicVictoryPoints++;

        if (building.getLocation().getPort() != null) {
            Port port = building.getLocation().getPort();
            if (port.type == null) {
                for (ResourceType resourceType : tradingRatioMap.keySet()) {
                    if (tradingRatioMap.get(resourceType) > 3) {
                        tradingRatioMap.put(resourceType, 3);
                    }
                }
            } else {
                tradingRatioMap.put(port.type, 2);
            }
        }
    }

    public void upgradeBuilding(Building building) {
        inventory.decrementItem(ItemType.City);
        inventory.incrementItem(ItemType.Settlement);
        publicVictoryPoints++;
    }

    public void addRoad(Road road) {
        roads.add(road);
        inventory.decrementItem(ItemType.Road);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public Building getLastBuildingPlaced() {
        return buildings.get(buildings.size() - 1);
    }

    public ResourceType stealFromPlayer(Inventory otherPlayer) {
        ResourceType resource = otherPlayer.getRandomResource();

        if (resource == null) return null;

        inventory.receiveItem(resource, 1);
        otherPlayer.payItem(resource, 1);

        return resource;
    }

    public void addDevelopmentCard(){

    }

    public void useKnightCard(){

    }
    public int getPublicVictoryPoints(){
        return publicVictoryPoints;
    }
    public int getSecretVictoryPoints(){
        return secretVictoryPoints;
    }
    public void changePublicVictoryPoints(int increment) {
        publicVictoryPoints += increment;
    }

    public void addPublicVictoryPoints(int n) {
        publicVictoryPoints+=n;
    }
    public void addSecretVictoryPoints(int n) {
        secretVictoryPoints+=n;
    }
    public void subtractPublicVictoryPoints(int n) {
        publicVictoryPoints-=n;
    }
    public void subtractSecretVictoryPoints(int n) {
        secretVictoryPoints-=n;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public PlayerGraphicsInfo getGraphicsInfo() {
        return graphicsInfo;
    }
    @Override
    public String toString() {
        return color.toString() + " Player";
    }
}