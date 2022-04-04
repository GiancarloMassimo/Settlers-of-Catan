public class Building {
    private int type;//1-settlement, 2-city
    Player owner;
    Node location;
    public Building(Player p, Node n) {
        owner = p;
        type = 1;
        location=n;
    }
//    public getResources(){
//        Node location;
//        for(Tile t:location.getTiles())
//    }
    public void upgrade() {
        if(type==1) type++;
    }
    public Player getOwner(){
        return owner;
    }
    public Node getLocation(){
        return location;
    }
    public int getType(){
        return type;
    }
}
