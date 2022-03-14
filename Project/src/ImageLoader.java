import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ImageLoader {
    private static HashMap<String, BufferedImage> images = new HashMap<>();

    public static BufferedImage getImage(String name) {
        if (images.get(name) != null) {
            return images.get(name);
        }

        try {
            BufferedImage image = ImageIO.read(ImageLoader.class.getResource("/Images/" + name + ".png"));
            images.put(name, image);
            return image;
        }
        catch (Exception e) {
            System.out.println("Unable to load image: " + name);
            return null;
        }
    }
}
