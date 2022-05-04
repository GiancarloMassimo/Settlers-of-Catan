import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Port {
    ResourceType type;
    BufferedImage image;
    ArrayList<Node> nodes;
    int screenX = -1, screenY = -1;

    public Port(ResourceType type) {
        this.type = type;
        nodes = new ArrayList<>();

        if (type == null) {
            image = ImageLoader.getImage("GeneralPort");
        } else {
            switch (type) {
                case Wood -> image = ImageLoader.getImage("WoodPort");
                case Wheat -> image = ImageLoader.getImage("WheatPort");
                case Ore -> image = ImageLoader.getImage("OrePort");
                case Brick -> image = ImageLoader.getImage("BrickPort");
                case Sheep -> image = ImageLoader.getImage("SheepPort");
            }
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void setScreenPosition(int[] pos) {
        screenX = pos[0]; screenY = pos[1];
    }
}
