import java.awt.*;
import java.awt.event.MouseEvent;

public class EdgeGraphics implements GraphicsItem, MouseEventHandler, ItemPlacer<Edge> {
    final int INDICATOR_RADIUS = 12;

    Edge[] edges;
    ItemPlacement<Edge> placement = null;

    public EdgeGraphics(Map map) {
        edges = map.getEdges();
        InputHandler.addMouseEvent(this);
        ItemPlacementController.setEdgeItemPlacer(this);
        //TODO: I don't like this function being in this class, but I'll change it later
        assignNodeScreenPositions(map.getNodes());
        assignEdgeScreenPositions();
    }

    private void assignNodeScreenPositions(Node[] nodes) {
        for (Node node : nodes) {
            Tile referenceTile = node.getAdjacentTiles().get(0);

            int x = referenceTile.screenX; int y = referenceTile.screenY;
            int[] upperOffset = {38, -12};
            int[] upperLeftOffset = {-10, 15};
            int[] upperRightOffset = {87, 15};
            int[] lowerOffset = {38, 95};
            int[] lowerLeftOffset = {-10, 75};
            int[] lowerRightOffset = {87, 75};

            int[] offset = null;

            if (node == referenceTile.upper) offset = upperOffset;
            if (node == referenceTile.upperLeft) offset = upperLeftOffset;
            if (node == referenceTile.upperRight) offset = upperRightOffset;
            if (node == referenceTile.lower) offset = lowerOffset;
            if (node == referenceTile.lowerLeft) offset = lowerLeftOffset;
            if (node == referenceTile.lowerRight) offset = lowerRightOffset;

            node.screenX = x + offset[0];
            node.screenY = y + offset[1];
        }
    }

    private void assignEdgeScreenPositions() {
        for (Edge edge : edges) {
            edge.screenX = (edge.a.screenX + edge.b.screenX) / 2;
            edge.screenY = (edge.a.screenY + edge.b.screenY) / 2;
        }
    }


    @Override
    public void draw(Graphics g) {
        if (placement != null)
            drawEdgeIndicators(g);
        drawRoads(g);
    }

    private void drawEdgeIndicators(Graphics g) {
        for (Edge edge : edges) {
            if (placement.checkCondition(edge)) {
                drawTranslucentCircle(g, edge.screenX, edge.screenY);
            }
        }
    }

    private void drawRoads(Graphics g) {
        for (Edge edge : edges) {
            if (edge.road != null) {
                PlayerGraphicsInfo playerGraphics = edge.road.getOwner().getGraphicsInfo();

                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(10.0F));
                g2d.setColor(playerGraphics.getPlayerColor());
                g2d.drawLine(edge.a.screenX + 20, edge.a.screenY + 20,
                        edge.b.screenX + 20, edge.b.screenY + 20);
            }
        }
    }

    private void drawTranslucentCircle(Graphics g, int x, int y) {
        g.setColor(ColorPalette.circleIndicator);
        g.fillArc(x, y, INDICATOR_RADIUS * 2, INDICATOR_RADIUS * 2, 0, 360);
    }

    @Override
    public void startItemPlacement(ItemPlacement<Edge> placement) {
        this.placement = placement;
        GameStateChangeListener.invoke();
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        Edge clicked = getEdgeClicked(e.getX(), e.getY());

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

    private Edge getEdgeClicked(int x, int y) {
        for (Edge edge : edges) {
            int centerX = edge.screenX + INDICATOR_RADIUS;
            int centerY = edge.screenY + INDICATOR_RADIUS;
            if (Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2)) < INDICATOR_RADIUS) {
                return edge;
            }
        }
        return null;
    }
}
