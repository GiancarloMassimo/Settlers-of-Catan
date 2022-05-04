import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private Tile[][] map;
    private Node[] nodes;
    private Edge[] edges;
    private Port[] ports;
    private Tile desert;

    public Map() {
        createMap();
    }

    private void createMap() {
        TileMapFactory tileMapFactory = new TileMapFactory();
        map = Arrays.copyOf(tileMapFactory.getMap(), tileMapFactory.getMap().length);

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c].getType() == TileType.Desert) desert = map[r][c];
            }
        }

        NodeGraphFactory nodeGraphFactory = new NodeGraphFactory(map);
        nodes = Arrays.copyOf(nodeGraphFactory.getNodes(), nodeGraphFactory.getNodes().length);
        edges = Arrays.copyOf(nodeGraphFactory.getEdges(), nodeGraphFactory.getEdges().length);
        ports = Arrays.copyOf(nodeGraphFactory.getPorts(), nodeGraphFactory.getPorts().length);
        nodeGraphFactory = null;
    }

    public Tile[][] getTiles() {
        return map;
    }

    public Tile getDesert() {
        return desert;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Port[] getPorts() {
        return ports;
    }
}
