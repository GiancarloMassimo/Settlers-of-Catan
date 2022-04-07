import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphics implements GraphicsItem
{
    private BufferedImage RedPlayer, YellowPlayer, BluePlayer, GreenPlayer, VPindicator;
    private BufferedImage NormalRoad, LongestRoad, NormalArmy, LargestArmy;

    public PlayerGraphics()
    {
        //players
        RedPlayer = ImageLoader.getImage("Red Player");
        YellowPlayer = ImageLoader.getImage("Yellow Player");
        BluePlayer = ImageLoader.getImage("Blue Player");
        GreenPlayer = ImageLoader.getImage("Green Player");

        //other
        VPindicator = ImageLoader.getImage("Victory Point Indicator");

        //roads and armies
        NormalRoad = ImageLoader.getImage("Normal Road");
        LongestRoad = ImageLoader.getImage("Longest Road");
        NormalArmy = ImageLoader.getImage("Normal Army");
        LargestArmy = ImageLoader.getImage("Largest Army");
    }
    public void draw(Graphics g)
    {
        //Red's player components
        g.drawImage(RedPlayer, 1040, 300, 544 , 81 , null);
        g.drawImage(NormalRoad, 1140, 320, 77 , 26 , null);
        g.drawImage(NormalArmy, 1255, 315, 15 , 40 , null);

        //Yellow's player components
        g.drawImage(YellowPlayer, 1040, 400, 544 , 81 , null);
        g.drawImage(NormalRoad, 1140, 420, 77 , 26 , null);
        g.drawImage(NormalArmy, 1255, 415, 15 , 40 , null);

        //Blue's player components
        g.drawImage(BluePlayer, 1040, 500, 544 , 81 , null);
        g.drawImage(NormalRoad, 1140, 520, 77 , 26 , null);
        g.drawImage(NormalArmy, 1255, 515, 15 , 40 , null);

        // Green's player components
        g.drawImage(GreenPlayer, 1040, 600, 544 , 81 , null);
        g.drawImage(NormalRoad, 1140, 620, 77 , 26 , null);
        g.drawImage(NormalArmy, 1255, 615, 15 , 40 , null);

        //g.drawImage(VPindicator,1090,295,30,30,null);

    }
}
