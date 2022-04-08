import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class DiceGraphics implements GraphicsItem {
    HashMap<Integer, BufferedImage> diceNumberImageMap = new HashMap<>();
    Dice dice;

    public DiceGraphics() {
        dice = GameManager.instance.getDice();
        diceNumberImageMap.put(1, ImageLoader.getImage("1Dice"));
        diceNumberImageMap.put(2, ImageLoader.getImage("2Dice"));
        diceNumberImageMap.put(3, ImageLoader.getImage("3Dice"));
        diceNumberImageMap.put(4, ImageLoader.getImage("4Dice"));
        diceNumberImageMap.put(5, ImageLoader.getImage("5Dice"));
        diceNumberImageMap.put(6, ImageLoader.getImage("6Dice"));
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage dice1 = diceNumberImageMap.get(dice.getDie1()),
                      dice2 = diceNumberImageMap.get(dice.getDie2());

        g.drawImage(dice1, 796, 612, 75, 75, null);
        g.drawImage(dice2, 896, 612, 75, 75, null);
    }
}
