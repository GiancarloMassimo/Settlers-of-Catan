import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class InventoryGraphics implements GraphicsItem, MouseEventHandler
{
    private BufferedImage inventoryText, woodpic, brickpic, wheatpic, sheeppic, orepic, Knight, VPCard, Monopoly, YearOfPlenty, RoadBuilding;

    public InventoryGraphics()
    {
        inventoryText = ImageLoader.getImage("Inventory_text");

        woodpic = ImageLoader.getImage("woodpic");
        brickpic = ImageLoader.getImage("brickpic");
        wheatpic = ImageLoader.getImage("wheatpic");
        sheeppic = ImageLoader.getImage("sheeppic");
        orepic = ImageLoader.getImage("orepic");

        Knight = ImageLoader.getImage("Knight");
        VPCard = ImageLoader.getImage("VPCard");
        Monopoly = ImageLoader.getImage("Monopoly");
        YearOfPlenty = ImageLoader.getImage("YearOfPlenty");
        RoadBuilding = ImageLoader.getImage("RoadBuilding");

    }
    public void draw(Graphics g)
    {
        g.drawImage(inventoryText, 39, 747, 188, 44, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 72));

        InputHandler.addMouseEvent(this);

        Inventory current = GameManager.instance.getCurrentPlayer().getInventory();

        g.drawImage(woodpic, 245, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Wood) + "", 265, 796);

        g.drawImage(brickpic, 340, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Brick) + "", 360, 796);

        g.drawImage(wheatpic, 435, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Wheat) + "", 455, 796);

        g.drawImage(sheeppic, 530, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Sheep) + "", 550, 796);

        g.drawImage(orepic, 625, 732, 83, 88, null);
        g.drawString(current.getResourceCount(ResourceType.Ore) + "", 645, 796);

        for (int i = 0; i < GameManager.instance.getCurrentPlayer().armySize; i++) {
            g.drawImage(Knight, 715, 820 + i * 20, 30, 45, null);
        }



        g.drawImage(VPCard, 715, 732, 60, 88, null);

        g.drawImage(Knight, 780, 732, 60, 88, null);

        g.drawImage(Monopoly, 845, 732, 60, 88, null);

        g.drawImage(YearOfPlenty, 910, 732, 60, 88, null);

        g.drawImage(RoadBuilding, 975, 732, 60, 88, null);

        for (int i=0;i<5;i++)
        g.fillOval(755 + i *65, 800 ,26,25);

        g.setColor(Color.BLACK);

        g.setFont(new Font("default", Font.BOLD, 20));
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.VictoryPoint) + "", 763, 820);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.Knight) + "", 828, 820);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.Monopoly) + "", 893, 820);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.YearOfPlenty) + "", 958, 820);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.RoadBuilding) + "", 1023, 820);

    }


    @Override
    public void OnMouseClick(MouseEvent e) {
        Player p = GameManager.instance.getCurrentPlayer();
        DevelopmentCardType type = getCardType(e.getX(), e.getY());
        if(type == null) return;
        Inventory inventory = p.getInventory();
        if(inventory.getDevelopmentCardCount(type) == 0) return;
        GameActionHandler.signalAction(
                GameActionTypes.Instant,
                () -> playCard(type, inventory, p)
        );
    }

    private void playCard(DevelopmentCardType type, Inventory inventory, Player p) {
        if(type == DevelopmentCardType.Monopoly && inventory.canPlayCard(DevelopmentCardType.Monopoly)){
            inventory.playCard(type);
            ItemSelectionController.monopoly(p);
        }
        else if(type == DevelopmentCardType.YearOfPlenty && inventory.canPlayCard(DevelopmentCardType.YearOfPlenty)) {
            inventory.playCard(type);
            ItemSelectionController.yearOfPlenty(p);
        }
        else if(type == DevelopmentCardType.Knight && inventory.canPlayCard(DevelopmentCardType.Knight)) {
            inventory.playCard(type);
            GameManager.instance.getCurrentPlayer().armySize++;
            GameManager.instance.getLargestArmy().checkLargestArmy(GameManager.instance.getCurrentPlayer(), GameManager.instance.getCurrentPlayer().armySize);
            ItemPlacementController.placeRobber();
        }
        else if(type == DevelopmentCardType.RoadBuilding && inventory.canPlayCard(DevelopmentCardType.RoadBuilding)) {
            if (inventory.getItemCount(ItemType.Road) < 2) {
                return;
            }
            inventory.playCard(type);
            GameActionHandler.signalAction(
                    GameActionTypes.Instant,
                    () -> {
                        try {
                            ItemPlacementController.placeRoad();
                        } catch (NoValidPositionForItemException e) {
                            //do nothing
                        }
                    }
            );
            GameActionHandler.queueAction(
                    GameActionTypes.Instant,
                    () -> {
                        try {
                            ItemPlacementController.placeRoad();
                        } catch (NoValidPositionForItemException e) {
                            //do nothing
                        }
                    }
            );
        }
    }

    private DevelopmentCardType getCardType(int x, int y) {
        if(!(y>732 && y<820)) return null;
        if(x>715 && x<775) return DevelopmentCardType.VictoryPoint;
        if(x>780 && x<840) return DevelopmentCardType.Knight;
        if(x>845 && x<905) return DevelopmentCardType.Monopoly;
        if(x>910 && x<970) return DevelopmentCardType.YearOfPlenty;
        if(x>975 && x<1035) return DevelopmentCardType.RoadBuilding;
        return null;
    }
}
