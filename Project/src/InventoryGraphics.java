import java.awt.*;
import java.awt.image.BufferedImage;

public class InventoryGraphics implements GraphicsItem
{
    private BufferedImage inventoryText, woodpic, brickpic, wheatpic, sheeppic, orepic;

    public InventoryGraphics()
    {
        inventoryText = ImageLoader.getImage("Inventory_text");

        woodpic = ImageLoader.getImage("woodpic");
        brickpic = ImageLoader.getImage("brickpic");
        wheatpic = ImageLoader.getImage("wheatpic");
        sheeppic = ImageLoader.getImage("sheeppic");
        orepic = ImageLoader.getImage("orepic");

    }
    public void draw(Graphics g)
    {
        g.drawImage(inventoryText, 39, 747, 188, 44, null);

        g.drawImage(woodpic, 245, 732, 83, 88, null);
        g.drawImage(brickpic, 340, 732, 83, 88, null);
        g.drawImage(wheatpic, 435, 732, 83, 88, null);
        g.drawImage(sheeppic, 530, 732, 83, 88, null);
        g.drawImage(orepic, 625, 732, 83, 88, null);
    }

}
