public class Edge {
    Node a, b;
    Road road;
    int screenX, screenY;
    Road road = null;

    public Edge(Node a, Node b) {
        this.a = a; this.b = b;
    }

    public Node getAdjacentNode(Node n) {
        if (n == b) return a;
        if (n == a) return b;
        return null;
    }

    public boolean equals(Edge o) {
        return (a == o.a && b == o.b) || (a == o.b && b == o.a);
    }

    public void createRoad(Road r){
        road = r;
    }

    public Road getRoad(){
        return road;
    }

    public boolean roadAvailable(Player p){//check if edge is touching road or building owned by player p, assumes current edge is empty
        if(a.getBuilding().getOwner().equals(p)) return true;
        if(b.getBuilding().getOwner().equals(p)) return true;

        for(Edge e:a.getEdges()){
            if(e.getRoad().getOwner().equals(p) && !a.containsBuilding()) return true;
        }
        for(Edge e:b.getEdges()){
            if(e.getRoad().getOwner().equals(p) && !b.containsBuilding()) return true;
        }
        return false;
    }

    public boolean containsRoad(){ return road!=null;}
}
