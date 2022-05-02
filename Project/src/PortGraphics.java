import java.awt.*;

public class PortGraphics implements GraphicsItem {
    Port[] ports;

    public PortGraphics() {
        ports = GameManager.instance.getMap().getPorts();
    }


    @Override
    public void draw(Graphics g) {
        drawPorts(g);
    }

    private void drawPorts(Graphics g) {
        for (Port port : ports) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(10.0F));
            g2d.setColor(ColorPalette.portBrown);

            for (Node node : port.nodes) {
                g2d.drawLine(port.screenX + 30, port.screenY + 30, node.screenX + 15, node.screenY + 15);
            }

            g.drawImage(port.image, port.screenX, port.screenY, null);
        }
    }
}
