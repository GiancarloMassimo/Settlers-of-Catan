import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private Tile[][] map;
    private Node[] nodes;
    private Edge[] edges;

    public Map() {
        createMap();
    }

    private void createMap() {
        TileMapFactory tileMapFactory = new TileMapFactory();
        map = Arrays.copyOf(tileMapFactory.getMap(), tileMapFactory.getMap().length);
        tileMapFactory = null;

        NodeGraphFactory nodeGraphFactory = new NodeGraphFactory(map);
        nodes = Arrays.copyOf(nodeGraphFactory.getNodes(), nodeGraphFactory.getNodes().length);
        edges = Arrays.copyOf(nodeGraphFactory.getEdges(), nodeGraphFactory.getEdges().length);
        nodeGraphFactory = null;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }
}
