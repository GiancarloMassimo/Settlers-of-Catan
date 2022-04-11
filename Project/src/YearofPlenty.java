public class YearofPlenty extends ProgressCard{

    public YearofPlenty(Player o){
        super(o,ProgressCardType.YearOfPlenty);
    }

    public void use(String c1, String c2){  //gives two of any resource of choice
        String choice1 = c1;
        String choice2 = c2;
        switch(choice1)
        {
            case "brick":
                super.getOwner().getPlayerInventory().changeBrick(1);
            case "sheep":
                super.getOwner().getPlayerInventory().changeSheep(1);
            case "ore":
                super.getOwner().getPlayerInventory().changeOre(1);
            case "wheat":
                super.getOwner().getPlayerInventory().changeWheat(1);
            case "wood":
                super.getOwner().getPlayerInventory().changeWood(1);
        }
        switch(choice2)
        {
            case "brick":
                super.getOwner().getPlayerInventory().changeBrick(1);
            case "sheep":
                super.getOwner().getPlayerInventory().changeSheep(1);
            case "ore":
                super.getOwner().getPlayerInventory().changeOre(1);
            case "wheat":
                super.getOwner().getPlayerInventory().changeWheat(1);
            case "wood":
                super.getOwner().getPlayerInventory().changeWood(1);
        }

    }
}
