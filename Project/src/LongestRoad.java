import java.util.HashMap;

public class LongestRoad {
    private Edge[] edges;
    private Player longestRoadPlayer = null;
    private int longestRoadLength = 4; // Starts at 4 because the min length for longest road is 5
    private HashMap<Player, Integer> longestRoads;

    public LongestRoad() {
        edges = GameManager.instance.getMap().getEdges();

        /*for (Edge edge : edges) {
            System.out.println(edge.index + ": \n");
            System.out.print("A side: ");
            for (Edge edge1 : edge.a.getEdges()) {
                System.out.print(edge1.index + " ");
            }
            System.out.println();
            System.out.print("B side: ");
            for (Edge edge1 : edge.b.getEdges()) {
                System.out.print(edge1.index + " ");
            }
            System.out.println("\n");
        }*/

        longestRoads = new HashMap<>();
        for (Player player : GameManager.instance.getPlayers()) {
            longestRoads.put(player, 0);
        }
    }

    public void measureLongestRoad(Road source) {
        Player player = source.getOwner();
        Edge edge = source.getEdge();

        var aSideLength = new Helpers.ReferenceInt(0);
        var bSideLength = new Helpers.ReferenceInt(0);

        boolean[] visited = new boolean[edges.length];
        traverseASide(player, edge, null, visited, 0, aSideLength);
        visited[edge.index] = false;
        traverseBSide(player, edge, null, visited, 0, bSideLength);

        visited = new boolean[edges.length];
        traverseBSide(player, edge, null, visited, 0, bSideLength);
        visited[edge.index] = false;
        traverseASide(player, edge, null, visited, 0, aSideLength);

        int length = aSideLength.value + bSideLength.value + 1;

        System.out.println(player + " " + length);
    }

    private void traverseASide(Player player, Edge edge, Node prev, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        Node nextNode = length == 0 ? edge.a : edge.getAdjacentNode(prev);

        for (Edge aSideEdge : nextNode.getEdges()) {
            if (!visited[aSideEdge.index] && aSideEdge.road != null && aSideEdge.road.getOwner() == player) {
                traverseASide(player, aSideEdge, nextNode, visited, length + 1, maxLength);
            } else {
                if (length > maxLength.value) {
                    maxLength.value = length;
                }
            }
        }
    }

    private void traverseBSide(Player player, Edge edge, Node prev, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        Node nextNode = length == 0 ? edge.b : edge.getAdjacentNode(prev);

        for (Edge bSideEdge : nextNode.getEdges()) {
            if (!visited[bSideEdge.index] && bSideEdge.road != null && bSideEdge.road.getOwner() == player) {
                traverseBSide(player, bSideEdge, nextNode, visited, length + 1, maxLength);
            } else {
                if (length > maxLength.value) {
                    maxLength.value = length;
                }
            }
        }
    }
}
