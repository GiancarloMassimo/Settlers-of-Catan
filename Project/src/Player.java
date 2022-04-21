import java.util.ArrayList;

public class Player {
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    private Inventory inventory;
    private int secretVictoryPoints;
    private int publicVictoryPoints;
    private int playerNumber;

    private PlayerGraphicsInfo graphicsInfo;
    private PlayerColor color;

    public Player(int playerNumber, PlayerColor color) {

        secretVictoryPoints=0;
        publicVictoryPoints=0;
        this.playerNumber=playerNumber;
        inventory = new Inventory();
        this.color = color;
        graphicsInfo = new PlayerGraphicsInfo(color);
        buildings = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        inventory.decrementItem(ItemType.Settlement);
        publicVictoryPoints++;
    }

    public void upgradeBuilding(Building building) {
        building.upgrade();
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
