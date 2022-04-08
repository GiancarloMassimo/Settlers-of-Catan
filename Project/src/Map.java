import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private Tile[][] map;
    private Node[] nodes;

    public Map() {
        createMap();
    }

    private void createMap() {
        TileMapFactory tileMapFactory = new TileMapFactory();
        map = Arrays.copyOf(tileMapFactory.getMap(), tileMapFactory.getMap().length);
        tileMapFactory = null;

        NodeGraphFactory nodeGraphFactory = new NodeGraphFactory(map);
        nodes = Arrays.copyOf(nodeGraphFactory.getNodes(), nodeGraphFactory.getNodes().length);

        for (int i = 1; i <= nodes.length; i++) {
            System.out.println(i + ". "
                    + (nodes[i - 1] == null ? "null" : nodes[i - 1].getAdjacentTiles().toString()) + " "
                    + nodes[i - 1].getEdges().size());
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
