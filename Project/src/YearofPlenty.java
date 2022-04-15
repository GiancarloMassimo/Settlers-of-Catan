public class YearofPlenty extends ProgressCard{

    public YearofPlenty(Player o){
        super(o,ProgressCardType.YearOfPlenty);
    }

    public void use(ResourceType c1, ResourceType c2){  //gives two of any resource of choice
        switch(c1)
        {
            case Brick:
                super.getOwner().getInventory().receiveItem(ResourceType.Brick,1);
            case Sheep:
                super.getOwner().getInventory().receiveItem(ResourceType.Sheep,1);
            case Ore:
                super.getOwner().getInventory().receiveItem(ResourceType.Ore ,1);
            case Wheat:
                super.getOwner().getInventory().receiveItem(ResourceType.Wheat,1);
            case Wood:
                super.getOwner().getInventory().receiveItem(ResourceType.Wood,1);
        }

        switch(c2)
        {
            case Brick:
                super.getOwner().getInventory().receiveItem(ResourceType.Brick,1);
            case Sheep:
                super.getOwner().getInventory().receiveItem(ResourceType.Sheep,1);
            case Ore:
                super.getOwner().getInventory().receiveItem(ResourceType.Ore ,1);
            case Wheat:
                super.getOwner().getInventory().receiveItem(ResourceType.Wheat,1);
            case Wood:
                super.getOwner().getInventory().receiveItem(ResourceType.Wood,1);
        }

    }
}
