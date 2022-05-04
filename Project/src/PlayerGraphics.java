import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphics implements GraphicsItem
{
    private BufferedImage OpaqueSquare;
    private BufferedImage NormalRoad, LongestRoad, NormalArmy, LargestArmy;
    private Player[] players;
    private LongestRoad longestRoad;

    private LargestArmy largestArmy;
    public PlayerGraphics()
    {
        players = GameManager.instance.getPlayers();
        longestRoad = GameManager.instance.getLongestRoad();
        largestArmy = GameManager.instance.getLargestArmy();
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
            g.setFont(new Font("default", Font.BOLD, 16));
            g.drawImage(OpaqueSquare, 1070, 380 + i * 80, 544 , 81 , null);
            g.drawImage(longestRoad.hasLongestRoad(players[i]) ? LongestRoad : NormalRoad, 1150, 400 + i * 80, 77 , 26 , null);

            g.drawImage(GameManager.instance.getLargestArmy().largestArmyPlayer == players[i] ? LargestArmy : NormalArmy, 1265, 385 + i * 80, 15 , 40 , null);

            g.setColor(players[i].getGraphicsInfo().getPlayerColor());
            if (players[i] == GameManager.instance.getCurrentPlayer())
                g.drawRect(1070, 380 + i * 80, 544, 66);
            g.fillArc(1050, 375 + i * 80,75,75,0,360);
            g.setColor(Color.BLACK);
            g.drawArc(1050, 375 + i * 80, 75, 75, 0, 360);

            g.setColor(Color.white);
            g.drawString(longestRoad.getRoadLength(players[i]) + "", 1235, 422 + i * 80);
            
            g.drawString(players[i].armySize + "", 1290, 422 + i * 80);


            g.setFont(new Font("default", Font.BOLD, 50));
            g.drawString(players[i].getPublicVictoryPoints()+"", 1075 - (players[i].getPublicVictoryPoints() > 9 ? 20 : 0), 427 + i * 80);

            if (players[i] == GameManager.instance.getCurrentPlayer() && players[i].getSecretVictoryPoints() > 0) {
                g.setColor(ColorPalette.devCardPurple);
                g.fillOval(1090, 360 + i * 80, 40, 40);
                g.setColor(Color.white);
                g.setFont(new Font("default", Font.BOLD, 32));
                g.drawString(players[i].getSecretVictoryPoints() + "", 1102, 390 + i * 80);
            }
        }
    }
}