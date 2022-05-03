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

        g.drawImage(VPCard, 715, 732, 60, 88, null);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.VictoryPoint) + "", 730, 796);

        g.drawImage(Knight, 780, 732, 60, 88, null);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.Knight) + "", 795, 796);

        g.drawImage(Monopoly, 845, 732, 60, 88, null);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.Monopoly) + "", 860, 796);

        g.drawImage(YearOfPlenty, 910, 732, 60, 88, null);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.YearOfPlenty) + "", 925, 796);

        g.drawImage(RoadBuilding, 975, 732, 60, 88, null);
        g.drawString(current.getDevelopmentCardCount(DevelopmentCardType.RoadBuilding) + "", 990, 796);
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        Player p = GameManager.instance.getCurrentPlayer();
        DevelopmentCardType type = getCardType(e.getX(), e.getY());
        if(type == null) return;
        Inventory inventory = p.getInventory();
        if(inventory.getDevelopmentCardCount(type) == 0) return;
        if(type == DevelopmentCardType.Monopoly){
            ItemSelectionController.monopoly(p);
            System.out.println("used monopoly");
        }
        else if(type == DevelopmentCardType.YearOfPlenty) {
            ItemSelectionController.yearOfPlenty(p);
            System.out.println("used yearofplenty");
        }
        else if(type == DevelopmentCardType.Knight) {
            System.out.println("used knight");
        }
        else if(type == DevelopmentCardType.RoadBuilding) {
            System.out.println("used roadbuilding");
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
