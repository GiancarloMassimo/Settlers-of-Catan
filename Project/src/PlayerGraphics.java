import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphics implements GraphicsItem
{
    private BufferedImage RedPlayer, YellowPlayer, BluePlayer, GreenPlayer;

    public PlayerGraphics()
    {
        RedPlayer = ImageLoader.getImage("Red Player");
        YellowPlayer = ImageLoader.getImage("Yellow Player");
    }
    public void draw(Graphics g)
    {
        g.drawImage(RedPlayer, 1040, 300, 544 , 81 , null);
        g.drawImage(YellowPlayer, 1040, 400, 544 , 81 , null);
        g.drawImage(RedPlayer, 1040, 500, 544 , 81 , null);
        g.drawImage(RedPlayer, 1040, 600, 544 , 81 , null);
    }
}