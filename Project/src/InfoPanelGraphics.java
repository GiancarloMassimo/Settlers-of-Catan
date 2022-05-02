import java.awt.*;
import java.io.InputStream;

public class InfoPanelGraphics implements GraphicsItem{
    private final String INFO_TEXT = "Welcome to Settlers of Catan!\n" +
            "\n" +
            "The current player is highlighted below in their color.\n" +
            "Longest Road and Largest Army will be highlighted next to each player.\n" +
            "To buy resources, you must click the item desired from the shop below.\n" +
            "Use the trading panel to trade with other players on your turn.\n" +
            "• Click on a resource to offer/ask for it.\n" +
            "• Players will click on their color to accept the trade offer.\n" +
            "• Port trades are made through the bank: simply offer the correct ratio\n" +
            "of resources.\n" +
            "Click on a Development Card to play it.\n" +
            "Press E to end turn.    Press L (on this window) to toggle the Game Log.";
    private final float FONT_SIZE = 14f;
    private final float BORDER_WIDTH = 4f;
    private final int CORNER_RADIUS = 10;

    private Font font;

    public InfoPanelGraphics() {
        InputStream is = GamePanel.class.getResourceAsStream("/Fonts/OpenSans-Regular.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (Exception e) {
            System.out.println("Font could not be loaded");
        }
    }

    @Override
    public void draw(Graphics g) {
        drawBackgroundPanel(g);
        drawText(g);
    }

    private void drawBackgroundPanel(Graphics g) {
        g.setColor(ColorPalette.beigeBackground);
        g.fillRoundRect(1056, 25, 525, 250, CORNER_RADIUS, CORNER_RADIUS);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(BORDER_WIDTH));
        g.drawRoundRect(1056, 25, 525, 250, CORNER_RADIUS, CORNER_RADIUS);
    }

    private void drawText(Graphics g) {
        String[] lines = INFO_TEXT.split("\n");
        int startX = 1070;
        int startY = 45;
        int lineSpacing = 20;

        g.setFont(font.deriveFont(FONT_SIZE));
        g.setColor(Color.BLACK);

        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], startX, startY + i * lineSpacing);
        }
    }
}
