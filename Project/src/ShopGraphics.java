import java.awt.*;
import java.awt.image.BufferedImage;

public class ShopGraphics implements GraphicsItem
{
    private BufferedImage ShopText, Road, DevCard, City, Village, brickpic, wheatpic, sheeppic, woodpic, orepic;
    public ShopGraphics()
    {
        ShopText = ImageLoader.getImage("Shop_");
        Road = ImageLoader.getImage("Road");
        DevCard = ImageLoader.getImage("DevCard");
        City = ImageLoader.getImage("City");
        Village = ImageLoader.getImage("Village");

        woodpic = ImageLoader.getImage("woodpic");
        brickpic = ImageLoader.getImage("brickpic");
        wheatpic = ImageLoader.getImage("wheatpic");
        sheeppic = ImageLoader.getImage("sheeppic");
        orepic = ImageLoader.getImage("orepic");
    }

    public void draw(Graphics g)
    {
        g.drawImage(ShopText, 39, 822, 103, 46, null);
        g.drawImage(Road, 250, 810, 10, 50, null);
        g.drawImage(DevCard, 600, 810, 30, 40, null);
        g.drawImage(City, 450, 810, 65, 49, null);
        g.drawImage(Village, 310, 810, 45, 38, null);

        g.drawImage(woodpic, 275, 810, 25, 25, null);
        g.drawImage(brickpic, 370, 810, 25, 25, null);
        g.drawImage(wheatpic, 465, 810, 25, 25, null);
        g.drawImage(sheeppic, 560, 810, 25, 25, null);
        g.drawImage(orepic, 655, 810, 25, 25, null);
    }
}
