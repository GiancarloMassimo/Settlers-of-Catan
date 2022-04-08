
import java.util.ArrayList;

public class Node {
    ArrayList<Tile> adjacenttiles; //array of all tiles that the node is on
    Port port;
    public Node(Port p){
        port =p;
        adjacenttiles = new ArrayList<>();
    }
    public void addAdjacenttiles(Tile t)
    {
        adjacenttiles.add(t);
    }
}
