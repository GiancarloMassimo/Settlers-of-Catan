import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundGraphics implements GraphicsItem {
    private BufferedImage backgroundImage;

    public BackgroundGraphics() {
        backgroundImage = ImageLoader.getImage("Background");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Frame.WIDTH, Frame.HEIGHT, null);
    }
}
