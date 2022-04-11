
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
    public boolean buildingAvailable(){//search outwards once to see if building can be placed, assumes current node is empty

        for(Edge e: edges){
            if(e.a.containsBuilding()||e.b.containsBuilding()) return false;
        }
        return true;
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
}
