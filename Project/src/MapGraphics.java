import java.awt.*;
import java.awt.image.BufferedImage;

public class MapGraphics implements GraphicsItem {
    private final int CORNER_RADIUS = 10;

    private BufferedImage boardBackground;
    private Map map;

    public MapGraphics() {
        boardBackground = ImageLoader.getImage("BoardBackground");
        map = new Map();
    }

    @Override
    public void draw(Graphics g) {
        drawBackground(g);
        drawTiles(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(ColorPalette.darkBackground);
        g.fillRoundRect(40, 25, 1000, 700, CORNER_RADIUS, CORNER_RADIUS);
        g.drawImage(boardBackground, 170, 80, 700, 606, null);
    }

    private void drawTiles(Graphics g) {
        int spacingX = 100;
        int spacingY = 85;
        int offsetX = 275;
        int offsetY = 150;
        int numberOffsetY = 10;

        Tile[][] mat = map.getMap();
        for (int r = 0; r < mat.length; r++) {
            int translationX = Math.abs(((mat.length + 1) / 2) - (r + 1)) * (spacingX / 2);
            for (int c = 0; c < mat[r].length; c++) {
                g.drawImage(mat[r][c].getImage(), translationX + offsetX + spacingX * c, offsetY + spacingY * r, 100, 115, null);
                g.drawImage(mat[r][c].getNumberImage(), translationX + offsetX + spacingX * c, offsetY + spacingY * r + numberOffsetY, 99, 87, null);
            }
        }

    }
}
