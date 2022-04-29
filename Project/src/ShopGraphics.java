import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ShopGraphics implements GraphicsItem, MouseEventHandler
{
    private BufferedImage ShopText, DevCard, brickpic, wheatpic, sheeppic, woodpic, orepic;

    private Player[] players;
    public ShopGraphics()
    {
        players = GameManager.instance.getPlayers();
        InputHandler.addMouseEvent(this);

        ShopText = ImageLoader.getImage("Shop_");
        DevCard = ImageLoader.getImage("DevCard");

        woodpic = ImageLoader.getImage("woodpic");
        brickpic = ImageLoader.getImage("brickpic");
        wheatpic = ImageLoader.getImage("wheatpic");
        sheeppic = ImageLoader.getImage("sheeppic");
        orepic = ImageLoader.getImage("orepic");
    }

    public void draw(Graphics g)
    {
        PlayerGraphicsInfo graphicsInfo = GameManager.instance.getCurrentPlayerGraphicsInfo();

        g.drawImage(ShopText, 39, 822, 103, 46, null);
        g.drawImage(graphicsInfo.getRoadImage(), 250, 810, 10, 50, null);
        g.drawImage(DevCard, 560, 810, 30, 40, null);
        g.drawImage(graphicsInfo.getCityImage(), 410, 805, 65, 49, null);
        g.drawImage(graphicsInfo.getSettlementImage(), 310, 810, 45, 38, null);

        //Road
        g.drawImage(woodpic, 265, 810, 25, 25, null);
        g.drawImage(brickpic, 265, 835, 25, 25, null);

        //Village
        g.drawImage(woodpic, 350, 810, 25, 25, null);
        g.drawImage(wheatpic, 375, 835, 25, 25, null);
        g.drawImage(brickpic, 350, 835, 25, 25, null);
        g.drawImage(sheeppic, 375, 810, 25, 25, null);

        //City
        g.drawImage(wheatpic, 470, 810, 25, 25, null);
        g.drawImage(wheatpic, 495, 810, 25, 25, null);
        g.drawImage(orepic, 470, 835, 25, 25, null);
        g.drawImage(orepic, 495, 835, 25, 25, null);
        g.drawImage(orepic, 520, 835, 25, 25, null);

        //Development Card
        g.drawImage(sheeppic, 595, 810, 25, 25, null);
        g.drawImage(wheatpic, 620, 810, 25, 25, null);
        g.drawImage(orepic, 595, 835, 25, 25, null);

        for(int i = 0; i < 3; i++)
        {
            g.setColor(GameManager.instance.getCurrentPlayer().getGraphicsInfo().getPlayerColor());
            g.fillArc(225, 800, 25, 25, 0, 360);
            g.fillArc(400, 800, 25, 25, 0, 360);
            g.fillArc(300, 800, 25, 25, 0, 360);

            g.setColor(Color.black);
            g.setFont(new Font("default", Font.PLAIN, 20));
            g.drawString(GameManager.instance.getCurrentPlayer().getInventory().getItemCount(ItemType.Settlement)+"", 307, 820);
            g.drawString(GameManager.instance.getCurrentPlayer().getInventory().getItemCount(ItemType.Road)+"", 225, 820);
            g.drawString(GameManager.instance.getCurrentPlayer().getInventory().getItemCount(ItemType.City)+"", 407, 820);


        }
    }


    @Override
    public void OnMouseClick(MouseEvent e) {
        ItemType clicked = getItemClicked(e.getX(), e.getY());

        if (clicked != null) {
            Inventory inventory = GameManager.instance.getCurrentPlayer().getInventory();
            Bank bank = GameManager.instance.getBank();

            GameActionHandler.signalAction(
                    GameActionTypes.Instant,
                    () -> bank.purchase(inventory, clicked)
            );
        }

    }

    private ItemType getItemClicked(int x, int y) {
        final int itemRadius = 30;
        final int[] roadPos = {250, 810},
                    settlementPos = {310, 810},
                    cityPos = {410, 810},
                    DevPos = {540, 810};

        if (Helpers.getDistance(x, y, roadPos[0] + 20, roadPos[1] + 20) < itemRadius) return ItemType.Road;
        if (Helpers.getDistance(x, y, settlementPos[0] + 20, settlementPos[1] + 20) < itemRadius) return ItemType.Settlement;
        if (Helpers.getDistance(x, y, cityPos[0] + 20, cityPos[1] + 20) < itemRadius) return ItemType.City;
        if (Helpers.getDistance(x, y, DevPos[0] + 20, DevPos[1] + 20) < itemRadius) return ItemType.DevelopmentCard;

        return null;
    }
}
