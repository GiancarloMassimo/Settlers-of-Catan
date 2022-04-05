import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public static GamePanel instance;

    private ArrayList<GraphicsItem> graphicsItems = new ArrayList<>();

    public GamePanel() {
        if (instance == null)
            instance = this;

        setFocusable(true);

        addGraphicItem(new BackgroundGraphics());
        addGraphicItem(new InfoPanelGraphics());
        addGraphicItem(new MapGraphics());
        addGraphicItem(new InventoryGraphics());
        addGraphicItem(new ShopGraphics());
        addGraphicItem(new TradingGraphics());
        addGraphicItem(new PlayerGraphics());
    }

    private void addGraphicItem(GraphicsItem graphicsItem) {
        if (!graphicsItems.add(graphicsItem)) {
            System.out.println("Could not add graphics item: " + graphicsItem.getClass().getName());
        }
    }

    @Override
    public void paint(Graphics g) {
        drawGraphicsItems(g);
    }

    private void drawGraphicsItems(Graphics g) {
        for (GraphicsItem graphicsItem : graphicsItems) {
            graphicsItem.draw(g);
        }
    }
}
