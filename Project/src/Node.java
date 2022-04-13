
import java.util.ArrayList;

public class Node {
    private ArrayList<Tile> adjacentTiles; //array of all tiles that the node is on
    private ArrayList<Edge> edges;
    public int screenX, screenY;
    Port port;
    Building building = null;

    public Node(){
        adjacentTiles = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void setPort(Port p) {
        port = p;
    }

    public void addAdjacentTiles(Tile t) {
        adjacentTiles.add(t);
    }

    public Building getBuilding(){ return building;}

    public void createBuilding(Building b){
        building = b;
    }

    public boolean containsBuilding() {
        return building != null;
    }

    public boolean buildingAvailable(){//search outwards once to see if building can be placed, assumes current node is empty, (Doesn't check for connection to player road)
        for(Edge e: edges){
            if(e.a.containsBuilding()||e.b.containsBuilding()) return false;
        }
        return true;
    }

    public boolean connectedToRoad(Player p){//checks for connection to players own road, assumes current node is empty (use both this and buildingAvailable whenever it isn't the board set up)
        for(Edge e:edges){
            if(e.getRoad().getOwner().equals(p)) return true;
        }
        return false;
    }

    public boolean addEdge(Edge edge) {
        for (Edge e : edges)
            if (e.equals(edge)) return false;

        edges.add(edge);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Tile> getAdjacentTiles() {
        return adjacentTiles;
    }

    public String toString() {
        String out = "";

        for (Tile t : adjacentTiles) {
            out += t.getNum() + " ";
        }

        return out;
    }
}
