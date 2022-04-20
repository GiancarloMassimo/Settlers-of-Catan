public class NodeGraphFactory {
    private final int nodeCount = 54;
    private final int edgeCount = 72;
    private Node[] nodes = new Node[nodeCount];
    private Edge[] edges = new Edge[edgeCount];
    int nodesAdded = 0;
    int edgesAdded = 0;

    Tile[][] map;

    public NodeGraphFactory(Tile[][] map) {
        this.map = map;
        createGraph();
    }

    public void createGraph() {
        createNodes();
    }

    private void createNodes() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                assignNullNodes(map[r][c]);
                assignEdges(map[r][c]);
                assignAdjacentNodes(map[r][c], r, c);
            }
        }
    }

    void assignEdges(Tile t) {
        createBidirectionalEdge(t.upper, t.upperRight);
        createBidirectionalEdge(t.upperRight, t.lowerRight);
        createBidirectionalEdge(t.lowerRight, t.lower);
        createBidirectionalEdge(t.lower, t.lowerLeft);
        createBidirectionalEdge(t.lowerLeft, t.upperLeft);
        createBidirectionalEdge(t.upperLeft, t.upper);
    }

    void createBidirectionalEdge(Node a, Node b) {
        Edge e = new Edge(a, b);
        if (a.addEdge(e) && b.addEdge(e)) {
            edges[edgesAdded] = e;
            e.index = edgesAdded;
            edgesAdded++;
        }
    }

    void assignNullNodes(Tile t) {
        if (t.upper == null) {
            t.upper = addNode(new Node());
        }
        t.upper.addAdjacentTiles(t);

        if (t.upperRight == null) {
            t.upperRight = addNode(new Node());
        }
        t.upperRight.addAdjacentTiles(t);

        if (t.lowerRight == null) {
            t.lowerRight = addNode(new Node());
        }
        t.lowerRight.addAdjacentTiles(t);

        if (t.lower == null) {
            t.lower = addNode(new Node());
        }
        t.lower.addAdjacentTiles(t);

        if (t.lowerLeft == null) {
            t.lowerLeft = addNode(new Node());
        }
        t.lowerLeft.addAdjacentTiles(t);

        if (t.upperLeft == null) {
            t.upperLeft = addNode(new Node());
        }
        t.upperLeft.addAdjacentTiles(t);
    }

    public void assignAdjacentNodes(Tile t, int r, int c) {
        if (r < 3) {
            if (isValidPosition(r - 1, c - 1)) {
                map[r - 1][c - 1].lowerRight = t.upper;
                map[r - 1][c - 1].lower = t.upperLeft;
            }

            if (isValidPosition(r - 1, c)) {
                map[r - 1][c].lowerLeft = t.upper;
                map[r - 1][c].lower = t.upperRight;
            }
        }
        else {
            if (isValidPosition(r - 1, c)) {
                map[r - 1][c].lowerRight = t.upper;
                map[r - 1][c].lower = t.upperLeft;
            }

            if (isValidPosition(r - 1, c + 1)) {
                map[r - 1][c + 1].lowerLeft = t.upper;
                map[r - 1][c + 1].lower = t.upperRight;
            }
        }

        if (isValidPosition(r, c + 1)) {
            map[r][c + 1].upperLeft = t.upperRight;
            map[r][c + 1].lowerLeft = t.lowerRight;
        }

        if (r < 2) {
            if (isValidPosition(r + 1, c + 1)) {
                map[r + 1][c + 1].upperLeft = t.lower;
                map[r + 1][c + 1].upper = t.lowerRight;
            }

            if (isValidPosition(r + 1, c)) {
                map[r + 1][c].upperRight = t.lower;
                map[r + 1][c].upper = t.lowerLeft;
            }
        } else {
            if (isValidPosition(r + 1, c)) {
                map[r + 1][c].upperLeft = t.lower;
                map[r + 1][c].upper = t.lowerRight;
            }

            if (isValidPosition(r + 1, c - 1)) {
                map[r + 1][c - 1].upperRight = t.lower;
                map[r + 1][c - 1].upper = t.lowerLeft;
            }
        }

        if (isValidPosition(r, c - 1)) {
            map[r][c - 1].upperRight = t.upperLeft;
            map[r][c - 1].lowerRight = t.lowerLeft;
        }
    }

    public boolean isValidPosition(int r, int c) {
        try {
            Tile t = map[r][c];
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private Node addNode(Node node) {
        nodes[nodesAdded] = node;
        node.index = nodesAdded;
        nodesAdded++;
        return node;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Edge[] getEdges() {
        return edges;
    }
}
