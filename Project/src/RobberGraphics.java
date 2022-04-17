import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//TODO: All these NodeGraphics, EdgeGraphics, RobberGraphics classes
//      that draw indicators really should be extending the same parent class
//      but I'm too lazy to do that now

public class RobberGraphics implements GraphicsItem, MouseEventHandler, ItemPlacer<Tile> {
    private final int INDICATOR_RADIUS = 12;
    private final int drawOffsetX = 40, drawOffsetY = 35;

    Robber robber;
    BufferedImage image;
    Tile[][] tiles;

    ItemPlacement<Tile> placement = null;

    public RobberGraphics () {
        robber = GameManager.instance.getRobber();
        tiles = GameManager.instance.getMap().getTiles();
        image = ImageLoader.getImage("Robber");

        InputHandler.addMouseEvent(this);
        ItemPlacementController.setTileItemPlacer(this);
    }


    @Override
    public void draw(Graphics g) {
        drawRobber(g);
        if (placement != null)
            drawTileIndicators(g);
    }

    private void drawTileIndicators(Graphics g) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (placement.checkCondition(tile))
                    drawTileIndicator(g, tile);
            }
        }
    }

    private void drawTileIndicator(Graphics g, Tile tile) {
        drawTranslucentCircle(g, tile.screenX + drawOffsetX, tile.screenY + drawOffsetY);
    }

    private void drawTranslucentCircle(Graphics g, int x, int y) {
        g.setColor(ColorPalette.circleIndicator);
        g.fillArc(x, y, INDICATOR_RADIUS * 2, INDICATOR_RADIUS * 2, 0, 360);
    }

    private void drawRobber(Graphics g) {
        g.drawImage(
                image,
                robber.getTile().screenX + drawOffsetX,
                robber.getTile().screenY + drawOffsetY,
                null);
    }

    @Override
    public void startItemPlacement(ItemPlacement<Tile> placement) {
        this.placement = placement;
        GameStateChangeListener.invoke();
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        Tile clicked = getTileClicked(e.getX(), e.getY());

        if (clicked != null) {
            if (placement != null) {
                if (placement.checkCondition(clicked)) {
                    GameActionHandler.signalAction(
                            GameActionTypes.EndMultiStageAction,
                            () -> {
                                placement.place(clicked);
                                placement = null;
                                GameStateChangeListener.invoke();
                            }
                    );
                }
            }
        }

    }

    private Tile getTileClicked(int x, int y) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                double centerX = tile.screenX + INDICATOR_RADIUS + drawOffsetX;
                double centerY = tile.screenY + INDICATOR_RADIUS + drawOffsetY;
                if (Helpers.getDistance(centerX, centerY, x, y) < INDICATOR_RADIUS) {
                    return tile;
                }
            }
        }
        return null;
    }
}
