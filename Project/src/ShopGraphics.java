import java.awt.*;
import java.awt.image.BufferedImage;

public class ShopGraphics implements GraphicsItem
{
    private BufferedImage ShopText, DevCard, brickpic, wheatpic, sheeppic, woodpic, orepic;
    public ShopGraphics()
    {
        ShopText = ImageLoader.getImage("Shop_");
        DevCard = ImageLoader.getImage("DevCard");

        woodpic = ImageLoader.getImage("woodpic");
        brickpic = ImageLoader.getImage("brickpic");
        wheatpic = ImageLoader.getImage("wheatpic");
        sheeppic = ImageLoader.getImage("sheeppic");
        orepic = ImageLoader.getImage("orepic");
    }

    public void draw(Graphics g)
    {
        PlayerGraphicsInfo graphicsInfo = GameManager.instance.getCurrentPlayerGraphicsInfo();

        g.drawImage(ShopText, 39, 822, 103, 46, null);
        g.drawImage(graphicsInfo.getRoadImage(), 250, 810, 10, 50, null);
        g.drawImage(DevCard, 560, 810, 30, 40, null);
        g.drawImage(graphicsInfo.getCityImage(), 410, 805, 65, 49, null);
        g.drawImage(graphicsInfo.getSettlementImage(), 310, 810, 45, 38, null);

        //Road
        g.drawImage(woodpic, 265, 810, 25, 25, null);
        g.drawImage(brickpic, 265, 835, 25, 25, null);

        //Village
        g.drawImage(woodpic, 350, 810, 25, 25, null);
        g.drawImage(wheatpic, 375, 835, 25, 25, null);
        g.drawImage(brickpic, 350, 835, 25, 25, null);
        g.drawImage(sheeppic, 375, 810, 25, 25, null);

        //City
        g.drawImage(wheatpic, 470, 810, 25, 25, null);
        g.drawImage(wheatpic, 495, 810, 25, 25, null);
        g.drawImage(orepic, 470, 835, 25, 25, null);
        g.drawImage(orepic, 495, 835, 25, 25, null);
        g.drawImage(orepic, 520, 835, 25, 25, null);

        //Development Card
        g.drawImage(sheeppic, 595, 810, 25, 25, null);
        g.drawImage(wheatpic, 620, 810, 25, 25, null);
        g.drawImage(orepic, 595, 835, 25, 25, null);
    }
}
