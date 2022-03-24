import java.awt.*;
import java.awt.image.BufferedImage;

public class ShopGraphics implements GraphicsItem
{
    private BufferedImage inventoryText, shopText;

    public ShopGraphics()
    {
        inventoryText = ImageLoader.getImage("Inventory_text");
        shopText = ImageLoader.getImage("Shop_");
    }
    public void draw(Graphics g)
    {
        g.drawImage(inventoryText, 39, 747, 188, 44, null);
       // g.drawImage(shopText, 39, 822, 103, 46, null);
    }

}
