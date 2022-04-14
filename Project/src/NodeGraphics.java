import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class NodeGraphics implements GraphicsItem, MouseEventHandler, ItemPlacer<Node> {
    final int INDICATOR_RADIUS = 12;

    Node[] nodes;
    ItemPlacement<Node> placement = null;

    public NodeGraphics(Map map) {
        nodes = map.getNodes();
        InputHandler.addMouseEvent(this);
        ItemPlacementController.setNodeItemPlacer(this);
    }

    @Override
    public void draw(Graphics g) {
        drawBuildings(g);

        if (placement != null) {
            drawNodeIndicators(g);
        }
    }

    private void drawBuildings(Graphics g) {
        for (Node node : nodes) {
            if (node.building != null) {
                Player player = node.building.getOwner();
                PlayerGraphicsInfo graphicsInfo = player.getGraphicsInfo();
                BuildingType buildingType = node.building.getType();
                BufferedImage buildingImage = buildingType == BuildingType.Settlement ?
                        graphicsInfo.getSettlementImage() :
                        graphicsInfo.getCityImage();

                g.drawImage(buildingImage, node.screenX - 10, node.screenY - 10, null);
            }
        }
    }

    private void drawNodeIndicators(Graphics g) {
        for (Node node : nodes) {
            if (placement.checkCondition(node)) {
                drawNodeIndicator(node, g);
            }
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
        Node clicked = getNodeClicked(e.getX(), e.getY());
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

    private Node getNodeClicked(int x, int y) {
        for (Node node : nodes) {
            int centerX = node.screenX + INDICATOR_RADIUS;
            int centerY = node.screenY + INDICATOR_RADIUS;
            if (Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2)) < INDICATOR_RADIUS) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void startItemPlacement(ItemPlacement<Node> placement) {
        this.placement = placement;
        GameStateChangeListener.invoke();
    }
}
