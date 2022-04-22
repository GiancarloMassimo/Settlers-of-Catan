import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ItemSelectionGraphics implements GraphicsItem, KeyEventHandler, MouseEventHandler {
    BufferedImage selectionInventoryPanel, itemSelectionPanel, bankIcon;

    ItemSelection selection = null;
    Player currentPlayer = null;
    Bank bank;

    public ItemSelectionGraphics() {
        selectionInventoryPanel = ImageLoader.getImage("SelectionInventoryPanel");
        itemSelectionPanel = ImageLoader.getImage("ItemSelectionPanel");
        bankIcon = ImageLoader.getImage("bankIcon");
        bank = GameManager.instance.getBank();

        InputHandler.addMouseEvent(this);
        InputHandler.addKeyEvent(this);
        ItemSelectionController.setItemSelector(this);
    }

    @Override
    public void draw(Graphics g) {
        if (selection != null) {
            drawInventorySelectionPanel(g);
            drawItemSelectionPanel(g);
        }
    }

    private void drawInventorySelectionPanel(Graphics g) {
        g.drawImage(selectionInventoryPanel, 56, 515, null);

        if (currentPlayer == null) {
            g.drawImage(bankIcon, 75, 540, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("default", Font.BOLD, 20));
            g.drawString(bank.getStockOfResource(ResourceType.Wood) + "", 160, 560);
            g.drawString(bank.getStockOfResource(ResourceType.Brick) + "", 160 + 68, 560);
            g.drawString(bank.getStockOfResource(ResourceType.Wheat) + "", 160 + 68 * 2, 560);
            g.drawString(bank.getStockOfResource(ResourceType.Sheep) + "", 160 + 68 * 3, 560);
            g.drawString(bank.getStockOfResource(ResourceType.Ore) + "", 160 + 68 * 4, 560);
        } else {
            Inventory inventory = currentPlayer.getInventory();
            g.setColor(currentPlayer.getGraphicsInfo().getPlayerColor());
            g.fillOval(75, 538, 35, 35);
            g.setColor(Color.BLACK);
            g.setFont(new Font("default", Font.BOLD, 20));
            g.drawString(inventory.getResourceCount(ResourceType.Wood) + "", 160, 560);
            g.drawString(inventory.getResourceCount(ResourceType.Brick) + "", 160 + 68, 560);
            g.drawString(inventory.getResourceCount(ResourceType.Wheat) + "", 160 + 68 * 2, 560);
            g.drawString(inventory.getResourceCount(ResourceType.Sheep) + "", 160 + 68 * 3, 560);
            g.drawString(inventory.getResourceCount(ResourceType.Ore) + "", 160 + 68 * 4, 560);
        }
    }

    private void drawItemSelectionPanel(Graphics g) {
        g.drawImage(itemSelectionPanel, 56, 595, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 20));
        g.drawString(selection.getSelectionMessage(), 80, 625);
        g.setFont(new Font("default", Font.TRUETYPE_FONT, 14));
        g.drawString("Click to select resources", 75, 690);
        g.drawString("R to reset", 75, 705);
        if (selection.isCompleteSelection())
            g.drawString("C to confirm", 325, 700);

        g.drawString(selection.getSelection().get(ResourceType.Wood) + "", 110, 665);
        g.drawString(selection.getSelection().get(ResourceType.Brick) + "", 110 + 68, 665);
        g.drawString(selection.getSelection().get(ResourceType.Wheat) + "", 110 + 68 * 2, 665);
        g.drawString(selection.getSelection().get(ResourceType.Sheep) + "", 110 + 68 * 3, 665);
        g.drawString(selection.getSelection().get(ResourceType.Ore) + "", 110 + 68 * 4, 665);
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        if ((e.getKeyChar() == 'c' || e.getKeyChar() == 'C')
                && selection != null && selection.isCompleteSelection()) {
            GameActionHandler.signalAction(
                    GameActionTypes.EndMultiStageAction,
                    () -> {
                        selection.confirm();
                        selection = null;
                        currentPlayer = null;
                    }
            );
        } else if ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
                && selection != null) {
            selection.clearSelection();
        }
    }

    public void startSelection(ItemSelection selection) {
        this.selection = selection;
    }

    public void startSelection(ItemSelection selection, Player player) {
        this.selection = selection;
        this.currentPlayer = player;
        if (selection instanceof DiscardItemSelection) {
            selection.setUp(player);
        }
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        ResourceType clicked = getResourceClicked(e.getX(), e.getY());
        if (clicked != null) {
            if (selection != null) {
                selection.tryItemSelection(clicked);
                GameStateChangeListener.invoke();
            }
        }
    }

    ResourceType getResourceClicked(int x, int y) {
        final int itemRadius = 20;
        final int[] woodPos = {110, 665},
                brickPos = {110 + 68, 665},
                wheatPos = {110 + 68 * 2, 665},
                sheepPos = {110 + 68 * 3, 665},
                orePos = {110 + 68 * 4, 665};

        if (Helpers.getDistance(x, y, woodPos[0], woodPos[1]) < itemRadius) return ResourceType.Wood;
        if (Helpers.getDistance(x, y, brickPos[0], brickPos[1]) < itemRadius) return ResourceType.Brick;
        if (Helpers.getDistance(x, y, wheatPos[0], wheatPos[1]) < itemRadius) return ResourceType.Wheat;
        if (Helpers.getDistance(x, y, sheepPos[0], sheepPos[1]) < itemRadius) return ResourceType.Sheep;
        if (Helpers.getDistance(x, y, orePos[0], orePos[1]) < itemRadius) return ResourceType.Ore;

        return null;
    }
}
