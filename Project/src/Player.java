import java.util.ArrayList;

public class Player {
    private ArrayList<Building> buildings;
    private ArrayList<ResourceCard> ResourceDeck;
    private int victoryPoints;
    private int playerNumber;
    public Player(int playerNumber) {
        victoryPoints=0;
        this.playerNumber=playerNumber;
    }
    public void createBuilding(Node n) {
        //subtract resources
//        if(n.containsBuilding()) {
//            return;
//        }
        buildings.add(new Building(this, n));
    }
    public void upgrade(Building b){
        if(b.getType()==1) {
            //subtract resources
            b.upgrade();
        }
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }
}
