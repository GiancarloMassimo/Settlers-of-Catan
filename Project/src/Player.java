import java.util.ArrayList;

public class Player {
    private ArrayList<Building> buildings;
    private Inventory playerInventory;
    private int victoryPoints;
    private int playerNumber;
    private PlayerGraphicsInfo graphicsInfo;
    PlayerColor color;

    public Player(int playerNumber, PlayerColor color) {
        victoryPoints=0;
        this.playerNumber=playerNumber;
        playerInventory = new Inventory();
        this.color = color;
        graphicsInfo = new PlayerGraphicsInfo(color);
    }
    public void createBuilding(Node n) {

        //check if building can be placed on node
//        if(n.containsBuilding()) {
//            return;
//        }


        if(!playerInventory.purchase(1, 1, 0, 1, 1)){
            //not enough resources to purchase building
            return;
        }
        else{
            //purchase building (resources already subtracted)
        }

        buildings.add(new Building(this, n));
        victoryPoints++;
    }
    public void upgradeBuilding(Building b){
        if(b.getType()==BuildingType.Settlement) {
            if(!playerInventory.purchase(0, 0, 3, 2, 0)){
                //not enough resources to upgrade to city
                return;
            }
            else{
                //upgrade to city (resources already subtracted)

            }
            b.upgrade();
            victoryPoints++;
        }
        else{
            //cannot upgrade existing city
        }
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }


    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public PlayerGraphicsInfo getGraphicsInfo() {
        return graphicsInfo;
    }

    @Override
    public String toString() {
        return color.toString() + " Player";
    }
}
