import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerGraphicsInfo {
    private Color playerColor;
    private BufferedImage roadImage, settlementImage, cityImage;

    public PlayerGraphicsInfo (PlayerColor color) {
        String imageName = "";

        switch (color) {
            case Red:
                imageName = "Red";
                playerColor = ColorPalette.playerRed;
                break;
            case Blue:
                imageName = "Blue";
                playerColor = ColorPalette.playerBlue;
                break;
            case Green:
                imageName = "Green";
                playerColor = ColorPalette.playerGreen;
                break;
            case Yellow:
                imageName = "Yellow";
                playerColor = ColorPalette.playerYellow;
                break;
        }

        LoadImages(imageName);
    }

    private void LoadImages(String imageName) {
        roadImage = ImageLoader.getImage(imageName + "Road");
        settlementImage = ImageLoader.getImage(imageName + "Settlement");
        cityImage = ImageLoader.getImage(imageName + "City");
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public BufferedImage getRoadImage() {
        return roadImage;
    }

    public BufferedImage getSettlementImage() {
        return settlementImage;
    }

    public BufferedImage getCityImage() {
        return cityImage;
    }
}
