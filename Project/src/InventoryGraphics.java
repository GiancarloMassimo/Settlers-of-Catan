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

        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 72));

        Inventory current = GameManager.instance.getCurrentPlayer().getInventory();

        g.drawImage(woodpic, 245, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Wood) + "", 265, 796);

        g.drawImage(brickpic, 340, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Brick) + "", 360, 796);

        g.drawImage(wheatpic, 435, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Wheat) + "", 455, 796);

        g.drawImage(sheeppic, 530, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Sheep) + "", 550, 796);

        g.drawImage(orepic, 625, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Ore) + "", 645, 796);
    }

}
