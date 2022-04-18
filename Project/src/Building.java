public class Building {
    private BuildingType type;
    Player owner;
    Node location;
    public Building(Player p, Node n) {
        owner = p;
        type = BuildingType.Settlement;
        location=n;
    }

    public void collectResources() {
        var tiles = location.getAdjacentTiles();
        for (Tile t : tiles) {
            if (GameManager.instance.getRobber().getTile() == t) {
                continue;
            }

            if (GameManager.instance.getDice().getDiceTotal() == t.getNum()) {
                int count = type == BuildingType.Settlement ? 1 : 2;

                if (!GameManager.instance.getBank().hasResource(t.getResourceType(), count)) {
                    return;
                }

                owner.getInventory().receiveItem(t.getResourceType(), count);
                GameManager.instance.getBank().removeStock(t.getResourceType(), count);
                GameLog.instance.logEvent(owner + " received " + count + " " + t.getResourceType());


            }
        }
    }

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
