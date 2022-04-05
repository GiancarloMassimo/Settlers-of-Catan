import java.awt.*;
import java.awt.image.BufferedImage;

public class TradingGraphics implements GraphicsItem
{
    private BufferedImage TradingBox;
    public TradingGraphics()
    {
        TradingBox = ImageLoader.getImage("Trading");
    }

    public void draw(Graphics g)
    {
        g.drawImage(TradingBox, 1050, 720, 537, 147, null);
    }
}
