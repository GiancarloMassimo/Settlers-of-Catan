import java.awt.*;
import java.awt.image.BufferedImage;

public class BankPanelGraphics implements GraphicsItem {
    BufferedImage bankPanelImage;
    Bank bank;

    public BankPanelGraphics() {
        bankPanelImage = ImageLoader.getImage("BankPanel");
        bank = GameManager.instance.getBank();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bankPanelImage, 1056, 285, null);
        g.setColor(Color.black);
        g.setFont(new Font("default", Font.BOLD, 20));

        g.drawString(bank.getStockOfResource(ResourceType.Wood) + "", 1172, 330);
        g.drawString(bank.getStockOfResource(ResourceType.Brick) + "", 1172 + 74, 330);
        g.drawString(bank.getStockOfResource(ResourceType.Wheat) + "", 1172 + 74 * 2, 330);
        g.drawString(bank.getStockOfResource(ResourceType.Sheep) + "", 1172 + 74 * 3, 330);
        g.drawString(bank.getStockOfResource(ResourceType.Ore) + "", 1172 + 74 * 4, 330);
        g.drawString(bank.getDevelopmentCardCount() + "", 1172 + 74 * 5, 330);
    }
}
