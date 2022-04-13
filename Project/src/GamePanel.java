import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, MouseListener {
    public static GamePanel instance;

    private ArrayList<GraphicsItem> graphicsItems = new ArrayList<>();

    public GamePanel() {
        if (instance == null)
            instance = this;

        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);

        Map map = new Map();

        addGraphicItem(new BackgroundGraphics());
        addGraphicItem(new InfoPanelGraphics());
        addGraphicItem(new MapGraphics(map));
        addGraphicItem(new InventoryGraphics());
        addGraphicItem(new ShopGraphics());
        addGraphicItem(new TradingGraphics());
        addGraphicItem(new PlayerGraphics());
        addGraphicItem(new NodeGraphics(map));
        addGraphicItem(new DiceGraphics());

        GameManager.instance.onWindowLoad();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        InputHandler.InvokeMouseEvents(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        InputHandler.InvokeKeyEvents(e);
    }

    //region Unimplemented and unused input methods from interface
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    // endregion
}
