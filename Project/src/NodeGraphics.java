import java.awt.*;
import java.awt.event.MouseEvent;

public class NodeGraphics implements GraphicsItem, MouseEventHandler {
    Node[] nodes;
    Edge[] edges;
    final int INDICATOR_RADIUS = 12;

    public NodeGraphics(Map map) {
        nodes = map.getNodes();
        edges = map.getEdges();
        InputHandler.addMouseEvent(this);
        assignNodeScreenPositions();
        assignEdgeScreenPositions();
    }

    private void assignNodeScreenPositions() {
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
        //drawNodeIndicators(g);
        drawEdgeIndicators(g);
    }

    private void drawEdgeIndicators(Graphics g) {
        for (Edge edge : edges) {
            drawTranslucentCircle(g, edge.screenX, edge.screenY);
        }
    }

    private void drawNodeIndicators(Graphics g) {
        for (Node node : nodes) {
            drawNodeIndicator(node, g);
        }
    }

    private void drawNodeIndicator(Node node, Graphics g) {
        drawTranslucentCircle(g, node.screenX, node.screenY);
    }

    private void drawTranslucentCircle(Graphics g, int x, int y) {
        g.setColor(ColorPalette.circleIndicator);
        g.fillArc(x, y, INDICATOR_RADIUS * 2, INDICATOR_RADIUS * 2, 0, 360);
    }



    @Override
    public void OnMouseClick(MouseEvent e) {
        Node clicked = getEdgeClicked(e.getX(), e.getY());
        if (clicked != null) {
            for (Tile t : clicked.getAdjacentTiles()) {
                System.out.print(t.getNum() + " ");
            }
            System.out.println();
        }
    }

    private Node getEdgeClicked(int x, int y) {
        for (Node node : nodes) {
            int centerX = node.screenX + INDICATOR_RADIUS;
            int centerY = node.screenY + INDICATOR_RADIUS;
            if (Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2)) < INDICATOR_RADIUS) {
                return node;
            }
        }
        return null;
    }
}