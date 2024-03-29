import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {
    private int num = -1;
    private int r, c;
    private TileType type;
    private ResourceType resourceType = null;
    private BufferedImage image, numberImage;

    public int screenX, screenY;

    public Node upper, upperRight, lowerRight, lower, lowerLeft, upperLeft;
    public ArrayList<Building> buildings = new ArrayList<>();

    public Tile(TileType type, int r, int c) {
        this.type = type;
        switch (type) {
            case Ore:
                resourceType = ResourceType.Ore;
                break;
            case Wood:
                resourceType = ResourceType.Wood;
                break;
            case Wheat:
                resourceType = ResourceType.Wheat;
                break;
            case Brick:
                resourceType = ResourceType.Brick;
                break;
            case Sheep:
                resourceType = ResourceType.Sheep;
                break;
        }
        this.r = r; this.c = c;
        assignImage();
    }

    private void assignImage() {
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

    public ResourceType getResourceType() {
        return resourceType;
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

    public String toString() {
        return type.toString() + " " + num;
    }
}
