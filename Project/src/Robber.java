public class Robber {
    private Tile tile;
    public Robber(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public void move(Tile tile) {
        this.tile = tile;
    }
}
