public class YearofPlenty extends ProgressCard{

    public YearofPlenty(Player o){
        super(o,ProgressCardType.YearOfPlenty);
    }

    public void use(ResourceType c1, ResourceType c2){  //gives two of any resource of choice
        GameManager.instance.getBank().removeStock(c1, 1);
        GameManager.instance.getBank().removeStock(c2, 1);
        super.getOwner().getInventory().receiveItem(c1, 1);
        super.getOwner().getInventory().receiveItem(c2, 1);

    }
}
