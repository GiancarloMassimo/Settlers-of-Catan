import java.awt.image.BufferedImage;

public class Tile {
    private int num = -1;
    private int r, c;
    private TileType type;
    private BufferedImage image, numberImage;

    public Tile(TileType type, int r, int c) {
        this.type = type;
        this.r = r; this.c = c;
        assignImage();
    }

    void assignImage() {
        switch (type) {
            case Brick:
                image = ImageLoader.getImage("BrickTile");
                break;
            case Ore:
                image = ImageLoader.getImage("OreTile");
                break;
            case Wheat:
                image = ImageLoader.getImage("WheatTile");
                break;
            case Sheep:
                image = ImageLoader.getImage("SheepTile");
                break;
            case Wood:
                image = ImageLoader.getImage("WoodTile");
                break;
            case Desert:
                image = ImageLoader.getImage("DesertTile");
                break;
        }
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public TileType getType() {
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getNumberImage() {
        return numberImage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        numberImage = num < 2 ? null : ImageLoader.getImage(num+"");
    }
}
