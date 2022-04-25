import java.awt.*;
import java.awt.image.BufferedImage;

public class Port {
    ResourceType type;
    BufferedImage image;

    public Port(ResourceType type) {
        this.type = type;

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
}
