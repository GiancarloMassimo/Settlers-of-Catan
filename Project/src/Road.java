public class Road {

    private final Player owner;
    private final Edge edge;
    public Road(Player o, Edge edge){
        owner = o;
        this.edge = edge;
    }

    public Player getOwner() {
        return owner;
    }

    public Edge getEdge() {
        return edge;
    }
    
}
