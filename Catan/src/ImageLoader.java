import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class ImageLoader {
    private static HashMap<String, BufferedImage> images = new HashMap<>();

    public static void loadImages() {
        try {
            File dir = new File("/Images/BrickTile.png");
            //ImageIO.read(ImageLoader.class.getResource("/Images/background.png"));
            System.out.println(dir.getName());
            System.out.println(dir.isFile());
            System.out.println(dir.isDirectory());
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    System.out.println(child.getName());
                }
            } else {
                System.out.println("ImageLoader path not a directory");
            }
        } catch (Exception e) {
            System.out.println("Images couldn't be loaded");
        }

    }

    public static BufferedImage getImage(String name) {
        return images.get(name);
    }
}
