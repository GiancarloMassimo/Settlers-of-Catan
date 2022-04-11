public class YearofPlenty extends ProgressCard{
    //TODO: WRITE THIS WITH ENUMS;
    public YearofPlenty(Player o){
        super(o,ProgressCardType.YearOfPlenty);
    }

    public void use(String c1, String c2){  //gives two of any resource of choice
        String choice1 = c1;
        String choice2 = c2;
        switch(choice1)
        {
            case "brick":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Brick, 1);
            case "sheep":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Sheep, 1);
            case "ore":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Ore, 1);
            case "wheat":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Wheat, 1);
            case "wood":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Wood, 1);
        }
        switch(choice2)
        {
            case "brick":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Brick, 1);
            case "sheep":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Sheep, 1);
            case "ore":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Ore, 1);
            case "wheat":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Wheat, 1);
            case "wood":
                super.getOwner().getPlayerInventory().receiveItem(ResourceType.Wood, 1);
        }

    }
}
