import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphics implements GraphicsItem
{
    private BufferedImage OpaqueSquare;
    private BufferedImage NormalRoad, LongestRoad, NormalArmy, LargestArmy;
    private Player[] players;


    public PlayerGraphics()
    {
        players = GameManager.instance.getPlayers();
        OpaqueSquare = ImageLoader.getImage("SemiOpaqueBackground");

        NormalRoad = ImageLoader.getImage("Normal Road");
        LongestRoad = ImageLoader.getImage("Longest Road");
        NormalArmy = ImageLoader.getImage("Normal Army");
        LargestArmy = ImageLoader.getImage("Largest Army");


    }
    public void draw(Graphics g)
    {
        for (int i = 0; i < players.length; i++)
        {
            g.drawImage(OpaqueSquare, 1070, 300 + i * 100, 544 , 81 , null);
            g.drawImage(NormalRoad, 1150, 320 + i * 100, 77 , 26 , null);
            g.drawImage(NormalArmy, 1265, 315 + i * 100, 15 , 40 , null);
            g.setColor(players[i].getGraphicsInfo().getPlayerColor());
            g.fillArc(1050, 295 + i * 100,75,75,0,360);
            g.setColor(Color.BLACK);
            g.drawArc(1050, 295 + i * 100, 75, 75, 0, 360);

            g.setColor(Color.white);
            g.drawString("1", 1290, 342 + i * 100);
            g.drawString("1", 1235, 342 + i * 100);

            g.setFont(new Font("default", Font.BOLD, 50));
            g.drawString(players[i].getVictoryPoints()+"", 1075, 347 + i * 100);
            g.setFont(new Font("default", Font.BOLD, 16));
        }
    }
}
