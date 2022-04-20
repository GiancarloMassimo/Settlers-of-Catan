import java.util.HashMap;

public class LongestRoad {
    private Edge[] edges;
    private Player longestRoadPlayer = null;
    private int longestRoadLength = 4; // Starts at 4 because the min length for longest road is 5
    private HashMap<Player, Integer> longestRoads;

    public LongestRoad() {
        edges = GameManager.instance.getMap().getEdges();

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
        traverseASide(player, edge, visited, 0, aSideLength);
        visited[edge.index] = false;
        traverseBSide(player, edge, visited, 0, bSideLength);

        visited = new boolean[edges.length];
        traverseBSide(player, edge, visited, 0, bSideLength);
        visited[edge.index] = false;
        traverseASide(player, edge, visited, 0, aSideLength);

        int length = aSideLength.value + bSideLength.value + 1;

        System.out.println(player + " " + length);
    }

    private void traverseASide(Player player, Edge edge, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        for (Edge aSideEdge : edge.a.getEdges()) {
            if (!visited[aSideEdge.index] && aSideEdge.road != null && aSideEdge.road.getOwner() == player) {
                traverseASide(player, aSideEdge, visited, length + 1, maxLength);
            } else {
                if (length > maxLength.value) {
                    maxLength.value = length;
                }
            }
        }
    }

    private void traverseBSide(Player player, Edge edge, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        for (Edge bSideEdge : edge.b.getEdges()) {
            if (!visited[bSideEdge.index] && bSideEdge.road != null && bSideEdge.road.getOwner() == player) {
                traverseBSide(player, bSideEdge, visited, length + 1, maxLength);
            } else {
                if (length > maxLength.value) {
                    maxLength.value = length;
                }
            }
        }
    }
}
