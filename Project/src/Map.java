import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private Tile[][] map;


    public Map() {
        createMap();
    }

    private void createMap() {
        TileMapFactory tileMapFactory = new TileMapFactory();
        map = Arrays.copyOf(tileMapFactory.getMap(), tileMapFactory.getMap().length);
        tileMapFactory = null;
    }

    public Tile[][] getMap() {
        return map;
    }
}
