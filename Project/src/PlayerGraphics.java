import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphics implements GraphicsItem
{
    private BufferedImage RedPlayer, YellowPlayer, BluePlayer, GreenPlayer;
    private BufferedImage NormalRoad, LongestRoad, NormalArmy, LargestArmy;

    public PlayerGraphics()
    {
        RedPlayer = ImageLoader.getImage("Red Player");
        YellowPlayer = ImageLoader.getImage("Yellow Player");
        BluePlayer = ImageLoader.getImage("Blue Player");
        GreenPlayer = ImageLoader.getImage("Green Player");

        NormalRoad = ImageLoader.getImage("Normal Road");
        LongestRoad = ImageLoader.getImage("Longest Road");
        NormalArmy = ImageLoader.getImage("Normal Army");
        LargestArmy = ImageLoader.getImage("Largest Army");
    }
    public void draw(Graphics g)
    {
        g.drawImage(RedPlayer, 1040, 300, 544 , 81 , null);
        g.drawImage(YellowPlayer, 1040, 400, 544 , 81 , null);
        g.drawImage(BluePlayer, 1040, 500, 544 , 81 , null);
        g.drawImage(GreenPlayer, 1040, 600, 544 , 81 , null);

        g.drawImage(NormalRoad, 1140, 320, 77 , 26 , null); //Red's player components
        g.drawImage(NormalArmy, 1255, 315, 15 , 40 , null);

        g.drawImage(NormalRoad, 1140, 420, 77 , 26 , null);//Yellow's player components
        g.drawImage(NormalArmy, 1255, 415, 15 , 40 , null);

        g.drawImage(NormalRoad, 1140, 520, 77 , 26 , null);//Blue's player components
        g.drawImage(NormalArmy, 1255, 515, 15 , 40 , null);

        g.drawImage(NormalRoad, 1140, 620, 77 , 26 , null);// Green's player components
        g.drawImage(NormalArmy, 1255, 615, 15 , 40 , null);

    }
}
