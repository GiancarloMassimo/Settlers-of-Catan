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
        boolean[] visited = new boolean[edges.length];

        var sourceReference = new Helpers.ReferenceSourceEdge(source.getEdge(), source.getEdge().a);
        findSource(player, source.getEdge(), visited, sourceReference);

        Edge edge = sourceReference.edge;
        Node direction = sourceReference.node;

        var longestLength = new Helpers.ReferenceInt(1);
        visited = new boolean[edges.length];
        traverseRoads(player, edge, direction, visited, 1, longestLength);

        processRoadMeasurement(player, longestLength.value);
    }

    private void processRoadMeasurement(Player player, int length) {
        if (length > longestRoadLength) {
            if (longestRoadPlayer != null && longestRoadPlayer != player) {
                longestRoadPlayer.changePublicVictoryPoints(-2);
            }
            if (player != longestRoadPlayer) {
                player.changePublicVictoryPoints(2);
                GameLog.instance.logEvent(player + " took longest road");
            }

            longestRoadLength = length;
            longestRoadPlayer = player;
        }

        if (longestRoads.get(player) < length) {
            longestRoads.put(player, length);
        }
    }

    private void findSource(Player player, Edge edge, boolean[] visited, Helpers.ReferenceSourceEdge source) {
        visited[edge.index] = true;

        if (edge.a.connectedRoadCount(player) == 1) {
            source.edge = edge;
            source.node = edge.a;
            return;
        } else if (edge.b.connectedRoadCount(player) == 1) {
            source.edge = edge;
            source.node = edge.b;
            return;
        }

        for (Edge nextEdge : edge.a.getEdges()) {
            if (!visited[nextEdge.index] && nextEdge.road != null && nextEdge.road.getOwner() == player) {
                findSource(player, nextEdge, visited, source);
            }
        }

        for (Edge nextEdge : edge.b.getEdges()) {
            if (!visited[nextEdge.index] && nextEdge.road != null && nextEdge.road.getOwner() == player) {
                findSource(player, nextEdge, visited, source);
            }
        }
    }

    private void traverseRoads(Player player, Edge edge, Node source, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        Node nextNode = edge.getAdjacentNode(source);

        for (Edge nextEdge : nextNode.getEdges()) {
            if (!visited[nextEdge.index] && nextEdge.road != null && nextEdge.road.getOwner() == player) {
                traverseRoads(player, nextEdge, nextNode, visited, length + 1, maxLength);
            } else {
                if (length > maxLength.value) {
                    maxLength.value = length;
                }
            }
        }
    }

    public int getRoadLength(Player player) {
        return longestRoads.get(player);
    }

    public boolean hasLongestRoad(Player player) {
        return player == longestRoadPlayer;
    }
}
