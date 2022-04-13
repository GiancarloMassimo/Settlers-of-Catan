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

        g.setColor(Color.black);
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString("1", 1185,790);
        g.drawString("1", 1185,823);
        g.drawString("1", 1240,790);
        g.drawString("1", 1240,823);
        g.drawString("1", 1295,790);
        g.drawString("1", 1295,823);
        g.drawString("1", 1350,790);
        g.drawString("1", 1350,823);
    }
}
