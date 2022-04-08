public class Edge {
    Node a, b;
    int screenX, screenY;

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
}
