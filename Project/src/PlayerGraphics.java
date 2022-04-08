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
        //TODO: WRITE THIS WITH A FOR LOOP USING PLAYER DATA
        //The way this is right now is going to be really tedious
        //Red's player components
        g.drawImage(RedPlayer, 1050, 300, 544 , 81 , null);
        g.drawImage(NormalRoad, 1150, 320, 77 , 26 , null);
        g.drawImage(NormalArmy, 1265, 315, 15 , 40 , null);

        //Yellow's player components
        g.drawImage(YellowPlayer, 1050, 400, 544 , 81 , null);
        g.drawImage(NormalRoad, 1150, 420, 77 , 26 , null);
        g.drawImage(NormalArmy, 1265, 415, 15 , 40 , null);

        //Blue's player components
        g.drawImage(BluePlayer, 1050, 500, 544 , 81 , null);
        g.drawImage(NormalRoad, 1150, 520, 77 , 26 , null);
        g.drawImage(NormalArmy, 1265, 515, 15 , 40 , null);

        // Green's player components
        g.drawImage(GreenPlayer, 1050, 600, 544 , 81 , null);
        g.drawImage(NormalRoad, 1150, 620, 77 , 26 , null);
        g.drawImage(NormalArmy, 1265, 615, 15 , 40 , null);

        //g.drawImage(VPindicator,1090,295,30,30,null);

    }
}
