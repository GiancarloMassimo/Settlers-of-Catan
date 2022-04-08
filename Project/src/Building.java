public class Building {
    private BuildingType type;
    Player owner;
    Node location;
    public Building(Player p, Node n) {
        owner = p;
        type = BuildingType.Settlement;
        location=n;
    }
//    public getResources(){
//        Node location;
//        for(Tile t:location.getTiles())
//    }
    public void upgrade() {
        if(type==BuildingType.Settlement) type = BuildingType.City;
    }
    public Player getOwner(){
        return owner;
    }
    public Node getLocation(){
        return location;
    }
    public BuildingType getType(){
        return type;
    }
}
