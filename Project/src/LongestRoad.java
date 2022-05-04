import java.util.HashMap;

public class LongestRoad {
    private Edge[] edges;
    private Player longestRoadPlayer = null;
    private int longestRoadLength = 4; // Starts at 4 because the min length for longest road is 5
    private HashMap<Player, Integer> longestRoads;
    private HashMap<Player, Helpers.ReferenceSourceEdge> lastSourceRoad;

    public LongestRoad() {
        edges = GameManager.instance.getMap().getEdges();
        longestRoads = new HashMap<>();
        lastSourceRoad = new HashMap<>();
        for (Player player : GameManager.instance.getPlayers()) {
            longestRoads.put(player, 0);
            lastSourceRoad.put(player, null);
        }
    }

    public void measureLongestRoad(Road source) {
        Player player = source.getOwner();
        boolean[] visited = new boolean[edges.length];

        var sourceReference = new Helpers.ReferenceSourceEdge(source.getEdge(), source.getEdge().a);
        findSource(player, source.getEdge(), visited, sourceReference);
        lastSourceRoad.put(player, sourceReference);

        Edge edge = sourceReference.edge;
        Node direction = sourceReference.node;

        var longestLength = new Helpers.ReferenceInt(1);
        visited = new boolean[edges.length];
        traverseRoads(player, edge, direction, visited, 1, longestLength);

        processRoadMeasurement(player, longestLength.value);
    }

    public void checkLongestRoadBreakup(Player player) {
        var longestLength = new Helpers.ReferenceInt(1);
        for (Edge edge : edges) {
            if (edge.road != null && edge.road.getOwner() == player) {
                Node d = edge.a;
                boolean[] visited = new boolean[edges.length];
                traverseRoads(player, edge, d, visited, 1, longestLength);

                d = edge.b;
                visited = new boolean[edges.length];
                traverseRoads(player, edge, d, visited, 1, longestLength);
            }
        }

        longestRoads.put(player, longestLength.value);

        Player newLongestPlayer = null;
        int currentLongest = 4;
        if (longestRoadPlayer != null)
            longestRoadPlayer.changePublicVictoryPoints(-2);

        for (Player p : longestRoads.keySet()) {
            if (longestRoads.get(p) > currentLongest) {
                newLongestPlayer = p;
                currentLongest = longestRoads.get(p);
            } else if (longestRoads.get(p) == currentLongest && currentLongest >= 5) {
                if (p == longestRoadPlayer) {
                    newLongestPlayer = p;
                }
            }
        }

        longestRoadPlayer = newLongestPlayer;
        longestRoadLength = currentLongest;
        if (longestRoadPlayer != null) {
            GameLog.instance.logEvent(newLongestPlayer + " took longest road");
            longestRoadPlayer.changePublicVictoryPoints(2);
        } else {
            GameLog.instance.logEvent("Longest road lost");
        }
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
                if (edge.a.building != null && edge.a.building.getOwner() != player) {
                    return;
                }
                findSource(player, nextEdge, visited, source);
            }
        }

        for (Edge nextEdge : edge.b.getEdges()) {
            if (!visited[nextEdge.index] && nextEdge.road != null && nextEdge.road.getOwner() == player) {
                if (edge.b.building != null && edge.b.building.getOwner() != player) {
                    return;
                }
                findSource(player, nextEdge, visited, source);
            }
        }
    }

    private void traverseRoads(Player player, Edge edge, Node source, boolean[] visited, int length, Helpers.ReferenceInt maxLength) {
        visited[edge.index] = true;

        Node nextNode = edge.getAdjacentNode(source);

        for (Edge nextEdge : nextNode.getEdges()) {
            if (!visited[nextEdge.index] && nextEdge.road != null && nextEdge.road.getOwner() == player &&
                    (nextNode.building == null || nextNode.building.getOwner() == player)) {
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
