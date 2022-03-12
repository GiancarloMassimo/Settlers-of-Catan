import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class GamePanel extends JPanel {
    public static GamePanel instance;

    private HashSet<GraphicsItem> graphicsItems = new HashSet<>();

    public GamePanel() {
        if (instance == null)
            instance = this;

        setFocusable(true);

        addGraphicItem(new BackgroundGraphics());
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
