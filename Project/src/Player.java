import java.util.ArrayList;

public class Player {
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    private Inventory inventory;
    private int secretVictoryPoints;
    private int publicVictoryPoints;
    private int playerNumber;

    private PlayerGraphicsInfo graphicsInfo;
    PlayerColor color;

    public Player(int playerNumber, PlayerColor color) {

        //update Inventory/Bank interactions

        secretVictoryPoints=0;
        publicVictoryPoints=0;
        this.playerNumber=playerNumber;
        inventory = new Inventory();
        this.color = color;
        graphicsInfo = new PlayerGraphicsInfo(color);
        buildings = new ArrayList<>();
    }



    public void addBuilding(Building building) {
        buildings.add(building);
        publicVictoryPoints++;
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

    public void upgradeBuilding(Building b){
       /* if(b.getType()==BuildingType.Settlement) {
            if(!playerInventory.purchase(0, 0, 3, 2, 0)){
                //not enough resources to upgrade to city
                return;
            }
            else{
                //upgrade to city (resources already subtracted)

            }
            b.upgrade();
            publicVictoryPoints++;
        }*/
    }
    public void createRoad(Edge e){
        //road already exists on edge
       /* if(e.containsRoad()||!e.roadAvailable(this)) return;
        if(!playerInventory.purchase(1, 0, 0, 0, 1)){
            //not enough resources to purchase road
            return;
        }
        //purchase road

        roads.add(new Road(this, e));*/
    }

    public void purchaseDevelopmentCard(){

    }
    public void useKnightCard(){

    }
    public int getPublicVictoryPoints(){
        return publicVictoryPoints;
    }
    public int getSecretVictoryPoints(){
        return secretVictoryPoints;
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

    public void addSecretVictoryPoints(int n){
        secretVictoryPoints+=n;
    }
}
