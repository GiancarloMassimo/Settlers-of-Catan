public class Road {
    Edge location;
    Player owner;
    public Road(Player p, Edge e){
        owner = p;
        location = e;
    }
    public Player getOwner(){
        return owner;
    }
    public Edge getLocation(){
        return location;
    }

}
