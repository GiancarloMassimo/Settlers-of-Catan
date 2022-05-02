public class DevelopmentCard {
    private Player owner;
    public DevelopmentCard(Player o){
        owner =o;
    }

    public Player getOwner() {
        return owner;
    }

    public void use() {
        GameManager.instance.getBank().changeDevelopmentCardCount(1);
    }
}
