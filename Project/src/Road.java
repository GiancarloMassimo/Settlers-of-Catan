public class Road {
    private Player owner;
    private Edge edge;
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

    //    public boolean Connects(){
//        return true;
//    }
//
//    public int longestRoad(){
//        int num =0;
//        return num;
//    }
}
